package com.jojoldu.book.aws_spring_book.config;

import com.jojoldu.book.aws_spring_book.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    // LoginUserArgumentResolver가 스프링에서 인식 될 수 있도록 WebMvcConfigurer에 추가

    @Override
    // HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가 해야함
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
