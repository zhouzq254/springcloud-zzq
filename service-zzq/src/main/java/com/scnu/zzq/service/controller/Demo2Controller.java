package com.scnu.zzq.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/demo2")
public class Demo2Controller {


    @GetMapping("/test2")
    public String test2(){
        return "Hello world !";
    }
}
