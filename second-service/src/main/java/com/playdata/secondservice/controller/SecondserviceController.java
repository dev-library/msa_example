package com.playdata.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("second-service")
public class SecondserviceController {

    @GetMapping("hello")
    public String hello(){
        return "Hello, Second service!!!";
    }
}
