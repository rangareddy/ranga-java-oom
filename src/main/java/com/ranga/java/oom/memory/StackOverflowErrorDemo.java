package com.ranga.java.oom.memory;

// StackOverflowErrorDemo.java
public class StackOverflowErrorDemo {

    public static void main(String args[]) {
        long val = 5;

        long fact = factorial(val);
        System.out.println("Factorial of "+val+" is "+fact);
    }

    public static long factorial(long val) {
        if(val == 1) {
            return 1;
        } else {
            //return val * factorial(val - 1);
            return val * factorial(val--);
        }
    }
}

// 5 * 4 * 3 * 2 * 1
