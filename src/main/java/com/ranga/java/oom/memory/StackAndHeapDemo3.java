package com.ranga.java.oom.memory;

public class StackAndHeapDemo3 {

    // static
    static int staticVariable = 10;

    // instance
    int nonStaticVariable = 20;

    // static block
    static
    {
        staticVariable = 20;
    }

    // non static block
    {
        nonStaticVariable = 30;
    }

    static void test1() {
        System.out.println(staticVariable);
        staticVariable = 40;
    }

    void test2() {
        System.out.println(nonStaticVariable);
        nonStaticVariable = 50;
    }

    public static void main(String[] args) {
        System.out.println("main() started");

        // static method
        test1();

        StackAndHeapDemo3 obj = new StackAndHeapDemo3();
        obj.test2();

        // local variable
        int a = 10;
        int b = 20;
        int c = a + b;
        System.out.println(c);

        System.out.println("main() ended");
    }
}
