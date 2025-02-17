package _09_Threads_Synchronizers._03_Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Reentrant {
    private final Lock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        try {
            System.out.println("Method 1");
            method2(); // w?tek mo?e ponownie zdj?? blokad?
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("Method 2 :)");
        } finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockExample {
    public static void main(String[] args) {
        Reentrant example = new Reentrant();
        example.method1();
    }
}
