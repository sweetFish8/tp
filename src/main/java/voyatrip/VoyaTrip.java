package voyatrip;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.exceptions.TripNotFoundException;
import voyatrip.command.types.AccommodationCommand;
import voyatrip.command.types.Command;
import voyatrip.command.types.CommandAction;
import voyatrip.command.types.ItineraryCommand;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;
import voyatrip.command.Parser;
import voyatrip.ui.Ui;

import static voyatrip.command.types.CommandAction.EXIT;

public class VoyaTrip {
    private static final Parser parser = new Parser();
    private static final Scanner in = new Scanner(System.in);
    private static ArrayList<Trip> trips = new ArrayList<>();
    private static Boolean isExit = false;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Ui.printWelcomeMessage();
        while (!isExit) {
            handleInput(readInput());
        }
        Ui.printGoodbyeMessage();
    }


    private static String readInput() {
        return in.nextLine();
    }

    private static void handleInput(String input) {
        try {
            Command command = parser.parse(input);
            handleCommand(command);
        } catch (InvalidCommand e) {
            Ui.printInvalidCommand();
        }
    }

    private static void handleCommand(Command command) throws InvalidCommand {
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

    private static void handleTrip(TripsCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddTrip(command);
        case DELETE -> executeDeleteTrip(command);
        case LIST -> executeListTrip(command);
        default -> throw new InvalidCommand();
        }
    }

    private static void handleItinerary(ItineraryCommand command) throws InvalidCommand {
        if (Objects.requireNonNull(command.getCommandAction()) == CommandAction.LIST) {
            executeListItinerary(command);
        } else {
            throw new InvalidCommand();
        }
    }

    private static void handleActivity(ItineraryCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddActivity(command);
        case DELETE -> executeDeleteActivity(command);
        default -> throw new InvalidCommand();
        }
    }

    private static void handleAccommodation(AccommodationCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddAccommodation(command);
        case DELETE -> executeDeleteAccommodation(command);
        case LIST -> executeListAccommodation(command);
        default -> throw new InvalidCommand();
        }
    }

    private static void handleTransportation(TransportationCommand command) throws InvalidCommand {
        switch (command.getCommandAction()) {
        case ADD -> executeAddTransportation(command);
        case DELETE -> executeDeleteTransportation(command);
        case LIST -> executeListTransportation(command);
        default -> throw new InvalidCommand();
        }
    }

    private static void handleExit() {
        isExit = true;
    }

    private static void executeAddTrip(TripsCommand command) {
        Trip newTrip = new Trip(command.getName(),
                command.getStartDate(),
                command.getEndDate(),
                command.getTotalBudget());
        trips.add(newTrip);
        Ui.printAddTripMessage(newTrip.abbrInfo());
    }

    private static void executeAddActivity(Command command) {
    }

    private static void executeAddAccommodation(Command command) {
    }

    private static void executeAddTransportation(TransportationCommand command) {

        String tripName = command.getTrip();
        String transportMode = command.getMode();
        String transportName = command.getName();
        Integer transportBudget = command.getBudget();

        Trip trip = findTrip(tripName);
        if (trip == null) {
            Ui.printTripNotFound();
        }

        trip.addTransportation(transportMode, transportName, transportBudget);
        Ui.printAddTransportationMessage();

    }

    private static Trip findTrip(String associatedTrip) {
        for (Trip trip : trips) {
            if (trip.getName().equals(associatedTrip)) {
                return trip;
            }
        }
        return null;
    }

    private static void executeDeleteTrip(TripsCommand command) {
        try {
            Trip deletedTrip = trips.get(command.getIndex() - 1);
            trips.remove(command.getIndex() - 1);
            Ui.printDeleteTripMessage(deletedTrip.abbrInfo());
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBounds();
        }
    }

    private static void executeDeleteActivity(Command command) {
    }

    private static void executeDeleteAccommodation(Command command) {
    }

    private static void executeDeleteTransportation(TransportationCommand command) {
        String tripName = command.getTrip();
        Integer transportIndex = command.getIndex();

        try {
            Trip trip = findTrip(tripName);
            if (trip == null) {
                throw new TripNotFoundException();
            }
            trip.deleteTransportation(transportIndex);
            Ui.printDeleteTransportationMessage();
        } catch (TripNotFoundException e) {
            Ui.printTripNotFound();
        }
    }

    private static void executeListTrip(Command command) {
    }

    private static void executeListItinerary(Command command) {
    }

    private static void executeListAccommodation(Command command) {
    }

    private static void executeListTransportation(Command command) {
    }
}
