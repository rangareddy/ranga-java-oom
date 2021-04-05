package com.ranga.oom;

import javassist.CannotCompileException;
import javassist.ClassPool;

public class Metaspace {
    public static void main(String args[]) throws Exception
    {
        for (int i = 0; i <100000000 ; i++) {
            Class claz = generate("com.ranga.oom.Metaspace" + i);
        }
    }

    public static Class generate(String name) throws CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        return classPool.makeClass(name).toClass();
    }
}