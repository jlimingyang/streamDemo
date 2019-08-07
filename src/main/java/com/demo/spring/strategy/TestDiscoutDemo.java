package com.demo.spring.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDiscoutDemo {

    @Autowired
    SaleService saleService;

    @GetMapping("/testStrategy")
    public void testDiscount(){
        System.out.println(saleService.sale("a", 10));
        System.out.println(saleService.sale("b", 10));
        System.out.println(saleService.sale("c", 10));
    }
}
