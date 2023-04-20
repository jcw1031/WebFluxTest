package com.woopaca.webfluxtest.reactivestreams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독 정보(구독자, 어떤 데이터를 구독할지)
public class MySubscription implements Subscription {

    private final Subscriber<Integer> subscriber;
    private final Iterator<Integer> data;

    public MySubscription(Subscriber<? super Integer> subscriber, Iterable<Integer> data) {
        this.subscriber = (Subscriber<Integer>) subscriber;
        this.data = data.iterator();
    }

    @Override
    public void request(long n) {
        while (n-- > 0) {
            if (data.hasNext()) {
                subscriber.onNext(data.next());
                continue;
            }
            subscriber.onComplete();
            break;
        }
    }

    @Override
    public void cancel() {

    }
}
