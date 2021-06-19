// JavaHeapSpaceDemo.java
// JVM Parameters: -Xms128m -Xmx256m
// JVM Parameters: -Xms256m -Xmx512m
// JVM Parameters: -Xms512m -Xmx1024m

package com.ranga.java.oom;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        GetHeapSizeDemo.printMemory();
        Integer[] array = new Integer[1000*1000*100];
        GetHeapSizeDemo.printMemory();
        System.out.println("Successful");
    }
}