package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public class AccommodationCommand extends Command {
    String name;
    Integer budget;

    public AccommodationCommand(String keyword) {
        super(keyword);
    }

    @Override
    public Command tokenizeAdd(String command) throws InvalidCommand {
        String[] hyphenSeparatedTokens = splitCommand(command);
        for (String argument : hyphenSeparatedTokens) {
            String argumentKeyword = argument.split(" ")[0];
            String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();
            switch (argumentKeyword) {
                case "name", "n" -> this.name = argumentValue;
                case "budget", "b" -> this.budget = Integer.parseInt(argumentValue);
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
