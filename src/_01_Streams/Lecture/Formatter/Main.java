package _01_Streams.Lecture.Formatter;

import java.util.Formatter;

public class Main {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        formatter.format("Name: %s, Age: %d, Salary: %.2f", "John", 30, 4500.50);

        System.out.println(formatter);
        formatter.close();
    }
}
