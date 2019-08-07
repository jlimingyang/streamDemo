package com.demo.spring.diAndIoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DITestBean {

    @Bean
    @Scope("prototype")
    public TestBean testBean(){
        return new TestBean("test",11);
    }
}
