# java.lang.OutOfMemoryError: Java heap space

When the application loads up, Java heap space is created. If for some reason our application is not making old unused objects available to the garbage collector, all these objects are going to consume this limited available space leaving no space for new objects. Hence, when a new object creation request comes in, JVM throws the OutOfMemoryError exception.

The application tries to add more data into the heap space area, but there is not enough space for it. There may be a lot of physical memory available, but when the JVM reaches the heap size limit, it will cause a Java heap space error.

Java memory is divided into two different regions, these regions are called Heap (for Heap space) and Permgen (for Permanent Generation):

<p align='center'>
   <img src='https://camo.githubusercontent.com/96de2c8147afa199563d478e54796d152250e988c2368a26a4de4788dcb11af3/68747470733a2f2f7469616e6d696e6778696e672e636f6d2f696d616765732f6a6176612d6c616e672d6f75746f666d656d6f72796572726f722d6a6176612d686561702d73706163652e706e67'>   
</p>

The size of these regions is set during the startup of the Java Virtual Machine (JVM) and can be customized by specifying the JVM parameters -Xmx and -XX:MaxPermSize. If the size is not explicitly set, the platform-specific default value will be used.

```java
// JavaHeapSpaceDemo.java
// JVM Parameters: -Xms10m -Xmx10m

public class JavaHeapSpaceDemo {
   public static void main(String[] args) {
       Integer[] array = new Integer[1000*1000*100];
       System.out.println("Successful");
   }
}
```
**Compile and Run**:
```sh
javac JavaHeapSpaceDemo.java
java -Xms10m -Xmx10m JavaHeapSpaceDemo
```
**Output:**
```java
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
  at JavaHeapSpaceDemo.javaHeapSpace(JavaHeapSpaceDemo.java:10)
  at JavaHeapSpaceDemo.main(JavaHeapSpaceDemo.java:7)
```
## Solution:
1. Increase the Heap size using '-Xms' and '-Xmx' jvm parameters.
```sh
java -Xms1024m -Xmx1024m JavaHeapSpaceDemo
```
2. Fix the memory leak in the application.

