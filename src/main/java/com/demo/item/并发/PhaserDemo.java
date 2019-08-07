package com.demo.item.并发;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/*
 * @author Lee
 * @description 同步辅助类
 * 案例场景：Phaser将同步三个并发任务。
 * 这三个任务将在三个不同的文件夹及其子文件夹中查找过去24小时内改过扩展名为.txt的文件。这个任务分解为三个步骤：
 * ①在指定文件夹及其子文件夹中获得扩展名为.txt的文件；
 * ②对第一步的结果过滤，删除修改时间超过24小时的文件；③将结果打印数据到控制台。
 * @date 2019/4/23 17:13
 * @param
 * @return
 **/

public class PhaserDemo {

    public static void main(String[] args) {
        Phaser phaser=new Phaser(3);
        FileSearch system=new FileSearch("E:\\a", ".txt",
                phaser);
        FileSearch apps=new FileSearch("E:\\b", ".txt",
                phaser);
        FileSearch documents=new FileSearch("E:\\c", ".txt",
                phaser);
        Thread systemThread=new Thread(system, "system-a");
        systemThread.start();
        Thread appsThread=new Thread(apps, "apps-b");
        appsThread.start();
        Thread documentsThread=new Thread(documents, "documents-c");
        documentsThread.start();
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Terminated:"+ phaser.isTerminated());
    }
    static class FileSearch implements Runnable {
        private String initPath;// 查找路径
        private String end;// 文件后缀
        private List<String> results;// 结果集
        private Phaser phaser;

        public FileSearch(String initPath, String end, Phaser phaser) {
            this.initPath = initPath;
            this.end = end;
            this.phaser = phaser;
            this.results = new ArrayList<String>();
        }

        private void direactoryProcess(File file) {
            File list[] = file.listFiles();
            if (list != null) {
                for (File f : list) {
                    if (f.isDirectory()) {
                        direactoryProcess(f);
                    } else {
                        fileProcess(f);
                    }
                }
            }
        }

        private void fileProcess(File file) {
            if (file.getName().endsWith(end)) {
                results.add(file.getAbsolutePath());
            }
        }

        private void filterResult() {
            List<String> newResult = new ArrayList<String>();
            long actualDate = System.currentTimeMillis();
            for (int i = 0; i < results.size(); i++) {
                File file = new File(results.get(i));
                long lastModifyTime = file.lastModified();
                if (actualDate - lastModifyTime < TimeUnit.MICROSECONDS.
                        convert(1,
                                TimeUnit.DAYS)) {
                    newResult.add(results.get(i));
                }
            }
            results = newResult;
        }

        private boolean checkResults() {
            if (results.isEmpty()) {
                System.out.println(Thread.currentThread().
                        getName() + ": Phase "
                        + phaser.getPhase() + " 0 result");
                System.out.println(Thread.currentThread().
                        getName() + ": Phase "
                        + phaser.getPhase() + " end");
                phaser.arriveAndDeregister();
                return false;
            } else {
                System.out.println(Thread.currentThread().
                        getName() + ": Phase "
                        + phaser.getPhase() + " " +
                        results.size() + " result");
                phaser.arriveAndAwaitAdvance();
                return true;
            }
        }

        private void showInfo() {
            for (int i = 0; i < results.size(); i++) {
                System.out.println(Thread.currentThread().
                        getName() + ":"
                        + results.get(i));
            }
            phaser.arriveAndAwaitAdvance();
        }

        @Override
        public void run() {
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().
                    getName() + ": Starting");
            File file = new File(initPath);
            if (file.isDirectory()) {
                direactoryProcess(file);
            }
            if (!checkResults()) {
                return;
            }
            filterResult();
            if (!checkResults()) {
                return;
            }
            showInfo();
            phaser.arriveAndDeregister();   //所有线程都到达了这一步 就进行下一步任务
            System.out.println(Thread.currentThread().
                    getName() + ": Work completed");
        }
    }
}
