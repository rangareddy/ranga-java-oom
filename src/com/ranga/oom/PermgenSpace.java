package com.ranga.oom;

import javassist.ClassPool;

public class PermgenSpace {
    static ClassPool classPool = ClassPool.getDefault();
    public static void main(String args[]) throws Exception
    {
        permgenSpace();
    }

    private static void permgenSpace() throws Exception {
        for (int i = 0; i < 1000000000; i++) {
            Class c = classPool.makeClass("com.ranga.oom.PermgenSpace " + i).toClass();
            System.out.println(c.getName());
        }
    }
}
