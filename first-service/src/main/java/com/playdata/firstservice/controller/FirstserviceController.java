package com.playdata.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("first-service") // gateway서버의 predicates의 식별 주소를 추가해야 합니다.
public class FirstserviceController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service!";
    }
}
