package com.spring.diAndIoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDemoController {

    @Autowired
    TestBean testBean;

    @GetMapping("/testBean")
    public void testBean(){
        System.out.println(testBean);
    }
}
