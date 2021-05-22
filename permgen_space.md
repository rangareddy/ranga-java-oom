# java.lang.OutOfMemoryError: PermGen space

Java memory is separated into different regions:

<p align="center">
  <img src="https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_Permgen.png">
</p>

The size of all those regions, including the permgen area, are set during the JVM startup. If we do not set the size ourself, the platform-specific default value will be used.

The **java.lang.OutOfMemoryError: PermGen space** message indicates that the **Permanent Generation’s area in memory is exhausted**.

The main cause for this error is that either **too many classes or too big classes are loaded to the permanent generation**. 

The possible reasons are as follows:
1. Before Java7, the String.intern() method was frequently used incorrectly.
2. A large number of proxy classes were generated during the operation, which caused the method area to burst and could not be uninstalled.
3. The application was running for a long time without restarting.

This is applicable to JVM 7 and earlier. Java 8 has changed the memory model. This newer model does not have permanent generation region, which is replaced by Metaspace region. 

```java
import java.util.*;

public class PermgenSpaceDemo {
    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        int count=0;
        while(true) {
            list.add(String.valueOf(count++).intern());
            System.out.println(count);
        }
    }
}
```

```sh
$ javac PermgenSpaceDemo.java
$ java -XX:PermSize=2M -XX:MaxPermSize=5M PermgenSpaceDemo
```

In the above sample code, code iterates over a loop and generates classes at run time. Class generation complexity is being taken care of by the Javassist library.
Running the above code will keep generating new classes and loading their definitions into Permgen space until the space is fully utilized and the java.lang.OutOfMemoryError: Permgen space is thrown.

**Solution:**
1. Increase the PermGen size 'XX:PermSize' and 'XX:MaxPermSize'
```sh
java -XX:PermSize=64M -XX:MaxPermSize=256M PermgenSpaceDemo
```
2. Application redeployment without restarting can cause this issues. So restart JVM.
