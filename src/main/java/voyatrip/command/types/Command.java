package voyatrip.command.types;

abstract public class Command {
    String keyword;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    protected String[] splitByDoubleHyphen(String command) {
        command = command.strip();
        return command.split(" --(?=\\w)");
    }

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
