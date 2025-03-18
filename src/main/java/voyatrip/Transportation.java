package voyatrip;

public class Transportation {
    private String mode;
    private String name;
    private Integer budget;

    public Transportation(String mode, String name, Integer budget) {
        this.mode = mode;
        this.name = name;
        this.budget = budget;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
