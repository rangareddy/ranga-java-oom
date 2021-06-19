package com.ranga.java.oom.memory;

// StackOverflow Circular dependency

class A {
    public A() {
        B b = new B();
    }
}

class B {
    public B() {
        A a = new A();
    }
}
public class StackOverflowErrorDemo2 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

    }
}
