package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

public abstract class Command {
    CommandAction commandAction;
    CommandTarget commandTarget;

    public Command(CommandAction commandAction, CommandTarget commandTarget) {
        this.commandAction = commandAction;
        this.commandTarget = commandTarget;
    }

    protected String[] splitByDoubleHyphen(String command) {
        command = command.strip();
        return command.split("(^--| --)(?=\\w+ \\w+)");
    }

    protected void processRawArgument(String rawArgument) throws InvalidCommand {
        String[] hyphenSeparatedTokens = splitByDoubleHyphen(rawArgument);
        for (String argument : hyphenSeparatedTokens) {
            if (!argument.isEmpty()) {
                matchArgument(argument);
            }
        }
    }

    protected abstract void matchArgument(String argument) throws InvalidCommand;

    public CommandAction getCommandAction() {
        return commandAction;
    }

    public CommandTarget getCommandTarget() {
        return commandTarget;
    }
}
