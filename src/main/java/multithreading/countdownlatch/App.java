package multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class CountDownLatchSample implements Runnable {

    private CountDownLatch countDownLatch;
    private Integer threadId;

    public CountDownLatchSample(CountDownLatch countDownLatch, Integer threadId) {
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;
    }

    @Override
    public void run() {

        try {
            System.out.println("Thread Started: " + threadId);
            Thread.sleep(5000);
            System.out.println("Thread Ended: " + threadId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("CountDownLatch counts:" + countDownLatch.getCount());
    }
}


public class App {

    public static void main(String args[]) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.iterate(0, l -> l + 1)
                .limit(3)
                .forEach(l -> executorService.submit(new CountDownLatchSample(countDownLatch,l)));
        countDownLatch.await();
        System.out.println("All tasks ended");
        executorService.shutdown();

    }


}
