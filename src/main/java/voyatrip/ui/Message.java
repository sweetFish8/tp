package voyatrip.ui;

public class Message {
    // exception
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";
    private static final String TRIP_NOT_FOUND_MESSAGE = "Trip not found. Please try again.";
    // exception
    public static void printInvalidCommand() {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

    //exception
    public static void printTripNotFound() {
        System.out.println(TRIP_NOT_FOUND_MESSAGE);
    }
}
