package _01_Streams.Lecture.CharOperations;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static String INPUT_PATH = "src/_01_Streams/resources/input_2.txt";
    public static String OUTPUT_PATH = "src/_01_Streams/resources/output_2.txt";

    public static void main(String[] args) throws IOException {
        try (FileReader fileReader = new FileReader(INPUT_PATH); FileWriter fileWriter = new FileWriter(OUTPUT_PATH)) {
            int c;

            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
            }
        }

    }
}
