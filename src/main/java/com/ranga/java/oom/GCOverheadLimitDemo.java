// GCOverheadLimitDemo.java
// JVM Parameters: -Xms10m -Xmx10m -XX:+UseParallelGC

package com.ranga.java.oom;

import java.util.ArrayList;
import java.util.List;

public class GCOverheadLimitDemo {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        for(int count=0; count<1000000; count++) {
            System.out.println(++count);
            long id = count;
            String name = "Ranga " + id;
            int age = count > 100 ? count % 100 : count;
            float salary = count * 0.05f;
            Employee employee = new Employee(id, name, age, salary);
            list.add(employee);
        }
    }
}