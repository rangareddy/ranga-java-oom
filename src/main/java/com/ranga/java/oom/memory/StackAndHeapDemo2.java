package com.ranga.java.oom.memory;

public class StackAndHeapDemo2 {

    static void test1() {
        System.out.println("test1() begin");
        test2();
        System.out.println("test1() end");
    }

    static void test2() {
        System.out.println("test2()");
    }

    public static void main(String[] args) {
        System.out.println("main() started");
        test1();
        System.out.println("main() ended");
    }
}
