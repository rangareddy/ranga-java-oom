// MetaspaceDemo.java
// JVM Parameters: -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=256M
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
