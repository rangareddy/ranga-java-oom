// KillProcessDemo.java
import java.util.*;

public class KillProcessDemo {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList();
        for ( int i = 10000 ; i < 100000 ; i++) {
            try{
                list.add(new int[100_000_000]);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
}
