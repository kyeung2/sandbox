import java.util.List;
import java.util.Map;



public class Fun {


//1.    jshell REPL, /ex to exit

    public static void main(String[] args) {

//2. factory collection static methods, immutable  obviously
        List<String> fromOf = List.of("a", "b", "c");
        System.out.println(fromOf);
        Map<Integer, String> integerStringMap = Map.of(1, "value", 2, "otherValue");
        System.out.println(integerStringMap);

    }

//    4. Jigsaw Project, module system
//    5. reactive streams package ava.util.concurrent.Flow


}
