package _09_Threads_Synchronizers._03_Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SafeLockBalance {
    private int number = 0;
    private final Lock lock = new ReentrantLock();

    public int balance() {
        lock.lock(); // zamkniecie blokady
        try {
            number++;
            number--;
            return number;
        } finally {
            lock.unlock();
        }
    }
}

public class SafeLockBalanceExample {
    public static void main(String[] args) {
        SafeLockBalance balance = new SafeLockBalance();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                if (balance.balance() != 0) {
                    System.out.println("Error!");
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
