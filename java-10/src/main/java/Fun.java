import java.util.List;

public class Fun {
    public static void main(String[] args) {
//        1. Local-Variable Type Inference
        var x = "This String's type was inferred :)";
        System.out.println(x);
        var numbers = List.of(1, 2, 3, 4, 5);
        for (var number : numbers) {
            System.out.println(number);
        }

//      2. Time-Based Release Versioning, $FEATURE.$INTERIM.$UPDATE.$PATCH
        Runtime.Version version = Runtime.version();
        version.feature();
        version.interim();
        version.update();
        version.patch();
        System.out.println(version);
    }
}
