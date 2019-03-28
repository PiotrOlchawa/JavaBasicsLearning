package multithreading.threadsync;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class ThreadPools implements Runnable {

    private int threadId;

    public ThreadPools(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread with id " + threadId + " started");
            Thread.sleep(5000);
            System.out.println("Thread with id " + threadId + "  ended");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args []) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.iterate(0,l->l+1).limit(10).forEach(l-> executorService.submit(new ThreadPools(l)));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

}
