package voyatrip.command;

import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.AccommodationCommand;
import voyatrip.command.types.ItineraryCommand;
import voyatrip.command.types.Command;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;

public class Parser {
    private String currentPage;
    private String currentTrip;

    public Parser() {
        currentPage = "all";
        currentTrip = "";
    }

    /**
     * Set the current page the user is in.
     *
     * @param page The current page the user is in.
     */
    public void setCurrentPage(String page) {
        currentPage = page;
    }

    /**
     * Set the current trip the user is in.
     *
     * @param trip The current trip the user is in.
     */
    public void setCurrentTrip(String trip) {
        currentTrip = trip;
    }

    /**
     * Parse the input command.
     * The return is an abstract Command object that represents the input command.
     *
     * @param command Input command.
     * @return Command object that represents the input command.
     * @throws InvalidCommand If the input command is invalid.
     */
    public Command parse(String command) throws InvalidCommand {
        String argument = extractCommandArgument(command);
        String commandAction = extractCommandAction(command);
        String commandTargetType = extractCommandTargetType(command);

        boolean isIncorrectScope = !commandTargetType.equals("trip") && currentPage.equals("all");
        if (isIncorrectScope) {
            throw new InvalidCommand();
        }

        return matchCommand(commandAction, commandTargetType, argument);
    }

    private String extractCommandArgument(String command) {
        command = command.strip();
        String[] spaceSeparatedTokens = command.split(" ");

        return command.replaceFirst(spaceSeparatedTokens[0] + " " + spaceSeparatedTokens[1], "");
    }

    private String extractCommandAction(String command) throws InvalidCommand {
        String commandAction = command.strip().split(" ")[0];
        return switch (commandAction) {
        case "add", "a", "make", "mk" -> "add";
        case "delete", "d", "remove", "rm" -> "delete";
        case "list", "l" -> "list";
        case "cd" -> "cd";
        default -> throw new InvalidCommand();
        };
    }

    private String extractCommandTargetType(String command) throws InvalidCommand {
        String commandTargetType = command.strip().split(" ")[1];
        return switch (commandTargetType) {
        case "trip" -> "trip";
        case "itinerary", "i" -> "itinerary";
        case "activity", "act" -> "activity";
        case "accommodation", "accom" -> "accommodation";
        case "transportation", "tran" -> "transportation";
        default -> currentPage;
        };
    }

    private Command matchCommand(String commandAction, String commandTargetType, String rawArgument) throws InvalidCommand {
        String commandKeyword = commandAction + " " + commandTargetType;

        return switch (commandTargetType) {
        case "trip" -> new TripsCommand(commandKeyword, rawArgument);
        case "itinerary", "day", "activity" -> new ItineraryCommand(commandKeyword, currentTrip, rawArgument);
        case "accommodation" -> new AccommodationCommand(commandKeyword, currentTrip, rawArgument);
        case "transportation" -> new TransportationCommand(commandKeyword, currentTrip, rawArgument);
        default -> throw new InvalidCommand();
        };
    }
}
