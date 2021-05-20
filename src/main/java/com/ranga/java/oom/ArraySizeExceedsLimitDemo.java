// ArraySizeExceedsLimitDemo.java
// JVM Parameters: -Xms10m -Xmx10m

package com.ranga.java.oom;

public class ArraySizeExceedsLimitDemo {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] bytes = new int[MAX_ARRAY_SIZE];
    }
}
