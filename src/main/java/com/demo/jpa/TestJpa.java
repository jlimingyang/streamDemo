package com.demo.jpa;

import com.demo.SpringBootBaseTest;
import com.demo.jpa.po.TestDemo;
import com.demo.jpa.repository.TestDemoRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class TestJpa extends SpringBootBaseTest {

    @Autowired
    private TestDemoRepository testDemoRepository;

    @Test
    public void testjpa01() {
        List<TestDemo> all = testDemoRepository.findAll();
        System.out.println(JSON.toString(all));
    }
}
