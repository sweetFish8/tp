package voyatrip.command.types;

import java.util.Arrays;

import voyatrip.command.exceptions.InvalidCommand;

public class TripsCommand extends Command {
    final String[] INVALID_NAMES = {"home", "all"};

    String name;
    String startDate;
    String endDate;
    Integer numDay;
    Integer totalBudget;

    Integer index;

    public TripsCommand(String keyword, String rawArgument) throws InvalidCommand {
        super(keyword);
        name = null;
        startDate = null;
        endDate = null;
        numDay = null;
        totalBudget = null;
        index = null;

        processRawArgument(rawArgument);

        if (Arrays.asList(INVALID_NAMES).contains(name)) {
            throw new InvalidCommand();
        }
    }

    @Override
    protected void matchArgument(String argument) throws InvalidCommand {
        String argumentKeyword = argument.split(" ")[0];
        String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();

        switch (argumentKeyword) {
        case "name", "n" -> name = argumentValue;
        case "start", "s" -> startDate = argumentValue;
        case "end", "e" -> endDate = argumentValue;
        case "day", "d" -> numDay = Integer.parseInt(argumentValue);
        case "budget", "b" -> totalBudget = Integer.parseInt(argumentValue);
        case "index", "i" -> index = Integer.parseInt(argumentValue);
        default -> throw new InvalidCommand();
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getNumDay() {
        return numDay;
    }

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public Integer getIndex() {
        return index;
    }
}
