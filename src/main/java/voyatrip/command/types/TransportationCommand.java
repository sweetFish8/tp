package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class TransportationCommand extends Command {
    String trip;
    String name;
    String mode;
    Integer budget;

    Integer index;

    public TransportationCommand(String keyword, String trip, String rawArgument) throws InvalidCommand {
        super(keyword);
        this.trip = trip;
        name = null;
        mode = null;
        budget = null;
        index = null;

        processRawArgument(rawArgument);
    }

    @Override
    protected void matchArgument(String argument) throws InvalidCommand {
        String argumentKeyword = argument.split(" ")[0];
        String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();

        switch (argumentKeyword) {
        case "name", "n" -> name = argumentValue;
        case "mode", "m" -> mode = argumentValue;
        case "budget", "b" -> budget = Integer.parseInt(argumentValue);
        case "index", "i" -> index = Integer.parseInt(argumentValue);
        default -> throw new InvalidCommand();
        }
    }

    public String getTrip() {
        return trip;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public Integer getBudget() {
        return budget;
    }

    public Integer getIndex() {
        return index;
    }
}
