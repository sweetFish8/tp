package voyatrip.command.types;

import voyatrip.command.exceptions.InvalidCommand;

abstract public class Command {
    String keyword;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public String[] splitCommand(String command) {
        command = command.strip();
        return command.split(" --(?=\\w)");
    }

    abstract public Command tokenizeAdd(String command) throws InvalidCommand;

    abstract public Command tokenizeDelete(String command) throws InvalidCommand;

    abstract public Command tokenizeList(String command) throws InvalidCommand;
}
