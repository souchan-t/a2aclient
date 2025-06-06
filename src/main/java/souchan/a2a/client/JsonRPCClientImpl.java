package souchan.a2a.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import souchan.a2a.models.SecurityScheme;
import souchan.a2a.models.response.JSONRPCError;
import souchan.a2a.models.response.JSONRPCRequest;
import souchan.a2a.models.response.JSONRPCResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JsonRPCClientImpl implements JsonRPCClient {
    final HttpClient httpClient;
    final ObjectMapper objectMapper;

    JsonRPCClientImpl() {
        this(HttpClient.newHttpClient());
    }

    JsonRPCClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
    }

    public <T extends JSONRPCResponse> Object request(String url, JSONRPCRequest request, Class<T> responseClass, SecurityScheme securityScheme, Credential credential) throws A2AClientException {
        try {
            //リクエストボデイ
            final var requestBody = objectMapper.writeValueAsString(request);

            //リクエスト
            var httpRequestBuilder = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody));

            //認証の設定
            if (securityScheme != null && credential != null) {

                switch (securityScheme) {
                    case SecurityScheme.APIKeySecurityScheme apiKeySecurityScheme -> {
                        if (apiKeySecurityScheme.in().equals("header")) {
                            httpRequestBuilder.header(apiKeySecurityScheme.name(), ((APIKeyCredential) credential).apiKey());

                        } else {
                            //todo
                            throw new UnsupportedOperationException("HTTPヘッダー以外のAPIキー設定は未実装");
                        }
                    }

                    default -> {
                        //todo
                        throw new UnsupportedOperationException("APIキー認証以外は未実装");
                    }
                }
            }

            var httpRequest = httpRequestBuilder.build();

            //JSONRPC送信
            final var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() < 200 || httpResponse.statusCode() >= 300) {
                throw new A2AClientException("A2A クライアント Httpステータスコード:" + httpResponse.statusCode());
            } else {
                final T response = objectMapper.readValue(httpResponse.body(), responseClass);
                return response.result().orElseThrow(() -> new A2AClientException(response.error().orElseThrow().message()));
            }

        } catch (IOException | InterruptedException e) {
            throw new A2AClientException(e);
        }
    }

    public <T extends JSONRPCResponse> CompletableFuture<Void> requestSse(String url, JSONRPCRequest request, Class<T> responseClass, SecurityScheme securityScheme, Credential credential, A2AEventListener eventListener) {
        //リクエストボデイ
        final String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new A2AClientException(e);
        }

        //リクエスト
        var httpRequestBuilder = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "text/event-stream")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody));

        //認証の設定
        if (securityScheme != null && credential != null) {

            switch (securityScheme) {
                case SecurityScheme.APIKeySecurityScheme apiKeySecurityScheme -> {
                    if (apiKeySecurityScheme.in().equals("header")) {
                        httpRequestBuilder.header(apiKeySecurityScheme.name(), ((APIKeyCredential) credential).apiKey());

                    } else {
                        //todo
                        throw new UnsupportedOperationException("HTTPヘッダー以外のAPIキー設定は未実装");
                    }
                }

                default -> {
                    //todo
                    throw new UnsupportedOperationException("APIキー認証以外は未実装");
                }
            }
        }

        var httpRequest = httpRequestBuilder.build();

        return this.httpClient
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenAccept(response -> {
                    try (final var isr = new InputStreamReader(response.body());
                         final var br = new BufferedReader(isr)) {
                        String line;
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((line = br.readLine()) != null) {
                            if (line.isEmpty()) {
                                //data: { "hoge":"foo" } みたいな形の想定
                                final var eventText = stringBuilder.toString();
                                if (eventText.startsWith("data:")) {
                                    final var dataText = eventText.substring(5).trim();
                                    final var nodeTree = objectMapper.readTree(dataText);
                                    if (nodeTree.has("result")) {
                                        final T result = objectMapper.readValue(dataText, responseClass);
                                        eventListener.onEvent(result.result().orElseThrow());

                                    } else {
                                        final var jsonRpcError = objectMapper.readValue(dataText, JSONRPCError.class);
                                        eventListener.onError(new A2AClientException(jsonRpcError.message()));
                                    }

                                } else {
                                    //予期しない例外
                                    eventListener.onError(new IllegalStateException("予期しないSSEイベントテキスト"));
                                }
                                stringBuilder.setLength(0);
                            } else {
                                stringBuilder.append(line).append("\n");
                            }
                        }
                        eventListener.onComplete();

                    } catch (IOException e) {
                        eventListener.onError(e);

                    }
                });

    }

}
