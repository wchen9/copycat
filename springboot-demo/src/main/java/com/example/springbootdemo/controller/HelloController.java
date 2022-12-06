package com.example.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @RequestMapping("/01")
    public String doHello(){
        log.info("print hello ");
        return "hello";
    }

}
