package voyatrip.command;

import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.AccommodationCommand;
import voyatrip.command.types.ActivityCommand;
import voyatrip.command.types.Command;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;

public class Parser {
    private String currentPage;
    private String currentTrip;

    public Parser() {
        currentPage = "allTrip";
        currentTrip = "";
    }

    // maybe list all within the trip will list the whole trip
    public Command tokenize(String command) throws InvalidCommand {
        command = command.strip();
        String[] spaceSeparatedTokens = command.split(" ");
        String commandKeyword = spaceSeparatedTokens[0] + " " + spaceSeparatedTokens[1];
        String argument = command.replaceFirst(commandKeyword, "");
        return switch (commandKeyword) {
            case "add trip" -> new TripsCommand("add trip").tokenizeAdd(argument);
            case "add activity" -> new ActivityCommand("add activity").tokenizeAdd(argument);
            case "add accommodation" -> new AccommodationCommand("add accommodation").tokenizeAdd(argument);
            case "add transportation" -> new TransportationCommand("add transportation").tokenizeAdd(argument);
            default -> throw new InvalidCommand();
        };
    }
}
