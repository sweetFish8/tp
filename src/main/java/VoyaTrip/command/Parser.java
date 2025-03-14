package VoyaTrip.command;

import VoyaTrip.command.exceptions.InvalidCommand;

public class Parser {
    private String currentTrip;
    private String currentPage;

    Parser() {
        currentTrip = "";
        currentPage = "allTrips";
    }

    public void tokenize(String command) throws InvalidCommand {
    }

    private void tokenize(String command, String context) {
    }
}
