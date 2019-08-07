package com.demo.item.designPattern.adapterPattern;

public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc...");
    }

    @Override
    public void playMp4(String fileName) {

    }
}
