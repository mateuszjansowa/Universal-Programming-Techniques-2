package _09_Threads_Synchronizers.Challenges;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void transfer(BankAccount target, int amount) {
        BankAccount first = this;
        BankAccount second = target;

        // zawsze blokuj konta w tej samej kolejnosci
        if (System.identityHashCode(this) > System.identityHashCode(target)) {
            first = target;
            second = this;
        }

        // zablokuj konto o nizszym ID
        first.lock.lock();

        try {
            // zablokuj drugie konto
            second.lock.lock();
            try {
                // przenos kase
                if (balance >= amount) {
                    balance -= amount;
                    target.balance += amount;
                }
            } finally {
                second.lock.unlock(); // odblokuj drugie konto
            }
        } finally {
            first.lock.unlock(); // odblokuj pierwsze konto
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class Task_03 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                account1.transfer(account2, 1);
                account2.transfer(account1, 1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        System.out.println("Saldo koncowe kont: " + account1.getBalance() + " - " + account2.getBalance());
    }
}
