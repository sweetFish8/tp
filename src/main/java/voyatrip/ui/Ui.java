package voyatrip.ui;

import java.util.ArrayList;
import java.util.Scanner;

import voyatrip.Trip;
import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.Command;

public class Ui {
    private static final Parser parser = new Parser();
    private ArrayList<Trip> trips;
    private Boolean isExit = false;
    private static final Scanner in = new Scanner(System.in);

    public void run() {
        while (!isExit) {
            handleInput(readInput());
        }
    }

    private String readInput() {
        return in.nextLine();
    }

    private void handleInput(String input) {
        try {
            Command command = parser.parse(input);
            handleCommand(command);
        } catch (InvalidCommand e) {
            Message.printInvalidCommand();
        }
    }

    private void handleCommand(Command command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> handleAdd(command);
        case DELETE -> handleDelete(command);
        case LIST -> handleList(command);
        case EXIT -> handleExit();
        default -> throw new InvalidCommand();
        }
    }

    private void handleAdd(Command command) throws InvalidCommand {
        switch (command.getCommandTarget()) {
        case TRIP -> {
            executeAddTrip(command);
        }
        case ACTIVITY -> {
            executeAddActivity(command);
        }
        case ACCOMMODATION -> {
            executeAddAccommodation(command);
        }
        case TRANSPORTATION -> {
            executeAddTransportation(command);
        }
        default -> throw new InvalidCommand();
        }
    }


    private void handleDelete(Command command) throws InvalidCommand {
        switch (command.getCommandTarget()) {
        case TRIP -> {
            executeDeleteTrip(command);
        }
        case ACTIVITY -> {
            executeDeleteActivity(command);
        }
        case ACCOMMODATION -> {
            executeDeleteAccommodation(command);
        }
        case TRANSPORTATION -> {
            executeDeleteTransportation(command);
        }
        default -> throw new InvalidCommand();
        }
    }

    private void handleList(Command command) throws InvalidCommand {
        switch (command.getCommandTarget()) {
        case TRIP -> {
            executeListTrip(command);
        }
        case ITINERARY -> {
            executeListItinerary(command);
        }
        case ACCOMMODATION -> {
            executeListAccommodation(command);
        }
        case TRANSPORTATION -> {
            executeListTransportation(command);
        }
        default -> throw new InvalidCommand();
        }
    }

    private void handleExit() {
        isExit = true;
    }

    private void executeAddTrip(Command command) {
        System.out.println("Adding trip");
    }

    private void executeAddActivity(Command command) {
    }

    private void executeAddAccommodation(Command command) {
    }

    private void executeAddTransportation(Command command) {
    }

    private void executeDeleteTrip(Command command) {
    }

    private void executeDeleteActivity(Command command) {
    }

    private void executeDeleteAccommodation(Command command) {
    }

    private void executeDeleteTransportation(Command command) {
    }

    private void executeListTrip(Command command) {
    }

    private void executeListItinerary(Command command) {
    }

    private void executeListAccommodation(Command command) {
    }

    private void executeListTransportation(Command command) {
    }
}
