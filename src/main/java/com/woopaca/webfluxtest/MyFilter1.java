package com.woopaca.webfluxtest;

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

        /**
         * Flux
         * -------------------------------------
         */
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
        /**
         * -------------------------------------
         */

        while (true) {
            try {
                if (eventNotifier.isChange()) {
                    writer.println("응답: " + eventNotifier.getNewEvent());
                    writer.flush();
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
