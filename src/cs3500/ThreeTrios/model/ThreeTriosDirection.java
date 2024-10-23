
/**
 * The directions a ThreeTriosCard can have neighbors in.
 */
public enum ThreeTriosDirection {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private final String name;

    ThreeTriosAttackValues(String name) {
        this.name = name;
    }

    /**
    * Returns the name of the Direction.
    * @return the name of the Direction.
    */
    public String getName() {
        return name;
    }
}
