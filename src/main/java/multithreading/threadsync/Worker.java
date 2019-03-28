package multithreading.threadsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Worker {

    Object lock1 = new Object();
    Object lock2 = new Object();

    private Random random = new Random();
    private List<Integer> integers1 = new ArrayList<>();
    private List<Integer> integers2 = new ArrayList<>();

    // instead using lock on object  private synchronized void work1() throws InterruptedException {
    // may use lock on synchronized block with locking object
    private void work1() {
        synchronized (this.lock1) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integers1.add(random.nextInt(100));
        }
    }

    // instead using lock on object  private synchronized void work2() throws InterruptedException {
    // may use lock on synchronized block with locking object
    private synchronized void work2() throws InterruptedException {
        synchronized (this.lock2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integers2.add(random.nextInt(100));
        }
    }

    public void doAllWorks() throws InterruptedException {
        IntStream.range(0,1000).forEach(l->{
            try {
                work1();
                work2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}


class App3 {

    public static void main(String args[]) throws InterruptedException {

        Worker worker = new Worker();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.doAllWorks();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.doAllWorks();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        long currentTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - currentTime));

    }
}