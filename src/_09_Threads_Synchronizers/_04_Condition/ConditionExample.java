package _09_Threads_Synchronizers._04_Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isReady = false;

    public void waitForSignal() throws InterruptedException {
        lock.lock();
        try {
            while (!isReady) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                condition.await();
            }
            System.out.println("Signal received");
        } finally {
            lock.unlock();
        }
    }

    public void sendSignal() {
        lock.lock();
        try {
            isReady = true;
            System.out.println(Thread.currentThread().getName() + " sending signal...");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();

        Thread waitingThread = new Thread(() -> {
            try {
                example.waitForSignal();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "WaitingThread");

        Thread signalingThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                example.sendSignal();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "SignalingThread");

        waitingThread.start();
        signalingThread.start();
    }
}
