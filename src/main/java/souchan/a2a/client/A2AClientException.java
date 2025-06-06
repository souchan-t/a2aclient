package souchan.a2a.client;

public class A2AClientException extends RuntimeException {
    A2AClientException(String message) {
        super(message);
    }
    A2AClientException(Throwable cause) {
        super(cause);
    }
}
