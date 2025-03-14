package voyatrip.command.types;

import java.util.HashMap;

import voyatrip.command.Parser;
import voyatrip.command.exceptions.InvalidCommand;

public class TripsParser extends Parser {
    private HashMap<String, String> tokenizeAdd(String command) throws InvalidCommand {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("command", "trip add");
        String[] hyphenSeparatedTokens = command.split("-");
        for (String argument : hyphenSeparatedTokens) {
            String argumentKeyword = argument.split(" ")[0];
            String argumentValue = argument.replaceFirst(argumentKeyword, "").strip();
            tokens.put(argumentKeyword, argumentValue);
        }
        return tokens;
    }

    private HashMap<String, String> tokenizeDelete(String command) throws InvalidCommand {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("command", "trip delete");
        tokens.put("index", command.split(" ")[2]);
        return tokens;
    }

    private HashMap<String, String> tokenizeList(String command) throws InvalidCommand {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("command", "trip list");
        return tokens;
    }

    @Override
    public HashMap<String, String> tokenize(String command) throws InvalidCommand {
        String[] spaceSeparatedTokens = command.split(" ");
        return switch (spaceSeparatedTokens[1]) {
            case "add" -> tokenizeAdd(command);
            case "delete" -> tokenizeDelete(command);
            case "list" -> tokenizeList(command);
            default -> throw new InvalidCommand();
        };
    }
}
