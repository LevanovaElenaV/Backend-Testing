package lesson3;

//import java.util.stream.IntStream;

import java.util.Arrays;

public class Resolver {
    public int getMin(int[] array) {

//        return IntStream.of(array)
//                .min()
//                .getAsInt();

//        IntStream.of(array)
//                .min()
//                .orElseThrow(() -> new IllegalArgumentException("Array could not be empty"))

        if (array == null || array.length==0 ) {
            throw new IllegalArgumentException("Array could not be empty");
        }

        int min = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }


}


