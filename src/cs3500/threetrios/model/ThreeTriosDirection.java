package cs3500.threetrios.model;

/**
 * The directions a ThreeTriosCard can have neighbors in.
 */
public enum ThreeTriosDirection {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

  private final String name;

  ThreeTriosDirection(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the Direction.
   * @return the name of the Direction.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the opposite direction of this direction.
   * @return The opposite direction.
   */
  public ThreeTriosDirection getOpposite() {
    switch (this) {
        case NORTH:
            return SOUTH;
        case EAST:
            return WEST;
        case SOUTH:
            return NORTH;
        case WEST:
            return EAST;
        default:
            throw new IllegalStateException(
                "Somehow, a ThreeTriosDirection was not one of its enumerated possibilities.");
    }
  }
}
