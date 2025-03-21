package voyatrip;

public class Activity {
    private String name;
    private String time;

    public Activity(String name, String time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return name + " at " + time;
    }
}
