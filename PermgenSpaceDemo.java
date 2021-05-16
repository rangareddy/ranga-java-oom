import java.util.*;

public class PermgenSpaceDemo {
    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        int count=0;
        while(true) {
            list.add(String.valueOf(count++).intern());
            System.out.println(count);
        }
    }
}
