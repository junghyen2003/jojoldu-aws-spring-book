package com.jojoldu.book.chapter1.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello")
    // HTTP Method인 Get의 요청을 받을 수 있는 API 생성
    // 이전에는 @RequestMapping(method = RequestMethod.GET) 으로 사용
    public String hello() {
        return "hello";
    }
}
