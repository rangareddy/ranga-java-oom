# java.lang.OutOfMemoryError: Requested array size exceeds VM limit

Java has got a limit on the maximum array size your program can allocate. The exact limit is platform-specific but is generally some where between 1 and 2.1 billion elements.

Requested array size exceeds VM limit indicates that indicates that the application (or APIs used by that application) attempted to allocate an array that is larger than the heap size. For example, if an application attempts to allocate an array of 1024 MB but the maximum heap size is 512 MB then OutOfMemoryError will be thrown with “Requested array size exceeds VM limit”.

<p align='center'>
  <img src='https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_Requested_ArraySize.png'/>
</p>

In most cases the problem is either a configuration issue (heap size too small), or a bug that results in an application attempting to create a huge array, for example, when the number of elements in the array are computed using an algorithm that computes an incorrect size.

```java
// ArraySizeExceedsLimitDemo.java
// JVM Parameters: -Xms10m -Xmx10m

package com.ranga.java.oom;

public class ArraySizeExceedsLimitDemo {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] bytes = new int[MAX_ARRAY_SIZE];
    }
}
```
The maximum positive int in Java is 2^31 – 1 = 2,147,483,647. And the platform-specific limits can be really close to this number.
```sh
$ javac ArraySizeExceedsLimitDemo.java
$ java -Xms10m -Xmx10m ArraySizeExceedsLimitDemo
Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	at com.ranga.java.oom.ArraySizeExceedsLimitDemo.main(ArraySizeExceedsLimitDemo.java:10)
```

The java.lang.OutOfMemoryError: Requested array size exceeds VM limit can appear as a result of either of the following situations:
1. Your arrays grow too big and end up having a size between the platform limit and the Integer.MAX_INT
2. You deliberately try to allocate arrays larger than 2^31-1 elements to experiment with the limits.

**Solution:**
1. Increase heap size '-Xmx'
2. Fix bug in application code attempting to create a huge array.
