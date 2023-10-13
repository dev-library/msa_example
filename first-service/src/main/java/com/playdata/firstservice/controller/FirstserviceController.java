package com.playdata.firstservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("first-service") // gateway서버의 predicates의 식별 주소를 추가해야 합니다.
public class FirstserviceController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service!";
    }

    @GetMapping("header-check")
    public String headerCheck(@RequestHeader("fsreqhyml") String header){
        return header;
    }
}
