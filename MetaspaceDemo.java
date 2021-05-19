// MetaspaceDemo.java
// JVM Parameters: -XX:MetaspaceSize=64M -XX:MaxMetaspaceSize=256M
// javac -cp javassist.jar MetaspaceDemo.java
// java -cp .:javassist.jar MetaspaceDemo

import javassist.CannotCompileException;
import javassist.ClassPool;

public class MetaspaceDemo {
    public static void main(String args[]) throws Exception
    {
        ClassPool classPool = ClassPool.getDefault();
        for (int i = 0; i <100000000 ; i++) {
            Class claz = classPool.makeClass("MetaspaceDemo" + i).toClass();
        }
    }
}
