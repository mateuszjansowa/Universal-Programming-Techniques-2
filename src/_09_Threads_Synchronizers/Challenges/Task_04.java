package _09_Threads_Synchronizers.Challenges;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;
    private boolean isEmpty = true;
    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotEmpty = lock.newCondition();
    private final Condition bufferNotFull = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                // Czekaj, je?li bufor jest pe?ny
                bufferNotEmpty.await();
            }
            isEmpty = false;
            queue.add(value);
            System.out.println("Producent doda?: " + value);
            bufferNotFull.signal(); // obudz konsumenta
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                // Czekaj, je?li bufor jest pusty
                bufferNotFull.await();
            }
            isEmpty = true;
            int value = queue.poll();
            System.out.println("Konsument pobra?: " + value);
            bufferNotEmpty.signal(); // obudz producenta
            return value;
        } finally {
            lock.unlock();
        }
    }
}

public class Task_04 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
