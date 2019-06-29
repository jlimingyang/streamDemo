package com.spring.event;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDemo {

    @GetMapping("test01")
    public void  test01(@RequestParam String msg){
        System.out.println("test:"+msg);
    }
    
}
