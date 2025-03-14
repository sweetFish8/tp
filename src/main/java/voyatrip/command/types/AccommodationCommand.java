package voyatrip.command.types;

import java.util.HashMap;

import voyatrip.command.Parser;
import voyatrip.command.exceptions.InvalidCommand;

public class AccommodationParser extends Parser {
    private HashMap<String, String> tokenizeAdd() throws InvalidCommand {

    }

    private HashMap<String, String> tokenizeDelete() throws InvalidCommand {

    }

    private HashMap<String, String> tokenizeList() throws InvalidCommand {

    }

    @Override
    public HashMap<String, String> tokenize(String command) throws InvalidCommand {
        String[] spaceSeparatedTokens = command.split(" ");
        return switch (spaceSeparatedTokens[1]) {
            case "add" -> tokenizeAdd();
            case "delete" -> tokenizeDelete();
            case "list" -> tokenizeList();
            default -> throw new InvalidCommand();
        };
    }
}
