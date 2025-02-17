package _09_Threads_Synchronizers._02_synchronized;

class SafeBalance {
    private int number = 0;

    public SafeBalance() {
        System.out.println("Starting...");
    }


    public synchronized int balance() {
        number++;
        System.out.println(number); // 1 1 1 1 1
        number--;
        return number;
    }
}

public class SafeBalanceTest {
    public static void main(String[] args) {
        SafeBalance balance = new SafeBalance();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                if (balance.balance() != 0) {
                    System.out.println("Error!");
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
