// DirectBufferMemoryDemo.java
// JVM Parameters: -XX:MaxDirectMemorySize=8m -XX:+PrintGCDetails

package com.ranga.java.oom;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {

        long maxDirectMemory = sun.misc.VM.maxDirectMemory() / (1024 * 1024);
        System.out.println("Configured maxDirectMemory <" + maxDirectMemory + "> MB");
        System.out.println();

        // -XX:MaxDirectMemorySize=8M The local memory configuration is 8MB, and the actual use here is 10MB
        int capacity = 10 * 1024 * 1024; // 10 MB
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(capacity);
        // ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        Employee employee = new Employee(1l, "Ranga Reddy", 32, 10000);

        byteBuffer.putLong(employee.getId());
        //byteBuffer.put(employee.getName().getBytes());
        byteBuffer.putInt(employee.getAge());
        byteBuffer.putFloat(employee.getSalary());

        // flip the buffer which set the limit to current position, and position to 0.
        byteBuffer.flip();

        long id = byteBuffer.getLong();
        System.out.println("Id\t\t:\t"+id );

        int age = byteBuffer.getInt();
        System.out.println("Age\t\t:\t"+age);

        float salary = byteBuffer.getFloat();
        System.out.println("Salary\t:\t"+salary);

        byteBuffer.clear(); //make buffer ready for writing

        System.out.println();
        System.out.println("Done!!");
    }
}
