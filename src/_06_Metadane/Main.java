package _06_Metadane;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
@interface LogExecutionTime {}
public class Main {
    @LogExecutionTime
    public void process() {
        System.out.println("Processing...");
    }
}
