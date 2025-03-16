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

    public void deleteTransportation(String transportMode) {
        for (Transportation transportation : transportations) {
            if (transportation.getMode().equals(transportMode)) {
                transportations.remove(transportation);
            }
        }

    }
}
