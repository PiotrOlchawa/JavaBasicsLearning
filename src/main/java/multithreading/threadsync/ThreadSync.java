package multithreading.threadsync;

import java.util.Scanner;

public class ThreadSync extends Thread {

    // volatile guarantee that runned is not cached inside thread run()
    private volatile boolean runned = true;

    @Override
    public void run() {

        while (runned) {
            try {
                Thread.sleep(100);
                System.out.println("Thread is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        runned = false;
    }
}

class App {

    public static void main(String args[]) {
        ThreadSync threadSync = new ThreadSync();
        threadSync.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Enter pressed");
        threadSync.shutdown();
        ;
    }
}

class ThreadAccessingSameData {

    public int count = 0;

    public synchronized void  increment(){
        count++;
    }


    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }
    });

    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }
    });

    Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        }
    });

    Thread thread4 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        }
    });



    public void doWork() throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    public void doSynchronizedWork() throws InterruptedException {
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
    }
}


class App1 {

    //count =0

    // same time
    //1 = count+1;
    //1 = count+1;

    // two iterations count is incremented by 1
    // instead count incremented by two

    public static void main(String args[]) {
        ThreadAccessingSameData threadAccessingSameData = new ThreadAccessingSameData();
        try {
            threadAccessingSameData.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counts: " + threadAccessingSameData.count);

        ThreadAccessingSameData threadAccessingSameDataButSynchronized = new ThreadAccessingSameData();
        try {
            threadAccessingSameDataButSynchronized.doSynchronizedWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counts: " + threadAccessingSameDataButSynchronized.count);

    }
}