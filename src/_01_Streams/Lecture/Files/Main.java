package _01_Streams.Lecture.Files;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.random.RandomGenerator;

public class Main {

    public static String FILE_PATH = "src/_01_Streams/resources/input.txt";
    public static String RAF_PATH = "src/_01_Streams/resources/random_access.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File: " + file.getName() + " already exists");
            }

            System.out.println("Path: " + file.getPath());
            System.out.println("Is dir: " + file.isDirectory());
            System.out.println("Can read: " + file.canRead());
            System.out.println("Can write: " + file.canWrite());

            createRandomAccessFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createRandomAccessFile() {
        try {
            RandomAccessFile raf = new RandomAccessFile(RAF_PATH, "rw");
            raf.writeUTF("Hello!");
            raf.seek(0);

            String data = raf.readUTF();
            System.out.println("Read from file: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
