package _06_Metadane;

import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void main(String[] args) throws Exception {
        Method method = Main.class.getMethod("process");
        if (method.isAnnotationPresent(LogExecutionTime.class)) {
            long start = System.nanoTime();
            method.invoke(new Main());
            long end = System.nanoTime();
            System.out.println("Execution time: " + (end - start) + " ns");
        }
    }
}
