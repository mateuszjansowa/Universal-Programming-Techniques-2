package _09_Threads_Synchronizers.Challenges;

class Counter {
    private int count = 0;

    public synchronized void increment() { // dzieki synchronized tylko jeden w?tek naraz mo?e modyfikowa? warto?? count
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

public class Task_01 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
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
            System.out.println("Main thread is interrupted");
        }

        System.out.println("Oczekiwany wynik: 2000, Aktualny wynik: " + counter.getCount());
    }
}
