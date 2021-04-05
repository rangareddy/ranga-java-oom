package com.ranga.oom;

public class ArraySizeExceedsLimit {
    public static void main(String[] args) {
        arraySizeChecker();
    }

    public static void arraySizeChecker(){
        int[] bytes=new int[Integer.MAX_VALUE];
    }
}
