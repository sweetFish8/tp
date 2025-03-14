package VoyaTrip.command;

import java.util.ArrayList;
import java.util.HashMap;

import VoyaTrip.command.exceptions.InvalidCommand;
import VoyaTrip.command.types.AccommodationParser;
import VoyaTrip.command.types.ActivityParser;
import VoyaTrip.command.types.TransportationParser;
import VoyaTrip.command.types.TripsParser;

public class Parser {
    private TripsParser tripParser;
    private ActivityParser activityParser;
    private AccommodationParser accommodationParser;
    private TransportationParser transportationParser;
    private Parser currentParser;
    private String currentTrip;

    public Parser() {
        currentTrip = "";
        tripParser = new TripsParser();
        activityParser = new ActivityParser();
        accommodationParser = new AccommodationParser();
        transportationParser = new TransportationParser();
        currentParser = tripParser;
    }

    public HashMap<String, String> tokenize(String command) throws InvalidCommand {
        String[] spaceSeparatedTokens = command.split(" ");
        return switch (spaceSeparatedTokens[0]) {
            case "trip" -> tripParser.tokenize(command);
            case "activity" -> activityParser.tokenize(command);
            case "accommodation" -> accommodationParser.tokenize(command);
            case "transportation" -> transportationParser.tokenize(command);
            default -> currentParser.tokenize(command);
        };
    }
}
