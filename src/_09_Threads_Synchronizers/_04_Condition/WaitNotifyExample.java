package _09_Threads_Synchronizers._04_Condition;

class Buffer {
    private String data;
    private boolean empty = true;

    public synchronized void produce(String value) throws InterruptedException {
        while (!empty) { // jesli bufor jest pelny, czekaj
            wait();
        }
        data = value;
        empty = false;
        System.out.println("Producent added: " + value);
        notify(); // obudzenie czekajacego konsumenta
    }

    public synchronized String consume() throws InterruptedException {
        while (empty) { // jesli bufor jest pusty, czekaj
            wait();
        }
        empty = true;
        System.out.println("Consumer added: " + data);
        notify(); // obudzenie producenta
        return data;
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce("data-" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumer.start();
        producer.start();
    }
}
