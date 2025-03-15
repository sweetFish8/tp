package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class AccommodationCommand extends Command {
    String trip;
    String name;
    Integer budget;

    Integer index;

    public AccommodationCommand(String keyword, String trip, String rawArgument) throws InvalidCommand {
        super(keyword);
        this.trip = trip;
        name = null;
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

    public Integer getBudget() {
        return budget;
    }

    public Integer getIndex() {
        return index;
    }
}
