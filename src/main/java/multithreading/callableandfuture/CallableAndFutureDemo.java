package multithreading.callableandfuture;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class CallableAndFutureDemo {

    public static void main(String args[]) {

        Map<InetAddress, Boolean> inetAddressBooleanMap = new HashMap<>();

        try {
            inetAddressBooleanMap.put(InetAddress.getByName("8.8.8.8"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("192.168.1.1"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("interia.pl"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("wp.pl"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("wpdgsheudk.pl"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("7.7.7.7"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("192.168.1.2"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("bankier.pl"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("hp.pl"), false);
            inetAddressBooleanMap.put(InetAddress.getByName("allegro.pl"), false);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Pinger pinger = new Pinger(inetAddressBooleanMap);

        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.iterate(0, l -> l + 1).limit(inetAddressBooleanMap.size()).forEach(l -> {
            executorService.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    pinger.processOneMapAdress(l);
                    System.out.println("Adress process completed: " + l);
                    return null;
                }
            });
        });

        executorService.submit(() -> {
            int counts = 0;
        while(true){
            counts++;
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Thread was interrrupted");
                break;
            }
            if (counts>=3) {

            }
            System.out.println("Attempt: " + counts);
            inetAddressBooleanMap.entrySet().stream().filter(k->!k.getValue()).forEach(k->{
               pinger.checkIfReachable(k.getKey(),1000);
            });
            if(inetAddressBooleanMap.entrySet().stream().filter(l->!l.getValue()).count()==0){
            break;
            }
        }
        });

        executorService.shutdown();

        try {
            while (!executorService.awaitTermination(40, TimeUnit.SECONDS)) {
                System.out.println("Service did not finish in 40 seconds");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(inetAddressBooleanMap);
    }


}


class Pinger {

    Map<InetAddress, Boolean> inetAddress;
    Lock lock = new ReentrantLock();
    Lock lock1 = new ReentrantLock();


    public Pinger(Map<InetAddress, Boolean> inetAddress) {
        this.inetAddress = inetAddress;
    }

    private void aquireLocks() throws InterruptedException {

        boolean lockAquired = false;
        boolean lock1Aquired = false;

        while (true) {
            lockAquired = lock.tryLock();
            lock1Aquired = lock1.tryLock();

            if (lock1Aquired && !lockAquired) {
                lock1.unlock();
            }
            if (!lock1Aquired && lockAquired) {
                lock.unlock();
            }

            if (lockAquired && lock1Aquired) {
                break;
            }
            //Thread.sleep(1);
        }
    }


    void processOneMapAdress(int processNo) throws InterruptedException {
        aquireLocks();
        System.out.println("Aquiring locks for: " + processNo);
        Optional<InetAddress> notReachableAddressIntegerMap = inetAddress.entrySet()
                .stream().filter(k -> !k.getValue()).map(Map.Entry::getKey).findFirst();
        if (notReachableAddressIntegerMap.isPresent()) {
            InetAddress inetAddress = notReachableAddressIntegerMap.get();
            checkIfReachable(inetAddress, processNo);
        }
        lock.unlock();
        lock1.unlock();
        System.out.println("Releasing locks for: " + processNo);
    }

    public void checkIfReachable(InetAddress inetAddress, int processNo) {
        lock1.lock();
//        System.out.println("Lock is released");
        try {
           System.out.println("Trying to ping: " + processNo + " " + inetAddress.getHostAddress());
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 -W 1 " + inetAddress.getHostAddress());
            int returnVal = p1.waitFor();
            System.out.println("Value Returned: " + processNo + " " + returnVal);
            boolean reachable = (returnVal == 0);
            if (reachable) {
                System.out.println("Adress is reachable: " + processNo + " " + inetAddress.getHostAddress());
                this.inetAddress.put(inetAddress, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //e.printStackTrace();
        }
        lock1.unlock();
    }

}




