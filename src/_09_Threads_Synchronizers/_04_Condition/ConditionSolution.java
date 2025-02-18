package _09_Threads_Synchronizers._04_Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BufferWithCondition {
    private String data;
    private boolean empty = true;
    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotEmpty = lock.newCondition();
    private final Condition bufferNotFull = lock.newCondition();

    public void produce(String value) throws InterruptedException {
        lock.lock();
        try {
            while (!empty) {
                bufferNotFull.await(); // czekaj jesli bufor nie jest pelny
            }
            data = value;
            empty = false;
            System.out.println("Producer added: " + value);
            bufferNotEmpty.signal(); // obudz konsumenta
        } finally {
            lock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();
        try {
            while (empty) {
                bufferNotEmpty.await(); // czekaj jesli bufor jest pusty
            }
            empty = true;
            System.out.println("Consumer got: " + data);
            bufferNotFull.signal(); // obudz producenta
            return data;
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionSolution {
}
