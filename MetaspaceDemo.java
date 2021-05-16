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