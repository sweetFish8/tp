package voyatrip.ui;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import voyatrip.Trip;
import voyatrip.command.Parser;
import voyatrip.command.types.Command;
import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.exceptions.TripNotFoundException;
import voyatrip.command.types.ItineraryCommand;
import voyatrip.command.types.AccommodationCommand;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;
import voyatrip.command.types.CommandAction;

import static voyatrip.command.types.CommandAction.EXIT;


public class Ui {
    private static final Parser parser = new Parser();
    private static final Scanner in = new Scanner(System.in);
    private ArrayList<Trip> trips;
    private Boolean isExit = false;

    public static void printWelcomeMessage() {
        System.out.println(Message.WELCOME_MESSAGE);
    }

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
        if (EXIT.equals(command.getCommandAction())) {
            handleExit();
            return;
        }

        switch (command.getCommandTarget()) {
        case TRIP -> handleTrip((TripsCommand) command);
        case ITINERARY -> handleItinerary((ItineraryCommand) command);
        case ACTIVITY -> handleActivity((ItineraryCommand) command);
        case ACCOMMODATION -> handleAccommodation((AccommodationCommand) command);
        case TRANSPORTATION -> handleTransportation((TransportationCommand) command);
        default -> throw new InvalidCommand();
        }
    }

    private void handleTrip(TripsCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddTrip(command);
        case DELETE -> executeDeleteTrip(command);
        case LIST -> executeListTrip(command);
        default -> throw new InvalidCommand();
        }
    }

    private void handleItinerary(ItineraryCommand command) throws InvalidCommand {
        if (Objects.requireNonNull(command.getCommandAction()) == CommandAction.LIST) {
            executeListItinerary(command);
        } else {
            throw new InvalidCommand();
        }
    }

    private void handleActivity(ItineraryCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddActivity(command);
        case DELETE -> executeDeleteActivity(command);
        default -> throw new InvalidCommand();
        }
    }

    private void handleAccommodation(AccommodationCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddAccommodation(command);
        case DELETE -> executeDeleteAccommodation(command);
        case LIST -> executeListAccommodation(command);
        default -> throw new InvalidCommand();
        }
    }

    private void handleTransportation(TransportationCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddTransportation(command);
        case DELETE -> executeDeleteTransportation(command);
        case LIST -> executeListTransportation(command);
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

    private void executeAddTransportation(TransportationCommand command) {

        String tripName = command.getTrip();
        String transportMode = command.getMode();
        String transportName = command.getName();
        Integer transportBudget = command.getBudget();

        try {
            Trip trip = findTrip(tripName);
            if (trip == null) {
                throw new TripNotFoundException();
            }
            trip.addTransportation(transportMode, transportName, transportBudget);
            System.out.println("Added transportation");
        } catch (TripNotFoundException e) {
            Message.printTripNotFound();
        }

    }

    private Trip findTrip(String associatedTrip) {
        for (Trip trip : trips) {
            if (trip.getName().equals(associatedTrip)) {
                return trip;
            }
        }
        return null;
    }

    private void executeDeleteTrip(Command command) {
    }

    private void executeDeleteActivity(Command command) {
    }

    private void executeDeleteAccommodation(Command command) {

    }

    private void executeListAccommodation(Command command) {

    }

    private void executeDeleteTransportation(TransportationCommand command) {
        String tripName = command.getTrip();
        Integer transportIndex = command.getIndex();

        try {
            Trip trip = findTrip(tripName);
            if (trip == null) {
                throw new TripNotFoundException();
            }
            trip.deleteTransportation(transportIndex);
            System.out.println("Deleted transportation");
        } catch (TripNotFoundException e) {
            Message.printTripNotFound();
        }
    }

    private void executeListTransportation(TransportationCommand command) {

    }

    private void executeListTrip(Command command) {
    }

    private void executeListItinerary(Command command) {
    }

    public static void printGoodbyeMessage() {
        System.out.println(Message.GOODBYE_MESSAGE);
    }

    public static void printAddTripMessage(String abbrTripInfo) {
        System.out.println(Message.ADD_TRIP_MESSAGE);
        System.out.println(abbrTripInfo);
    }

    public static void printDeleteTripMessage(String abbrTripInfo) {
        System.out.println(Message.DELETE_TRIP_MESSAGE);
        System.out.println(abbrTripInfo);
    }

    public static void printInvalidCommand() {
        System.out.println(Message.INVALID_COMMAND_MESSAGE);
    }

    public static void printIndexOutOfBounds() {
        System.out.println(Message.INDEX_OUT_OF_BOUNDS_MESSAGE);
    }
}
