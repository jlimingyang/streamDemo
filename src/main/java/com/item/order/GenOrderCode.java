package com.item.order;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenOrderCode {

    private  int i = 1;
    @Test
    public void test001() {
        String code = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-ss-mm-"))+i;
        i++;
        System.out.println(code);
    }
}
