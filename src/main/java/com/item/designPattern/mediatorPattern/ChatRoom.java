package com.item.designPattern.mediatorPattern;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " [" + user.getName() +"] : " + message);
    }
}
