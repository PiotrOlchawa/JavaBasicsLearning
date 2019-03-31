package multithreading.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {


    public static void main(String[] args) throws InterruptedException {

        Account accountOne = new Account(10000);
        Account accountTwo = new Account(10000);
        RandomizedAccountTranswer randomizedAccountTranswer = new RandomizedAccountTranswer(accountOne, accountTwo);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        randomizedAccountTranswer.transwerFromSrcToDstAccount();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        randomizedAccountTranswer.transwerFromDstToSrcAccount();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

class RandomizedAccountTranswer {

    private static final Random RANDOM = new Random();
    private Account srcAccount;
    private Account dstAccount;
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();


    public RandomizedAccountTranswer(Account srcAccount, Account dstAccount) {
        this.srcAccount = srcAccount;
        this.dstAccount = dstAccount;
    }

    private void aquireLocks(Lock lock1, Lock lock2) {

        boolean lock1IsLocked = false;
        boolean lock2IsLocked = false;

        while (true) {
            lock1IsLocked = lock1.tryLock();
            lock2IsLocked = lock2.tryLock();

            if (lock1IsLocked && lock2IsLocked) return;
            if (lock1IsLocked) lock1.unlock();
            if (lock2IsLocked) lock2.unlock();
        }
    }


    public void transwerFromSrcToDstAccount() throws InterruptedException {
        //lock1.lock();
        //lock2.lock();
        aquireLocks(lock1,lock2);
        Account.transwer(srcAccount, dstAccount, RANDOM.nextInt(100));
        showAccountBalancesAfterTranswer();
        lock1.unlock();
        lock2.unlock();
        //Thread.sleep(1000);
    }

    public void transwerFromDstToSrcAccount() throws InterruptedException {
        //lock2.lock();  This will cause deadlock
        //lock1.lock();
        aquireLocks(lock1,lock2);
        Account.transwer(dstAccount, srcAccount, RANDOM.nextInt(100));
        showAccountBalancesAfterTranswer();
        lock1.unlock();
        lock2.unlock();
        //Thread.sleep(1000);

    }

    private void showAccountBalancesAfterTranswer() {
        System.out.println("Account Sum Balances: " + (srcAccount.getAmount() + dstAccount.getAmount()));
        System.out.println("Src Account: " + srcAccount.getAmount());
        System.out.println("Dst Account: " + dstAccount.getAmount());
    }
}


class Account {

    private int amount = 0;

    public Account(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static void transwer(Account srcAccount, Account dstAccount, int amount) {

        dstAccount.setAmount(dstAccount.getAmount() + amount);
        srcAccount.setAmount(srcAccount.getAmount() - amount);
    }

}