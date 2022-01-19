package io.nimbus.math;

/**
 * https://www.baeldung.com/java-array-permutations
 */
public class Permutations<T> {

    public static void main(String[] args) {

        var permutation = new Permutations<Integer>();


//        Integer[] elements = {3, 4, 7};
//        permutation.printAllRecursive(elements.length, elements);


        permutation.permutation("abc");
    }


    void permutation(String str) {

        permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }


    //TODO this is a famous Heap's algorithm
    // https://en.wikipedia.org/wiki/Heap%27s_algorithm
    public void printAllRecursive(
            int n, T[] elements) {

        if (n == 1) {
            printArray(elements);
        } else {
            for (int i = 0; i < n - 1; i++) {
                printAllRecursive(n - 1, elements);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            printAllRecursive(n - 1, elements);
        }
    }

    private void swap(T[] input, int a, int b) {
        T tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private void printArray(T[] input) {
        System.out.print('\n');
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }
}
