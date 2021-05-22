# java.lang.OutOfMemoryError: unable to create new native thread

Java supports multithreading so we can create as many threads as we like till we consume all the available memory for the JVM. Once the memory limit is reached, JVM native code can no longer create a new native thread from the underlying operating system. In order to identify the problem, we can take a thread dump and analyze it.

java.lang.OutOfMemoryError: Unable to create new native thread means that the Java application has reached the limit of the number of threads it can start.

Whenever the JVM requests a new thread from the operating system, it cannot create a new native thread. As long as the underlying operating system cannot allocate a new native thread, the OutOfMemoryError will be thrown. The exact limits of native threads are very platform dependent.

<p align='center'>
  <img src='https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_Native_Thread.png'>
</p>

**Formula to calculate the Number of threads:**
```
Number of threads = (MaxProcessMemory - JVMMemory - ReservedOsMemory) / (ThreadStackSize) 
MaxProcessMemory  - Refers to the maximum memory of a process
JVMMemory         - JVM memory
ReservedOsMemory  - Reserved operating system memory
ThreadStackSize   - The size of the thread stack
```
**Example:**
```
MaxProcessMemory is 2G
JVMMemory        is 64M.
ReservedOsMemory is 130M.
ThreadStackSize  is 325K. 

The formula is as follows:
Number of threads = (2 * 1024 * 1024 - 64 * 1024 - 130 *1024 ) / 325 
                  = 5841
```

Common reasons include the following:
* The number of threads exceeds the ulimit limit of the maximum number of threads in the operating system.
* The number of threads exceeds kernel.pid_max (can only be restarted).
* Insufficient native memory.
* The common process of this problem mainly includes the following steps:

The Java program requests the JVM to create a new Java thread. JVM native code proxy the request to create a new native thread for the operating system.

The operating system tries to create a new native thread, and needs to allocate some memory to the thread at the same time.
If the virtual memory of the operating system is exhausted, or is limited by the address space of a 32-bit process (about 2-4GB), the OS will refuse local memory allocation.

JVM will throw **java.lang.OutOfMemoryError: Unable to create new native thread** an error.

**Java Native Memory Tracking**
```
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=detail -XX:+PrintNMTStatistics
```

In high-concurrency application scenarios, if the number of threads created exceeds the system default maximum number of threads, this exception will be thrown. By default, a single Linux process cannot exceed 1024 threads.

```java
public class NativeThreadDemo {
    public static void main(String[] args) {
        runThreads();
    }
    public static void runThreads() {
        int count = 0;
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            System.out.println(count++);
        }
    }
}
```

```sh
javac NativeThreadDemo.java
java NativeThreadDemo
1
2
...

4069
4070
Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
  at java.lang.Thread.start0(Native Method)
  at java.lang.Thread.start(Thread.java:717)
  at NativeThreadDemo.runThreads(NativeThreadDemo.java:18)
  at NativeThreadDemo.main(NativeThreadDemo.java:3)
```
The native thread limit is platform-dependent so it will take a different number of threads in order to reach to the limit.

**Solution:**
1. Allocate more memory to the machine.
2. Reduce the size of Java Heap Space.
```
java -Xms1303m -Xmx1303m -XX:PermSize=256m -XX:MaxPermSize=256m NativeThreadDemo
```
3. Fix the thread leak in the application.
4. Increase the maximum number of OS-level threads.
* view limit the maximum number of threads
```sh
ulimit -a
```
* Adjust the maximum number of threads limit
```sh
ulimit -u <num_threads>
```
5. The capacity of the stack size of each thread reduced by '-Xss' parameter.
6. Limit the size of the thread pool.
