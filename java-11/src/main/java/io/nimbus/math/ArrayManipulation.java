package io.nimbus.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayManipulation {

    public void initialisation() {
        int[] evenNumber = {2, 4, 6, 8};

        int[] otherWay = new int[4];
        otherWay[0] = 2;
        otherWay[1] = 4;
        otherWay[2] = 6;
        otherWay[3] = 8;

        System.out.println(Arrays.toString(evenNumber));
        System.out.println(Arrays.toString(otherWay));
    }

    public void matrix(){
        int[][] matrix = new int[4][3]; //number of rows 4 and columns 3
    }

    public void conversion(){
        Integer[] spam = new Integer[] { 1, 2, 3 };
        List<Integer> list = Arrays.asList(spam);

        var backToArray = list.toArray(new Integer[0]);

        System.out.println(Arrays.toString(backToArray));
    }

    public void iteration(){
        int[] evenNumber = {2, 4, 6, 8};
        IntStream stream = Arrays.stream(evenNumber);
        stream.forEach(System.out::println);

        for (int i = 0; i < evenNumber.length; i++) {
            System.out.println(evenNumber[i]);
        }
        for (int x : evenNumber) {
            System.out.println(x);
        }
    }
}
