package weiss.harrypotter;

public class Spell {
    private String id;
    private String name;
    private String incantation;
    private Boolean canBeVerbal;
    private String type;
    private String light;
    private String creator;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIncantation() {
        return incantation;
    }

    public Boolean getCanBeVerbal() {
        return canBeVerbal;
    }

    public String getType() {
        return type;
    }

    public String getLight() {
        return light;
    }

    public String getCreator() {
        return creator;
    }
}
