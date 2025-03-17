package voyatrip;

public class Transportation {
    private String mode;
    private Integer budget;
    private String name;

    public Transportation(String name, String mode) {
        this.name = name;
        this.mode = mode;
    }

    public Integer getBudget() {
        return budget;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

}
