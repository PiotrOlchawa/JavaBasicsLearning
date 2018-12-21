package patterns.observer;

public class Runner {

    public static void main(String[] args) {
        Totolotek totolotek = new Totolotek();
        Internet internet = new Internet();
        Tv tv = new Tv();

        totolotek.addObserver(internet);
        totolotek.addObserver(tv);

        totolotek.updateObservers();

        System.out.println(internet.toString());
        System.out.println(tv.toString());
    }
}
