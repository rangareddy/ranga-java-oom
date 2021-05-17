import java.util.*;
// JVM params -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
public class JavaHeapSpaceDemo {
    private final static int SIZE = 1024 * 1024 * 1024;
    public static void main(String[] args) {
        javaHeapSpace();
    }
    public static void javaHeapSpace() {
        Integer[] array = new Integer[SIZE];
    }
}
