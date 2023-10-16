package com.playdata.firstservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("first-service") // gateway서버의 predicates의 식별 주소를 추가해야 합니다.
@RequiredArgsConstructor
public class FirstserviceController {

    private final Environment env;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service!";
    }

    @GetMapping("header-check")
    public String headerCheck(@RequestHeader("fsreqhyml") String header){
        return header;
    }

    @GetMapping("port-check")
    public String portCheck(){
        // application.yml파일에 기입된 정보를 얻어와서 자바 자료로 만들어줌.
        // 그냥 server.port로 기입하면 실제로 대입된 값인 0으로 출력
        return env.getProperty("local.server.port");
    }

}
