package functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Runner {

    static String myStringObject = "my string object";

    public static void main(String[] args) {

        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> integerList = Arrays.asList(integers);

        Predicate<Integer> predicate = i -> i % 2 == 0;
        Boolean predicateOut = predicate.test(2);
        //Used in stream filter
        integerList.stream().filter(predicate).forEach(System.out::println);
        // or
        integerList.stream().filter(i -> i % 2 == 0).forEach(System.out::println);


        Supplier<String> stringSupplier = () -> new String("my new string");
        Supplier<Double> doubleSupplier = () -> new Random().nextDouble();
        String s = stringSupplier.get();
        // Used in Stream.generate objects
        Stream.generate(doubleSupplier)
                .limit(10)
                .forEach(System.out::println);
        // or
        Stream.generate(new Random()::nextDouble)
                .limit(10)
                .forEach(System.out::println);


        CustomSupplier customSupplier = () -> new String("Hello");

        Function<Integer, String> function = (Integer i) -> i.toString() + " appended string";
        String string = function.apply(1);
        //Used in mapper
        Arrays.stream(integers).map(function).forEach(System.out::println);
        //or
        Arrays.stream(integers).map(i -> i.toString() + " appended string").forEach(System.out::println);


        Consumer<Integer> consumer = i -> System.out.print("Modulo predicate " + i + "\n");
        consumer.accept(1);
        //Used in stream forEach
        integerList.stream().filter(predicate).forEach(consumer);
        // or
        integerList.stream().filter(predicate).forEach(i -> System.out.print("Modulo predicate " + i + "\n"));


    }
}
