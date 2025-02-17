package _09_Threads_Synchronizers._02_synchronized;

class SafeBalanceBlock {
    private int number = 0;

    public SafeBalanceBlock() {
        System.out.println("Starting...");
    }


    public int balance() {
        synchronized (this) { // atomowa operacja
            number++;
            System.out.println(number); // 1 1 1 1 1
            number--;
        }

        return number;
    }
}

public class SafeBalanceSynchronizedBlock {
    public static void main(String[] args) {
        SafeBalanceBlock balance = new SafeBalanceBlock();

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
