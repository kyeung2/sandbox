package io.nimbus;

public class Fun {
    public static void main(String[] args) {
//        first LTS after Java8, Oracle JDK need commercial license, alternatives AdoptOpenJDK, Azul, IBM, Red Hat

//    1. can implicitly run code without `javac`, just `java`

        var myString = "";
//    2. some utility methods added to String
        System.out.println(myString.isBlank());
        var myString2 = "aaa\nbbb\nccc";
        myString2.lines().forEach(System.out::println);


//     3.  Local-Variable inference for Lambda function parameters
        MyAdding adds = (var a, var b) -> a + b; //could do Integer::sum ha.
    }

    interface MyAdding {
        int add(int a, int b);
    }
}
