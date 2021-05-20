package com.ranga.java.oom;

public class HeapMemoryCrash {
    public static void main(String[] a) {
        int max = 1000;
        Object[] arr = new Object[max];
        heapCheck();
        for (int n=0; n<max; n++) {
            arr[n] = new long[10*1024*128];
            System.out.println((n+1)+": " +((n+1)*10)+" MB of objects created.");
            heapCheck();
        }
    }

    public static void heapCheck() {
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long used = total - free;
        System.out.format("Total memory: %s%n",total);
        System.out.format(" Free memory: %s%n",free);
        System.out.format(" Used memory: %s%n",used);
    }
}
