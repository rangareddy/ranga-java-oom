// ArraySizeExceedsLimitDemo.java
// JVM Parameters: -Xms10m -Xmx10m

package com.ranga.java.oom;

public class ArraySizeExceedsLimitDemo {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    //private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 1000000000;

    public static void main(String[] args) {
        int[] bytes = new int[MAX_ARRAY_SIZE];
        System.out.println("Done");
    }
}
