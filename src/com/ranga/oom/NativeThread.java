package com.ranga.oom;

public class NativeThread {
    public static void main(String[] args) {
        runThreads();
    }
    public static void runThreads() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
