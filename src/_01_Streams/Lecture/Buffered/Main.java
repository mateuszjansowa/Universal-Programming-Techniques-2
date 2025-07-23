package _01_Streams.Lecture.Buffered;

import java.io.*;

public class Main {
    public static String INPUT_PATH = "src/_01_Streams/resources/log.json";
    public static String OUTPUT_PATH = "src/_01_Streams/resources/log_target.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH)); BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
