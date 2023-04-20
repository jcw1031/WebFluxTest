package com.woopaca.webfluxtest.my;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class MyFilter1 implements Filter {

    private final EventNotifier eventNotifier;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터 실행됨");

        // 1. Reactive Streams 라이브러리를 사용하면 표준을 지켜 응답할 수 있다.
        HttpServletResponse servletResponse = (HttpServletResponse) response;
//        servletResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
        servletResponse.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = servletResponse.getWriter();
        for (int i = 0; i < 5; i++) {
            writer.println("응답: " + i);
            writer.flush(); // 버퍼를 비운다. 하지만 Content-Type이 text/plain인 경우에는 웹 브라우저가 받아온 데이터를 쌓아두다가 모두 전송되면 화면에 띄워준다.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. SSE Emitter 라이브러리를 사용하면 편하게 구현할 수 있다.
        while (true) {
            try {
                if (eventNotifier.isChange()) {
                    String newEvent = eventNotifier.getNewEvent();
                    writer.println("응답: " + newEvent);
                    writer.flush();
                    eventNotifier.setChange(false);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. WebFlux -> Reactive Streams가 적용된 stream을 배운다. (비동기 단일 스레드 동작)
        // 4. Servlet MVC -> (멀티 스레드 방식)
    }
}
