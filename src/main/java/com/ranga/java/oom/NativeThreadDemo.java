// NativeThreadDemo.java

package com.ranga.java.oom;

public class NativeThreadDemo {
    public static void main(String[] args) {
        int count = 0;
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
            System.out.println(count++);
        }
    }
}
