# java.lang.OutOfMemoryError: Metaspace

> Java class metadata is allocated in native memory called metaspace.
> 
The **java.lang.OutOfMemoryError: Metaspace** indicates that the amount of **native memory** allocated for **Java class metadata is exausted**.

The amount of metaspace that can be used for class metadata is limited by the parameter **MaxMetaSpaceSize**, which is specified on the command line. When the amount of native memory needed for a class metadata exceeds MaxMetaSpaceSize, a java.lang.OutOfMemoryError exception with a detail MetaSpace is thrown.

Since this is the replacement of the permanent generation region, the Metaspace region now stores the information of permanent generation. It is used to store things like the declarations, including name and fields of the class, methods with their bytecode, object arrays, constant pool information and JIT compiler optimization of the loaded classes.

Starting from Java 8, the memory model in Java was significantly changed. A new memory area called Metaspace was introduced and Permgen was removed. This change was made due to variety of reasons, including but not limited to:

The required size of permgen was hard to predict. It resulted in either under-provisioning triggering lang.OutOfMemoryError: Permgen sizeerrors or over-provisioning resulting in wasted resources.

GC performanceimprovements, enabling concurrent class data de-allocation without GC pauses and specific iterators on metadata
Support for further optimizations such as G1concurrent class unloading.

<p align='center'>
  <img src='https://github.com/rangareddy/ranga-java-oom/blob/main/images/OOM_MetaSpace.jpeg'>
</p>

The main cause for the **java.lang.OutOfMemoryError: Metaspace** is
* Too many class are loaded or
* Classes loaded very huge in size.

**Checking MetaSpace capacity:**
```sh
jstat -gcmetacapacity (PID)  
jstat -gcmetacapacity 11236
   MCMN       MCMX        MC       CCSMN      CCSMX       CCSC     YGC   FGC    FGCT     GCT
      0.0  1110016.0    69208.0        0.0  1048576.0     9088.0   132     4    0.564    2.373
```
* MCMN: Minimum metaspace capacity (kB).
* MCMX: Maximum metaspace capacity (kB).
* **MC: Metaspace capacity (kB)**.
* CCSMN: Compressed class space minimum capacity (kB).
* CCSMX: Compressed class space maximum capacity (kB).
* YGC: Number of young generation GC events.
* FGC: Number of full GC events.
* FGCT: Full garbage collection time.
* GCT: Total garbage collection time.

**Monitoring MetaSpace Size with Java Native Memory tracking**
```sh
-XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=detail -XX:+PrintNMTStatistics
```

Hence, this type of error is thrown by JVM for having a large number of big classes. The following example uses javassist package from the link: http://jboss-javassist.github.io/javassist/ which enables Java bytecode manipulation.

```java
// MetaspaceDemo.java
// JVM Parameters: -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=64M
// javac -cp javassist.jar MetaspaceDemo.java
// java -cp .:javassist.jar MetaspaceDemo

package com.ranga.java.oom;

import javassist.ClassPool;

public class MetaspaceDemo {
    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        for (int i = 0; i < 70000; i++) {
            Class claz = classPool.makeClass("MetaspaceDemo " + i).toClass();
            System.out.println(claz.getName());
        }
    }
}
```

```sh

$ javac -cp javassist.jar MetaspaceDemo.java
$ java -cp .:javassist.jar MetaspaceDemo

MetaspaceDemo 1
MetaspaceDemo 2
MetaspaceDemo 3
..
..
MetaspaceDemo 68664
MetaspaceDemo 68665

Exception in thread "main" javassist.CannotCompileException: by java.lang.OutOfMemoryError: Metaspace
	at javassist.ClassPool.toClass(ClassPool.java:1085)
	at javassist.ClassPool.toClass(ClassPool.java:1028)
	at javassist.ClassPool.toClass(ClassPool.java:986)
	at javassist.CtClass.toClass(CtClass.java:1079)
	at com.ranga.java.oom.MetaspaceDemo.main(MetaspaceDemo.java:14)
Caused by: java.lang.OutOfMemoryError: Metaspace
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:642)
	at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at javassist.ClassPool.toClass2(ClassPool.java:1098)
	at javassist.ClassPool.toClass(ClassPool.java:1079)
	... 4 more
```

This code will keep generating new classes and loading their definitions to Metaspace until the space is fully utilized and the **java.lang.OutOfMemoryError: Metaspace** is thrown. When launched with **-XX:MaxMetaspaceSize=64m** then on Mac OS X my Java 1.8.0_05 dies at around 70,000 classes loaded.

The above code will continue to generate new classes at runtime and load their definitions into Metaspace until the space is fully utilized and java.lang.OutOfMemoryError: Metaspace is thrown.

**Solution:**
1.Increase the Metaspace size using '-XX:MetaspaceSize' and '-XX:MaxMetaSpaceSize' parameters.
```sh
java -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=256M Metaspace
```
2. Remove the limitation on the size of Metaspace by deleting this parameter
```sh
'-XX:MaxMetsSpaceSize'
```
3. Reducing the size of the Java heap will make more space available for MetaSpace.
4. Allocate more memory to the server.
5. Could be bug in application and needs to be Fix it.
