package com.item.test;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Demo04 {
    @Test
    public void testParse(){
        System.out.println(LocalDateTime.of(LocalDate.parse("2018-07-15"), LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8")));
    }
}
