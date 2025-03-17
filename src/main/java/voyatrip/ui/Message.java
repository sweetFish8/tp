package voyatrip.ui;

public class Message {
    // String Constants
    // user interface message
    private static final String WELCOME_MESSAGE = "Welcome to VoyaTrip! What can I do for you today?";
    private static final String GOODBYE_MESSAGE = "Goodbye! Wish you a lovely trip!";

    // exception
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";

    // Print methods
    // user interface message
    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    // exception
    public static void printInvalidCommand() {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

    // Getters
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public static String getGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    public static String getInvalidCommandMessage() {
        return INVALID_COMMAND_MESSAGE;
    }
}
