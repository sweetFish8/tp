package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class TransportationCommand extends Command {
    private String trip;
    private String name;
    private String mode;
    private Integer budget;
    private Integer index;

    public TransportationCommand(CommandAction commandAction,
                                 CommandTarget commandTarget,
                                 String trip,
                                 String rawArgument) throws InvalidCommand {
        super(commandAction, commandTarget);
        this.trip = trip;
        name = null;
        mode = null;
        budget = null;
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

        try {
            switch (argumentKeyword) {
            case "name", "n" -> name = argumentValue;
            case "mode", "m" -> mode = argumentValue;
            case "budget", "b" -> budget = Integer.parseInt(argumentValue);
            case "index", "i" -> index = Integer.parseInt(argumentValue);
            default -> throw new InvalidCommand();
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommand();
        }
    }

    private boolean isInvalidCommand() {
        boolean isInvalidName = name == null;
        boolean isInvalidAdd = isInvalidName || mode == null || budget == null;
        boolean isInvalidDelete = isInvalidName && index == null;

        return switch (commandAction) {
            case ADD -> isInvalidAdd;
            case DELETE -> isInvalidDelete;
            case LIST, CHANGE_DIRECTORY, EXIT -> false;
            default -> true;
        };
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
