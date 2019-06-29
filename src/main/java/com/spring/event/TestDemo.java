package com.spring.event;


import com.Application;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDemo {

@Autowired
ApplicationContext applicationContext;

    @GetMapping("test01")
    public void  test01(@RequestParam String msg){
        applicationContext.publishEvent(new OrderEvent("testListener",msg));
    }
    
}
