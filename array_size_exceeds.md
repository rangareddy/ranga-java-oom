# java.lang.OutOfMemoryError: Requested array size exceeds VM limit

Java has got a limit on the maximum array size your program can allocate. The exact limit is platform-specific but is generally some where between 1 and 2.1 billion elements.

Requested array size exceeds VM limit indicates that indicates that the application (or APIs used by that application) attempted to allocate an array that is larger than the heap size. For example, if an application attempts to allocate an array of 1024 MB but the maximum heap size is 512 MB then OutOfMemoryError will be thrown with “Requested array size exceeds VM limit”.

<p align='center'>
  <img src="https://camo.githubusercontent.com/7207fdefa44df44bf626a347b04f054ff9571009738c281f60928d91deda03db/68747470733a2f2f68617269746962636f626c6f672e66696c65732e776f726470726573732e636f6d2f323031362f31312f63617074757265362e706e67"/>
</p>

In most cases the problem is either a configuration issue (heap size too small), or a bug that results in an application attempting to create a huge array, for example, when the number of elements in the array are computed using an algorithm that computes an incorrect size.
```java
package com.ranga.oom;

public class ArraySizeExceedsLimit {
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    public static void main(String[] args) {
        arraySizeChecker();
    }

    public static void arraySizeChecker(){
        int[] bytes=new int[MAX_ARRAY_SIZE];
    }
}
```
The maximum positive int in Java is 2^31 – 1 = 2,147,483,647. And the platform-specific limits can be really close to this number.
```sh
$ javac ArraySizeExceedsLimitDemo.java
$ java -Xms10m -Xmx10m ArraySizeExceedsLimitDemo
Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
  at ArraySizeExceedsLimitDemo.arraySizeChecker(ArraySizeExceedsLimitDemo.java:8)
  at ArraySizeExceedsLimitDemo.main(ArraySizeExceedsLimitDemo.java:4)
```

The java.lang.OutOfMemoryError: Requested array size exceeds VM limit can appear as a result of either of the following situations:
1. Your arrays grow too big and end up having a size between the platform limit and the Integer.MAX_INT
2. You deliberately try to allocate arrays larger than 2^31-1 elements to experiment with the limits.

**Solution:**
1. Increase heap size '-Xmx'
2. Fix bug in application code attempting to create a huge array.
