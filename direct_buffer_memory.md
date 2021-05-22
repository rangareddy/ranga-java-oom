# java.lang.OutOfMemoryError: Direct buffer memory

Java has allowed applications to access memory outside of the heap (in a limited way) since JDK 1.4, using **DirectByteBuffer**.

Java 8 has NIO (New Input/Output), cache, channel, and selector. When writing NIO programs, ByteBuffer is often used to read or write data. This is an I/0 method based on channels (Channel) and buffers (Buffer). It can use the **Native function library** to **directly allocate off-heap memory**, then operate as a reference to this memory through a DirectByteBuffer stored in Java. This can **significantly improve performance in some scenarios** because they can **avoid copying data between the java heap and the native heap**.

Direct ByteBuffer default size is **64 MB**, Once the DirectByte buffer limit is exceeded, it will throw an **java.lang.OutOfMemoryError: Direct buffer memory** error.

When creating a **ByteBuffer** object, we can choose to allocate memory from the **JVM heap** or from the **OS local memory**. Because the local buffer avoids buffer copying, it has certain advantages over the heap buffer in terms of performance, but it also has some drawbacks.

```java
public abstract class ByteBuffer extends Buffer implements Comparable<ByteBuffer> {
    
    public static ByteBuffer allocate(int capacity) {

    }
    public static ByteBuffer allocateDirect(int capacity) {

    }
   
}
```
1. **ByteBuffer.allocate(int capability)** - Used to allocate JVM heap memory, which belongs to the jurisdiction of GC, and the speed is relatively slow due to the need to copy.
2. **ByteBuffer.allocateDirect(int capability)** - Used to allocate OS local memory, which is not under the jurisdiction of GC. Since memory copy is not required, the speed is relatively fast.

The following figure shows the difference between direct and indirect ByteBuffer objects:

<p align="center">
  <img src="https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_DirectBuffer.gif">
</p>

The direct buffer (mainly DirectByteBuffer) in Java NIO is divided into two parts:
```sh
                  java       |      native   
                             |
         DirectByteBuffer    |     malloc'd
           [    address   ] -+-> [   data    ]
                             |
```
**DirectByteBuffer** itself is a Java object in the Java heap; and this object has a long type field address, which records a piece of native memory requested by calling **malloc()**.
```java
class DirectByteBuffer extends MappedByteBuffer  implements DirectBuffer {

    // Primary constructor
    DirectByteBuffer(int cap) { // package-private

    }
}
```

**Java Native Memory Tracking**
```
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=detail -XX:+PrintNMTStatistics
```

The off-heap memory size limit can be determined by specifying JVM parameters
```sh
-XX:MaxDirectMemorySize=512m
```

```java
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
```

```sh
$ javac DirectBufferMemoryDemo.java
$ java -XX:MaxDirectMemorySize=8m DirectBufferMemoryDemo
Configured maxDirectMemory <8> MB

Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	at java.nio.Bits.reserveMemory(Bits.java:694)
	at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
	at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
	at com.ranga.java.oom.DirectBufferMemoryDemo.main(DirectBufferMemoryDemo.java:17)
```

```sh
$ java -XX:MaxDirectMemorySize=10m DirectBufferMemoryDemo
Configured maxDirectMemory <10> MB

Id		:	1
Age		:	32
Salary		:	10000.0

Done!!
```
**Solution:**

1. Increase the Direct Max Memory **'-XX:MaxDirectMemorySize'** parameter value.
```sh
-XX:MaxDirectMemorySize=256M
```
2. Check whether nio is used directly or indirectly, such as manually calling the method of generating buffer or using nio containers such as netty, jetty, tomcat, etc.
3. JVM parameters to check whether there are **'-XX:+DisableExplicitGCoptions'**, if there is to be removed, because the parameters will System.gc() fail.
```sh
-XX:+DisableExplicitGC
```