package _09_Threads_Synchronizers._04_CountdownLatch;

import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println(getName() + " finished work");
        latch.countDown(); // obniza licznik
    }
}

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        new Worker(latch).start();
        new Worker(latch).start();
        new Worker(latch).start();
        new Worker(latch).start();
        new Worker(latch).start();

        latch.await();
        System.out.println("All threads finished working");
    }
}
