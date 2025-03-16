package voyatrip.ui;

import java.util.ArrayList;
import java.util.Scanner;

import voyatrip.Trip;
import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.Command;
import voyatrip.command.types.CommandAction;
import voyatrip.command.types.CommandTarget;
import voyatrip.command.types.TripsCommand;

public class Ui {
    private static final Parser parser = new Parser();
    private ArrayList<Trip> trips;
    private Boolean isExit = false;

    public void run() {
        while (!isExit) {
            handleInput(readInput());
        }
    }

    private String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private void handleInput(String input) {
        try {
            Command command = parser.parse(readInput());
            handleCommand(command);
        } catch (InvalidCommand e) {
            Message.printInvalidCommand();
        }
    }

    private void handleCommand(Command command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> handleAdd(command);
        case DELETE -> handleDelete(command.getCommandTarget());
        case LIST -> handleList(command.getCommandTarget());
        case EXIT -> handleExit();
        }
    }

    private void handleAdd(Command command) {
        switch (command.getCommandTarget()) {
        case TRIP -> {
            TripsCommand tripsCommand = (TripsCommand) command;
//            trips.add(new Trip(tripsCommand.getStartDate(), tripsCommand.getEndDate(), tripsCommand.getTotalBudget()));
        }
        case ACTIVITY -> {
//            trips.get(trips.size() - 1).addItinerary();
        }
        case ACCOMMODATION -> {
//            trips.get(trips.size() - 1).addAccommodation();
        }
        case TRANSPORTATION -> {
//            trips.get(trips.size() - 1).addTransportation();
        }
        }
    }

    private void handleDelete(CommandTarget target) {
        switch (target) {
        case TRIP -> {
            trips.remove(trips.size() - 1);
        }
        case ACTIVITY -> {
//            trips.get(trips.size() - 1).removeItinerary();
        }
        case ACCOMMODATION -> {
//            trips.get(trips.size() - 1).removeAccommodation();
        }
        case TRANSPORTATION -> {
//            trips.get(trips.size() - 1).removeTransportation();
        }
        }
    }

    private void handleList(CommandTarget target) {
        switch (target) {
        case TRIP -> {
            for (int i = 0; i < trips.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + trips.get(i));
            }
        }
        case ITINERARY -> {
//            trips.get(trips.size() - 1).printItinerary();
        }
        case ACCOMMODATION -> {
//            trips.get(trips.size() - 1).printAccommodation();
        }
        case TRANSPORTATION -> {
//            trips.get(trips.size() - 1).printTransportation();
        }
        }
    }

    private void handleExit() {
        isExit = true;
    }
}
