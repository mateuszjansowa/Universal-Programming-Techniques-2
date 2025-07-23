package _01_Streams.Lecture.CharCoding;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static String INPUT_PATH = "src/_01_Streams/resources/input_pl.txt";
    public static String OUTPUT_PATH = "src/_01_Streams/resources/output_pl.txt";

    public static void main(String[] args) throws IOException {
        try (FileInputStream fis = new FileInputStream(INPUT_PATH);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr);

             FileOutputStream fos = new FileOutputStream(OUTPUT_PATH);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
