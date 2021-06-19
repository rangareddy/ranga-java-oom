package com.ranga.java.oom.memory;

class Test {
    public Test() {
        Test t = new Test();
    }
}

public class StackOverflowErrorDemo3 {
    public static void main(String[] args) {
        Test t = new Test();
    }
}
