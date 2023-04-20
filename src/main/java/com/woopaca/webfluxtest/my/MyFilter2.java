package com.woopaca.webfluxtest.my;

import lombok.RequiredArgsConstructor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class MyFilter2 implements Filter {

    private final EventNotifier eventNotifier;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터2 실행됨");

        // 데이터 발생
        eventNotifier.add("새로운 데이터");
    }
}
