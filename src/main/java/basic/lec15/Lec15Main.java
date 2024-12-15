package basic.lec15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lec15Main {

    public static void main(String[] args) {
        int[] array = {100, 200};

        for (int i = 0; i < array.length; i++) {
            System.out.printf("%s %s", i, array[i]);
        }

        final List<Integer> numbers = Arrays.asList(100, 200);

        System.out.println(numbers.get(0));

        for (Integer number : numbers) {
            System.out.println(number);
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.printf("%s %s\n", i, numbers.get(i));
        }
    }
}
