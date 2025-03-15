package voyatrip.command;

import voyatrip.command.exceptions.InvalidCommand;
import voyatrip.command.types.TransportationCommand;
import voyatrip.command.types.TripsCommand;

public class Test {
    private Parser parser = new Parser();

    private void run() {
        try {
            System.out.println("1");
            System.out.println(((TripsCommand) parser.parse("add trip --name Hi hi hi")).getName());
            System.out.println("2");
            parser.parse("cd trip --index 2");
            System.out.println("3.1");
            parser.parse("cd itinerary");
            System.out.println("3.2");
            parser.parse("add activity --name testing testing --time sth");
            System.out.println("3.3");
            parser.parse("add trip --name home");
            System.out.println("3.4");
            parser.parse("add trip --name all");
            System.out.println("3.5");
            parser.parse("add trip --name me al");
            System.out.println("3.6");
            parser.parse("add accom --name coool");
            System.out.println("3.7");
            parser.parse("add transportation --name me al");
            System.out.println("3.8");
            parser.parse("add trip--name sth");
            System.out.println("3.9");
            parser.parse("add trip--name --budget 123");
            System.out.println("4");
            parser.setCurrentPage("itinerary");
            System.out.println("5");
            parser.parse("cd itinerary");
            System.out.println("6");
            parser.parse("cd accommodation");
            System.out.println("7");
            System.out.println(((TransportationCommand) parser.parse("delete transportation --name bruh")).getName());
            System.out.println("8");
            parser.parse("list transportation");
        } catch (InvalidCommand e) {
            System.out.println("Invalid command");
        }
    }

    public static void main(String[] args) {
        Test tester = new Test();
        tester.run();
    }
}
