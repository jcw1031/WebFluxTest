package com.woopaca.webfluxtest.reactivestreams;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPublisher implements Publisher<Integer> {

    private final Iterable<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        System.out.println("1. 구독자: 구독 하겠습니다");
        System.out.println("2. 제공자: ㅇㅋ -> 구독 정보 만들어 줄테니 기다려");
        MySubscription subscription = new MySubscription(subscriber, data);
        System.out.println("3. 제공자: 구독 정보 생성했음");
        subscriber.onSubscribe(subscription);
    }
}
