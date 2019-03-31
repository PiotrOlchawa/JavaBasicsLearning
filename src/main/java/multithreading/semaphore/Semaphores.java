package multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Semaphores {

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newCachedThreadPool();

/*        Thread thread = new Thread(() -> {
            try {
                Service service = Service.getInstance();
                service.connect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        IntStream.iterate(0, l -> l + 1)
                .limit(200)
                .forEach(l -> executorService.submit(() -> {
                    try {
                        Service service = Service.getInstance();
                        service.connect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service {

    private static final Service service = new Service();
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(5);

    private Service() {
    }

    public static Service getInstance() {
        System.out.println();
        return service;
    }

    public void connect() throws InterruptedException {
        semaphore.acquire();
        System.out.println("Semaphore permits: " + semaphore.availablePermits());

        synchronized (this) {
            connections++;
            System.out.println("Current Connections: " + connections);
        }
        Thread.sleep(1000);
        synchronized (this) {
            connections--;
        }
        semaphore.release();
    }
}
