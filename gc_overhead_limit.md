# java.lang.OutOfMemoryError: GC Overhead limit exceeded

java.lang.OutOfMemoryError: GC Overhead limit exceeded occurs when garbage collector has reached its overhead limit. This means, it is running all the time, but is very slow in collecting objects.

java.lang.OutOfMemoryError: GC Overhead limit exceeded indicates that the garbage collector is running all the time and Java program is making very slow progress. After a GC (garbage collection), if the garbage collector is spending more than 98% of its time doing garbage collection and if it is recovering less than 2% of the heap and has been doing so far the last 5 (compile time constant) consecutive garbage collections, then a java.lang.OutOfMemoryError is thrown.

<p align='center'>
   <img src='https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_GC_OverHead.png'>
</p>

This error can be thrown from the Serial, Parallel or Concurrent, G1 collectors.

```java
// GCOverheadLimitDemo.java
// JVM Parameters: -Xms10m -Xmx10m -XX:+UseParallelGC

import java.util.*;
public class GCOverheadLimitDemo {
   public static void main(String[] args) {
       int count = 0 ;
       List <Employee> list = new ArrayList<> ();
       while ( true ){
           System.out.println( ++count);
           list.add( new Employee(count, "Ranga "+count, count, count * 0.5f));
       }
   }
}
```
**Compile and Run:**
```sh
javac GCOverheadLimitDemo.java
java -Xms10m -Xmx10m -XX:+UseParallelGC GCOverheadLimitDemo
```
**Output:**
```java
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
  at java.lang.Integer.toString(Integer.java:401)
  at java.lang.String.valueOf(String.java:3099)
  at java.io.PrintStream.print(PrintStream.java:597)
  at java.io.PrintStream.println(PrintStream.java:736)
  at GCOverheadLimitDemo.gcOverheadLimit(GCOverheadLimitDemo.java:11)
  at GCOverheadLimitDemo.main(GCOverheadLimitDemo.java:5)
```
**Solution:**
1. Increase the Heap size using '-Xmx'.
```sh
java -Xmx1024m GCOverheadLimitDemo
```
2. Fix the memory leak in the application.
3. GC Overhead limit exceeded can be turned off with '-XX:-UseGCOverheadLimit'. In fact, this parameter can't solve the memory problem. It just delays the wrong information, and eventually java.lang.OutOfMemoryError: Java heap space appears.
```sh
java -Xmx1024m -XX:-UseGCOverheadLimit GCOverheadLimitDemo
```
