package _08_Threads_Executors.Interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockTask implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " nie uzyskal blokady!");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " ma blokade");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " przerwany podczas oczekiwania na blokade!");
        } finally {
            if (lock.tryLock()) {
                lock.unlock();
            }
        }
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new LockTask());
        exec.execute(new LockTask());

        Thread.sleep(2000);
        exec.shutdownNow();
    }
}
