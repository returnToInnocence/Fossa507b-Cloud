package com.zenq.cloud507b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/api/hello")
    @ResponseBody
    public String hello(){
        //调用业务，接收前端参数
        return "hello world";
    }
}