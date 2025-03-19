package voyatrip.ui;

public class Message {
    // user message
    static final String WELCOME_MESSAGE = "Welcome to VoyaTrip! How can I help you today?";
    static final String GOODBYE_MESSAGE = "Goodbye! Wish you have a lovely trip!";
    static final String ADD_TRIP_MESSAGE = "The following trip has been added to your list! "
            + "What would you like to do next?";
    static final String DELETE_TRIP_MESSAGE = "The following trip has been deleted from your list! "
            + "What would you like to do next?";
    // exception
    static final String TRIP_NOT_FOUND_MESSAGE = "Trip not found. Please try again.";
    static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";
    static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Invalid index. Please try again.";

    // exception
    public static void printInvalidCommand() {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

    //exception
    public static void printTripNotFound() {
        System.out.println(TRIP_NOT_FOUND_MESSAGE);
    }

}
