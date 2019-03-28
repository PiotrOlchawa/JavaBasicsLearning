package multithreading.reentrandlock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

    private static final Random RANDOM = new Random();
    private static final int MAX_QUEUE_SIZE = 10;
    private Lock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    private Queue<Integer> queue = new ArrayDeque();

    public void producer() throws InterruptedException {

        while (true) {
            reentrantLock.lock();
            if (queue.size() == MAX_QUEUE_SIZE) {
                System.out.println("Queue is Full: Waiting");
                condition.await();
            }
            System.out.println("Adding to Queue ");
            try { //try finally block should exist there because if we thrown exception thread will stop and hang
                queue.offer(RANDOM.nextInt(100));
                System.out.println("Queue size: " + queue.size());
                condition.signal();
            } finally {
                reentrantLock.unlock();
            }
            Thread.sleep(RANDOM.nextInt(100));
        }
    }

    public void consumer() throws InterruptedException {
        Thread.sleep(2000);

        while (true) {
            reentrantLock.lock();
            if (queue.isEmpty()) {
                System.out.println("Queue is Empty: Waiting");
                condition.await();
            }

            try { //try finally block should exist there because if we thrown exception thread will stop and hang
                System.out.println("Pooling from queue element: " + queue.poll());
                condition.signal();
            } finally {
                reentrantLock.unlock();

            }
            Thread.sleep(RANDOM.nextInt(100));
        }
    }
}


class App {

    public static void main(String args[]) throws InterruptedException {


        Processor processor = new Processor();

        Thread producingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

/*      producingThread.start();
        consumingThread.start();

        producingThread.join();
        consumingThread.join();*/

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(producingThread);
        executorService.submit(consumingThread);
        executorService.shutdown();
    }

}