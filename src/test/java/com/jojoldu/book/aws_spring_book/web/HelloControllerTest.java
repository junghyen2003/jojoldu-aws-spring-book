package com.jojoldu.book.aws_spring_book.web;

import com.jojoldu.book.aws_spring_book.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행
// 본 테스트에서는 SpringRunner이라는 스프링 실행자를 사용
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class)
// 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중 할 수 있는 어노테이션
// 선언 할 경우 @Controller, @ControllerAdvice 등을 사용 할 수 있음
// 단 @Service, @Component, @Repository 등은 사용 할 수 없음
// 본 테스트에서는 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    @Autowired
    // 스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc;
    // 웹 API를 테스트 할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 해당 클래스를 통해 HTTP GET, POST 등에 대한 API를 테스트 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        //given
        String hello = "hello";

        //when
        ResultActions result = mvc.perform(get("/hello"));
                // MockMvc를 통해 /hello 주소로 HTTP GET 요청
                // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 할 수 있음

        //then
        result.andExpect(status().isOk())
                // mvc perform의 결과를 검증
                // HTTP Header의 Status를 검증
                // 200, 404, 500 등. 본 예에서는 OK(200) 인지 아닌지를 검증
                .andExpect(content().string(hello));
                // mvc perform의 결과를 검증
                // 응답 본문의 내용을 검증
                // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        //given
        String name = "hello";
        int amount = 1000;

        //when
        ResultActions result = mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)));
        // API 테스트 시 사용될 요청 파라미터를 설정
        // 단, 값은 String 만 허용
        // 따라서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능

        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        // Json 응답값을 필드별로 검증 할 수 있는 메소드
        // $을 기준으로 필드명을 명시
    }
}
