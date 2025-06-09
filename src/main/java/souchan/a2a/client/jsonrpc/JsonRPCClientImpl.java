package souchan.a2a.client.jsonrpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import souchan.a2a.client.A2AEventListener;
import souchan.a2a.client.APIKeyCredential;
import souchan.a2a.client.Credential;
import souchan.a2a.models.SecurityScheme;

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

    public JsonRPCClientImpl() {
        this(HttpClient.newHttpClient());
    }

    public JsonRPCClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
    }

    public <REQ, RES extends JSONRPCResponse<T>, T> T request(String url,
                                                              JSONRPCRequest<REQ> request,
                                                              Class<RES> responseClass,
                                                              SecurityScheme securityScheme,
                                                              Credential credential) throws JSONRPCException {
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
                throw new RuntimeException("A2A クライアント Httpステータスコード:" + httpResponse.statusCode());
            } else {
                final RES response = objectMapper.readValue(httpResponse.body(), responseClass);
                return response.result().orElseThrow(() -> new JSONRPCException(response.error().orElseThrow()));
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public <REQ, RES extends JSONRPCResponse<T>, T> CompletableFuture<Void> requestSse(String url,
                                                                                       JSONRPCRequest<REQ> request,
                                                                                       Class<RES> responseClass,
                                                                                       A2AEventListener eventListener,
                                                                                       SecurityScheme securityScheme,
                                                                                       Credential credential) {
        //リクエストボデイ
        final String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
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
                                        final RES result = objectMapper.readValue(dataText, responseClass);
                                        eventListener.onEvent(result.result().orElseThrow());

                                    } else {
                                        final var jsonRpcError = objectMapper.readValue(dataText, JSONRPCError.class);
                                        eventListener.onError(new JSONRPCException(jsonRpcError));
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
