package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaPlayground {

    public static void main(String[] args) {
        System.out.println(Person.N_EYES);
        // custom playground with lists. not lectures based.
        // case 1
        List<List<Integer>> list1 = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6));
        List<Integer> listResult1 = list1
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Before: " + list1); // [[1,2] [3,4] [5,6]]
        System.out.println("After: " + listResult1); // [1, 2, 3, 4, 5, 6]
        // case 2
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> listResult2 = list2
                .stream()
                .flatMap(Stream::of)
                .collect(Collectors.toList());
        System.out.println("Before: " + list2); // [1, 2, 3, 4, 5, 6]
        System.out.println("After: " + listResult2); // [1, 2, 3, 4, 5, 6]

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<String> chars = Arrays.asList("a", "b", "c", "d");
        List<String> combinations = numbers
                .stream()
                .flatMap(i -> chars.stream().map(ch -> "" + ch + i))
                .collect(Collectors.toList());
        System.out.println(combinations);
    }

}

class Person {
    public static final int N_EYES = 2; // class level fiu
}