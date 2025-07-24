package _01_Streams.Lecture.Serialization;

import java.io.Serializable;

public class Travel implements Serializable {
    private String destination;
    private int price;

    public Travel(String destination, int price) {
        this.destination = destination;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Travel{" + "destination='" + destination + '\'' + ", price=" + price + '}';
    }

    public String getDestination() {
        return this.destination;
    }
}
