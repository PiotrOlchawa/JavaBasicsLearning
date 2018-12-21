package patterns.observer;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Internet implements Observer {

    private int [] results = new int[7];

    @Override
    public void update(final int[] results) {
        this.results =  IntStream.range(0, results.length)
                .mapToObj(l -> results[l])
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Override
    public String toString() {
        return "Internet{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
