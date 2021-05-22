# java.lang.OutOfMemoryError: Kill process or sacrifice child

Linux manages operating system-related resources in units of processes. There is a memory worker thread called "Out of memory killer" in Linux. Generally, when the operating system detects a low memory situation, it will activate Out of memory killer to kill memory take up a very large user process.

Unlike other OOM errors, Kill process or sacrifice child errors are not triggered at the JVM level, but at the operating system level.

This error is thrown by the operating system. The operating system has processes which are governed by various kernel jobs. One of these jobs is out of memory killer. This job monitors the low memory situation and kills the rogue process which is consuming more memory resources. See the following example, which runs a loop and adds int array to a list. Eventually, you will get this error. Please note that you may need to tweak the swap file and heap sizes on your system.

The OutOfMemoryError : kill process or sacrifice occurs when one of the process consumes too much virtual memory and makes OS unstable, than OS decides to kills that process.

<p align='center'>
  <img src='https://camo.githubusercontent.com/ae8a52c3f98af80a83e6f837623073a711d80ef13b8a1170185bf0cbc9624ba9/68747470733a2f2f68617269746962636f626c6f672e66696c65732e776f726470726573732e636f6d2f323031362f31312f63617074757265372e706e67'/>
</p>

```java
import java.util.*;

public class KillProcessDemo {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList();
        processKillLoop(list);
    }

    private static void processKillLoop(List<int[]> list) {
        for (int i = 1; ; i++) {
            try{
                list.add(new int[100_000_000]);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
}
```

```sh
$ javac KillProcessDemo.java
$ java KillProcessDemo
```
Unlike all other OOM errors, this is not triggered by JVM. But by OS.

**Solution:**
1. Increasing the swap space can solve this OutOfMemoryError.
```sh
swapoff -a 
dd if =/dev/zero of=swapfile bs=1024 count=655360 
mkswap swapfile 
swapon swapfile
```
2. Migrate process to different machine.
3. Add more memory to machine
4. OOM Killer tuning
