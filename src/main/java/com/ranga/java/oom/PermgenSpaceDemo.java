// PermgenSpaceDemo.java
// JVM Parameters: -XX:PermSize=2M -XX:MaxPermSize=5M

package com.ranga.java.oom;

import java.util.ArrayList;
import java.util.List;

public class PermgenSpaceDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int count = 0;
        while (true) {
            list.add(String.valueOf(count++).intern());
            System.out.println(count);
        }
    }
}