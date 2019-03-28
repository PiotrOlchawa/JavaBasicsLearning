package multithreading.creatingthreads;

public class RunnerExtendsThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(100);
                System.out.println("Runner loop: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class App1 {

    public static void main(String args[]) {
        RunnerExtendsThread runner1 = new RunnerExtendsThread();
        runner1.start();

        RunnerExtendsThread runner2 = new RunnerExtendsThread();
        runner2.start();
    }
}

class Runner1ImplementsRunnable implements Runnable {


    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(100);
                System.out.println("Runner loop: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class App2 {

    public static void main(String args[]) {
        Runner1ImplementsRunnable runner1ImplementsRunnable = new Runner1ImplementsRunnable();
        Thread thread = new Thread(runner1ImplementsRunnable);
        Thread thread1 = new Thread(runner1ImplementsRunnable);
        thread.start();
        thread1.start();
    }

}

