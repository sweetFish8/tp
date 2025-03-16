package voyatrip.command.types;

import java.util.Arrays;

import voyatrip.command.exceptions.InvalidCommand;

public class TripsCommand extends Command {
    static final String[] INVALID_NAMES = {"home", "all"};

    private String name;
    private String startDate;
    private String endDate;
    private Integer numDay;
    private Integer totalBudget;
    private Integer index;

    public TripsCommand(CommandAction commandAction,
                        CommandTarget commandTarget,
                        String rawArgument) throws InvalidCommand {
        super(commandAction, commandTarget);
        name = null;
        startDate = null;
        endDate = null;
        numDay = null;
        totalBudget = null;
        index = null;

        processRawArgument(rawArgument);

        if (isInvalidCommand()) {
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

    private boolean isInvalidCommand() {
        boolean isInvalidName = name == null || Arrays.asList(INVALID_NAMES).contains(name);
        boolean isInvalidAdd = isInvalidName || startDate == null || endDate == null || totalBudget == null;
        boolean isInvalidDelete = isInvalidName && index == null;

        return switch (commandAction) {
            case ADD -> isInvalidAdd;
            case DELETE -> isInvalidDelete;
            case LIST, CHANGE_DIRECTORY -> isInvalidName;
            case EXIT -> false;
            default -> true;
        };
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
