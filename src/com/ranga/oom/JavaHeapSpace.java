package com.ranga.oom;

public class JavaHeapSpace {
    private final static int size=1024*1024*1024;
    public static void main(String[] args) {
        javaHeapSpace();
    }
    public static void javaHeapSpace() {
        Integer[] array = new Integer[size];
    }
}
