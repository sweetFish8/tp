package voyatrip.ui;

public class Message {
    // exception
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";

    // exception
    public static void printInvalidCommand() {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }
}
