package _01_Streams.Lecture.Serialization;

import java.io.*;

public class TravelApp {
    public static String OUTPUT_PATH = "src/_01_Streams/resources/travel_serialized_output.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TravelSearcher searcher = new TravelSearcher();
        searcher.addTravel(new Travel("Cyprus", 1500));
        searcher.addTravel(new Travel("Crete", 1000));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_PATH))) {
            oos.writeObject(searcher);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_PATH))) {
            TravelSearcher deserializedSearcher = (TravelSearcher) ois.readObject();
            System.out.println("Read: " + deserializedSearcher);
        }
    }
}
