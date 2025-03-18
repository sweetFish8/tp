package voyatrip;

import java.util.ArrayList;

public class Trip {
    private String destination;
    private String name;
    private ArrayList<Transportation> transportations;

    public Trip() {
        this.transportations = new ArrayList<Transportation>();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTransportation(String transportMode, String transportName, Integer transportBudget) {
        transportations.add(new Transportation(transportMode, transportName, transportBudget));
    }

    public void deleteTransportation(Integer index) {
        if (index < 1 || index > transportations.size()) {
            System.out.println("Invalid index: " + index);
            return;
        }
        transportations.remove(index - 1);
        // index - 1, to convert to zero-based index
    }

}



