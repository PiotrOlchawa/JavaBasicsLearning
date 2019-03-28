package multithreading.lowlevelprocessor;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private static final Random RANDOM = new Random();
    private LinkedList linkedListQueue = new LinkedList();
    private Object linkedListQueueLock = new Object();
    private static final int queueMaxSize = 10;
    private int count = 0;


    void produce() throws InterruptedException {
        while (true) {
            synchronized (linkedListQueueLock) {
                while (linkedListQueue.size() == queueMaxSize) {
                    System.out.println("Queue is Full: Waiting");
                    linkedListQueueLock.wait();
                }
                linkedListQueue.add(count++);
                System.out.println("Producing new Value to Queue: " + count
                        + " Queue Size: " + linkedListQueue.size());
                linkedListQueueLock.notify();
            }
            Thread.sleep(RANDOM.nextInt(1000));
        }
    }

    void consume() throws InterruptedException {
        Thread.sleep(3000);
        while (true) {
            synchronized (linkedListQueueLock) {
                while (linkedListQueue.size() == 0) {
                    System.out.println("Queue if Empty: Waiting");
                    linkedListQueueLock.wait();
                }
                System.out.println("Consuming First Value From Queue: "
                        + linkedListQueue.getFirst() + " Queue Size: " + linkedListQueue.size());
                linkedListQueue.removeFirst();
                linkedListQueueLock.notify();
            }
            Thread.sleep(RANDOM.nextInt(1000));
        }
    }
}


class App {

    public static void main(String args[]) throws InterruptedException {

        Processor processor = new Processor();

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }
}
