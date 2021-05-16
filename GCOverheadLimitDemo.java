import java.util.*;

public class GCOverheadLimitDemo {
    public static void main(String[] args) {
        gcOverheadLimit();
    }
    public static void gcOverheadLimit() {
        int count = 0 ;
        List <Employee> list = new ArrayList<> ();
        while ( true ){
            System.out.println( ++count);
            list.add( new Employee(count, "Ranga "+count, count, count * 0.5f));
        }
    }
}
