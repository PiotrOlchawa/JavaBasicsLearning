package multithreading.interruptingthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterruptedThread {

    static Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while(true){
                System.out.println("Thread");
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread Interrupted");
                break;
                }
            }
        }
    };

    static ExecutorService executorService = Executors.newCachedThreadPool();


    public static void main(String args[]) throws InterruptedException {
        InterruptedThread.executorService.submit(InterruptedThread.runnable);

        Thread.sleep(1000);

        executorService.shutdown();
        executorService.shutdownNow();


    }




}
