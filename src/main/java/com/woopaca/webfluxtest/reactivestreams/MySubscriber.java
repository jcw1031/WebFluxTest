package com.woopaca.webfluxtest.reactivestreams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber {

    private static final int BUFFER_SIZE = 3;

    private Subscription subscription;
    private int requestCount = BUFFER_SIZE;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println("4. 구독자: 구독 정보 잘 받았음");
        subscription.request(requestCount); // 데이터 한 개씩만 보내줘 (백프레셔 -> 소비자가 한 번에 처리할 수 있는 개수 요청)
    }

    @Override
    public void onNext(Object o) {
        System.out.println("구독 데이터 전달: " + o);

        if (--requestCount == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("0.5초 지남");
            requestCount = BUFFER_SIZE;
            subscription.request(requestCount);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("구독 에러");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료");
    }
}
