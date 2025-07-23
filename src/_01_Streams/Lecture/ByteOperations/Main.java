package _01_Streams.Lecture.ByteOperations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {


    public static String INPUT_PATH = "src/_01_Streams/resources/input.txt";
    public static String OUTPUT_PATH = "src/_01_Streams/resources/output.txt";

    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream(INPUT_PATH); FileOutputStream out = new FileOutputStream(OUTPUT_PATH)) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        }
    }
}
