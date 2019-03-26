package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class BasicStream {

    public static void main(String[] args) {


        List<String> list= Arrays.asList("a","b","c");
        IntStream.range(0,list.size())
                .filter(l->list.get(l).equals("b"))
                .map(l-> 1).sum();
        IntStream.iterate(2, n->n+2).limit(100).forEach(System.out::println);

        IntSupplier randomGen = () -> new Random().nextInt(1000);

        Long howmany = IntStream.generate(randomGen)
                .limit(100000)
                .filter(l->l==100)
                .count();
        System.out.println(howmany);





    }
}
