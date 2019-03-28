package multithreading.blockingqueue;

import java.util.Random;
import java.util.concurrent.*;

public class BlockingQueueDemo {

    public static void main(String args[]) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(10);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new QueueConsumer(blockingQueue));
        executorService.submit(new QueueProducer(blockingQueue));
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}


class QueueConsumer implements Runnable {


    private static final Random RANDOM = new Random();
    private BlockingQueue<Integer> integerBlockingQueue;

    public QueueConsumer(BlockingQueue<Integer> integerBlockingQueue) {
        this.integerBlockingQueue = integerBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                if (RANDOM.nextInt(10) == 0) {
                    System.out.println("Value Taken: " + integerBlockingQueue.take()
                            + " Queue size: " + integerBlockingQueue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class QueueProducer implements Runnable {

    private static final Random RANDOM = new Random();
    private BlockingQueue<Integer> integerBlockingQueue;

    public QueueProducer(BlockingQueue<Integer> integerBlockingQueue) {
        this.integerBlockingQueue = integerBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(750);
                integerBlockingQueue.put(RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}