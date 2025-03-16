package voyatrip.ui;

import java.util.ArrayList;
import java.util.Scanner;

import voyatrip.Trip;
import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.Command;

public class Ui {
    private Parser parser = new Parser();
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
        switch (command.getAction()) {
        case CommandAction.ADD -> handleAdd(command.getTarget());
        case CommandAction.DELETE -> handleDelete(command.getTarget());
        case CommandAction.LIST -> handleList(command.getTarget());
        case CommandAction.EXIT -> handleExit();
        }
    }

    private void handleAdd(CommandTarget target) {
        switch (target) {
        case commandTarget.TRIP -> {
            trips.add(new Trip());
        }
        case commandTarget.ITINERARY -> {
            trips.get(trips.size() - 1).addItinerary();
        }
        case commandTarget.ACCOMMODATION -> {
            trips.get(trips.size() - 1).addAccommodation();
        }
        case commandTarget.TRANSPORTATION -> {
            trips.get(trips.size() - 1).addTransportation();
        }
        }
    }

    private void handleDelete(CommandTarget target) {
        switch (target) {
        case commandTarget.TRIP -> {
            trips.remove(trips.size() - 1);
        }
        case commandTarget.ITINERARY -> {
            trips.get(trips.size() - 1).removeItinerary();
        }
        case commandTarget.ACCOMMODATION -> {
            trips.get(trips.size() - 1).removeAccommodation();
        }
        case commandTarget.TRANSPORTATION -> {
            trips.get(trips.size() - 1).removeTransportation();
        }
        }
    }

    private void handleList(commandTarget target) {
        switch (target) {
        case commandTarget.TRIP -> {
            for (int i = 0; i < trips.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + trips.get(i));
            }
        }
        case commandTarget.ITINERARY -> {
            trips.get(trips.size() - 1).printItinerary();
        }
        case commandTarget.ACCOMMODATION -> {
            trips.get(trips.size() - 1).printAccommodation();
        }
        case commandTarget.TRANSPORTATION -> {
            trips.get(trips.size() - 1).printTransportation();
        }
        }
    }

    private void handleExit() {
        isExit = true;
    }
}
