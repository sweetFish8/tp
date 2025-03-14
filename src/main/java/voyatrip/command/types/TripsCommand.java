package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class TripsCommand extends Command {
    String name;
    String startDate;
    String endDate;
    Integer numDay;
    Integer totalBudget;

    public TripsCommand(String keyword) {
        super(keyword);
    }

    @Override
    public Command tokenizeAdd(String command) throws InvalidCommand {
        String[] hyphenSeparatedTokens = splitCommand(command);
        for (String argument : hyphenSeparatedTokens) {
            String argumentKeyword = argument.split(" ")[0];
            String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();
            switch (argumentKeyword) {
                case "name", "n" -> name = argumentValue;
                case "start", "s" -> startDate = argumentValue;
                case "end", "e" -> endDate = argumentValue;
                case "day", "d" -> numDay = Integer.parseInt(argumentValue);
                case "budget", "b" -> totalBudget = Integer.parseInt(argumentValue);
                default -> throw new InvalidCommand();
            }
        }
        return this;
    }

    @Override
    public Command tokenizeDelete(String command) throws InvalidCommand {
        return this;
    }

    @Override
    public Command tokenizeList(String command) throws InvalidCommand {
        return this;
    }
}
