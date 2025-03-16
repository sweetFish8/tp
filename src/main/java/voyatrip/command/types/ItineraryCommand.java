package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class ItineraryCommand extends Command {
    String trip;
    String name;
    String time;
    Integer day;
    Integer index;

    public ItineraryCommand(CommandAction commandAction,
                            CommandTarget commandTarget,
                            String trip,
                            String rawArgument) throws InvalidCommand {
        super(commandAction, commandTarget);
        this.trip = trip;
        name = null;
        time = null;
        day = null;
        index = null;

        processRawArgument(rawArgument);
    }

    @Override
    protected void matchArgument(String argument) throws InvalidCommand {
        String argumentKeyword = argument.split(" ")[0];
        String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();

        switch (argumentKeyword) {
        case "name", "n" -> name = argumentValue;
        case "time", "t" -> time = argumentValue;
        case "day", "d" -> day = Integer.parseInt(argumentValue);
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

    public String getTime() {
        return time;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getIndex() {
        return index;
    }
}
