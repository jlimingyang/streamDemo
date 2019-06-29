package com.spring.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestBean  {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
