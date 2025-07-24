package _01_Streams.Lecture.Serialization;

import java.io.*;

public class Main {

    public static String OUTPUT_PATH = "src/_01_Streams/resources/serialized_output.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Mati", 29);


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_PATH))) {
            serialize(oos, person);
        }

        try (ObjectInputStream ois = new ObjectInputStream((new FileInputStream(OUTPUT_PATH)))) {
            deserialize(ois, person);
        }
    }

    public static void serialize(ObjectOutputStream oos, Serializable item) throws IOException {
        oos.writeObject(item);
    }

    public static void deserialize(ObjectInputStream ois, Serializable item) throws IOException, ClassNotFoundException {
        Person deserializedPerson = (Person) ois.readObject();
        System.out.println("Read: " + deserializedPerson);
    }
}
