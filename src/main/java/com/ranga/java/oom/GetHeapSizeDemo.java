// GetHeapSizeDemo.java
// java GetHeapSizeDemo
// java -Xmx128M GetHeapSizeDemo
// java -Xms16M -Xmx128M GetHeapSizeDemo

package com.ranga.java.oom;

public class GetHeapSizeDemo {

    private static final long MEGA_BYTE = 1024 * 1024; // 1MB = 1048576 = (1024 KB = 1024 * 1024 B)

    // Runtime instance
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        printMemory();
    }

    public static void printMemory() {
    // Memory which is currently available for use by heap
        long totalMemoryInMB = runtime.totalMemory() / MEGA_BYTE;
        System.out.println("Total Memory  : " + totalMemoryInMB + " MB"); // initial heap size

        // Maximum memory which can be used if required. The heap cannot grow beyond this size
        // Any attempt will result in an OutOfMemoryException.
        long maxMemoryInMB = runtime.maxMemory() / MEGA_BYTE;
        System.out.println("Max Memory    : " + maxMemoryInMB + " MB");   // maximum heap size

        // Free memory still available
        long freeMemoryInMB = runtime.freeMemory() / MEGA_BYTE;
        System.out.println("Free Memory   : " + freeMemoryInMB + " MB");          // free heap size 

        // Memory currently used by heap
        long usedMemoryInMB = totalMemoryInMB - freeMemoryInMB;
        System.out.println("Used Memory   : " + usedMemoryInMB + " MB");  // used heap size
    }
}
