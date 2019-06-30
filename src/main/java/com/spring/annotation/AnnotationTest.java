package com.spring.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationTest{

    @GetMapping("testAno")
    @Change("改变")
    public String testAno(@RequestParam String msg){
        System.out.println("test......");
        return msg;
    }

}
