package _01_Streams.Lecture.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = "John Doe/3000\nAliceSmith/4500";
        List<Employee> employees = new ArrayList<>();

        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line).useDelimiter("/");

            String name = lineScanner.next();
            double salary = lineScanner.nextDouble();

            employees.add(new Employee(name, salary));
        }

        employees.forEach(System.out::println);
    }
}


class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}