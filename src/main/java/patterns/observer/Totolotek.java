package patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Totolotek implements Observable {

    private static final Random RANDOM = new Random();
    private int[] numbers = new int[7];
    private List<Observer> observers = new ArrayList<Observer>();

    public Totolotek() {
        setRandomNumbers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void updateObservers() {
        for (Observer observer : observers) {
            observer.update(numbers);
        }
    }

    public void setRandomNumbers() {
        this.numbers = IntStream.range(0, numbers.length)
                .mapToObj(l -> RANDOM.nextInt(47))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
