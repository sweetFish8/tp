package voyatrip;


import java.time.LocalDate;

/**
 * This is the trip class that will hold all the information about the trip.
 */
public class Trip {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalBudget;
    private Integer numDays;
    private ArrayList<Day> itineray;
    private ArrayList<Accommodation> accommodations;
    private ArrayList<Transportation> transportations;

    /**
     * Constructor for the trip class.
     * @param startDate the start date of the trip.
     * @param endDate the end date of the trip.
     * @param totalBudget the total budget for the trip.
     */
    public Trip(LocalDate startDate, LocalDate endDate, Integer totalBudget) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalBudget = totalBudget;
    }
}
