package voyatrip;

import java.util.ArrayList;

import voyatrip.ui.Ui;

public class VoyaTrip {
    private static Ui ui = new Ui();
    private static ArrayList<Trip> trips;

    public static void main(String[] args) {
        ui.run();
    }
}
