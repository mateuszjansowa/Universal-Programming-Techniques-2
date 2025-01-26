package _08_Threads_Executors.Interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SleepTask implements Runnable {
    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Working on it...");
                Thread.sleep(1000); // moze zostac przerwany
            }
        } catch (InterruptedException e) {
            System.out.println("Zadanie zostalo przerwane w sleep()");
        }
    }
}
public class InterruptedExceptionInSleepWaitLock {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new SleepTask());

        Thread.sleep(3000);
        exec.shutdownNow();
    }
}
