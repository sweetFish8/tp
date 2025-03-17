package voyatrip.ui;

import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.AccommodationCommand;
import voyatrip.command.types.CommandAction;
import voyatrip.command.types.CommandTarget;
import voyatrip.command.types.ItineraryCommand;
import voyatrip.command.types.Command;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;

public class Parser {
    private CommandTarget currentTarget;
    private String currentTrip;

    public Parser() {
        currentTarget = null;
        currentTrip = "";
    }

    /**
     * Set the current target page the user is in.
     *
     * @param target The current target page the user is in.
     */
    public void setCurrentTarget(CommandTarget target) {
        currentTarget = target;
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
        String argument;
        CommandAction commandAction;
        CommandTarget commandTarget;
        try {
            argument = extractCommandArgument(command);
            commandAction = extractCommandAction(command);
            commandTarget = extractCommandTargetType(command);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommand();
        }

        boolean isIncorrectScope = !commandTarget.equals(CommandTarget.TRIP) && currentTarget == null;
        if (isIncorrectScope) {
            throw new InvalidCommand();
        }

        return matchCommand(commandAction, commandTarget, argument);
    }

    private String extractCommandArgument(String command) {
        command = command.strip();
        String[] spaceSeparatedTokens = command.split(" ");

        return command.replaceFirst(spaceSeparatedTokens[0] + " " + spaceSeparatedTokens[1], "");
    }

    private CommandAction extractCommandAction(String command) throws InvalidCommand {
        String commandAction = command.strip().split(" ")[0];
        return switch (commandAction) {
        case "add", "a", "make", "mk" -> CommandAction.ADD;
        case "delete", "d", "remove", "rm" -> CommandAction.DELETE;
        case "list", "l" -> CommandAction.LIST;
        case "cd" -> CommandAction.CHANGE_DIRECTORY;
        default -> throw new InvalidCommand();
        };
    }

    private CommandTarget extractCommandTargetType(String command) throws InvalidCommand {
        String commandTarget = command.strip().split(" ")[1];
        return switch (commandTarget) {
        case "trip" -> CommandTarget.TRIP;
        case "itinerary", "i" -> CommandTarget.ITINERARY;
        case "activity", "act" -> CommandTarget.ACTIVITY;
        case "accommodation", "accom" -> CommandTarget.ACCOMMODATION;
        case "transportation", "tran" -> CommandTarget.TRANSPORTATION;
        default -> currentTarget;
        };
    }

    private Command matchCommand(CommandAction commandAction, CommandTarget commandTarget, String rawArgument)
            throws InvalidCommand {
        return switch (commandTarget) {
        case TRIP -> new TripsCommand(commandAction, commandTarget, rawArgument);
        case ITINERARY, ACTIVITY -> new ItineraryCommand(commandAction, commandTarget, currentTrip, rawArgument);
        case ACCOMMODATION -> new AccommodationCommand(commandAction, commandTarget, currentTrip, rawArgument);
        case TRANSPORTATION -> new TransportationCommand(commandAction, commandTarget, currentTrip, rawArgument);
        default -> throw new InvalidCommand();
        };
    }
}
