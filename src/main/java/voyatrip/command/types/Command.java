package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

abstract public class Command {
    String keyword;

    public Command(String keyword) {
        this.keyword = keyword;
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

    abstract protected void matchArgument(String argument) throws InvalidCommand;

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return keyword.equals(command.keyword);
    }
}
