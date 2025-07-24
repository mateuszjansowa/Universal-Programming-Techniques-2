package _01_Streams.Lecture.Serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TravelSearcher implements Serializable {
    private List<Travel> travels = new ArrayList<>();

    public void addTravel(Travel travel) {
        travels.add(travel);
    }

    public Travel findTravel(String destination) {
        return travels.stream()
                .filter(t -> t.getDestination().equalsIgnoreCase(destination))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "TravelSearcher{" +
                "travels=" + travels +
                "}";
    }
}
