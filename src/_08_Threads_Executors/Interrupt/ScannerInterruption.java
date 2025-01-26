package _08_Threads_Executors.Interrupt;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class InputTask implements Runnable {
    @Override
    public void run() {
        try (Scanner scanner = new Scanner(FileInputStream.nullInputStream())) {
            System.out.println("Wpisz cos: ");
            while (scanner.hasNextLine()) {
                System.out.println("Odczytano: " + scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Watek przerwany");
        }
    }
}

public class ScannerInterruption {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new InputTask());

        Thread.sleep(5000);
        exec.shutdownNow();
    }
}
