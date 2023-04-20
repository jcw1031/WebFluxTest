package com.woopaca.webfluxtest.reactivestreams;

public class MainApp {

    /*WebFlux => 단일 스레드, 비동기 + Stream을 통해 백프레셔가 적용된 데이터만큼 간헐적 응답이 가능
            + 데이터 소비가 끝나면 응답이 종료*/
    // SSE 적용(Servlet, WebFlux) => 데이터 소비가 끝나도 Stream 계속 유지
    public static void main(String[] args) {
        MyPublisher publisher = new MyPublisher();
        MySubscriber subscriber = new MySubscriber();

        publisher.subscribe(subscriber);
    }
}
