package voyatrip;

import java.util.ArrayList;

public class Trip {

    private ArrayList<Transportation> transportations;

    public Trip() {
        this.transportations = new ArrayList<Transportation>();
    }

    public void addTransportation(String transportMode) {
        transportations.add(new Transportation(transportMode));
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



