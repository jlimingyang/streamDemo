package com.demo.spring.diAndIoc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
