// DirectBufferMemoryDemo.java
// JVM Parameters: -XX:MaxDirectMemorySize=5m

package com.ranga.java.oom;
import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {

        long maxDirectMemory = sun.misc.VM.maxDirectMemory() / 1024 / 1024;
        System.out.println("Configured maxDirectMemory: " + maxDirectMemory + " MB");

        // -XX:MaxDirectMemorySize=5M The local memory configuration is 5MB, and the actual use here is 6MB
        int numBytes = 6 * 1024 * 1024;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(numBytes);
        // ByteBuffer byteBuffer = ByteBuffer.allocate(numBytes);

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);           // Write data to DirectBuffer
            }
            byteBuffer.flip();
            for (int j = 0; j < 99; j++) {
                byteBuffer.get();               // Read data from DirectBuffer
            }
            byteBuffer.clear();
        }
    }
}
