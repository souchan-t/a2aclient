package souchan.a2a.client;

public interface A2AEventListener {
    void onEvent(Object event);
    void onError(Throwable throwable);
    void onComplete();
}

