package cs3500.threetrios.model;

/**
* Possible Players for a ThreeTrios Game.
*/
public enum ThreeTriosPlayer {
    RED("R"),
    BLUE("B");

    private final String symbol;

    ThreeTriosPlayer(String symbol) {
        this.symbol = symbol;
    }

    /**
    * Returns the Symbol of the Player.
    * @return the Symbol of the Player.
    */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the full name of the player.
     * @return "RED" for RED and "BLUE" for BLUE.
     */
    public String getName() {
        switch (this) {
            case RED:
                return "RED";
            case BLUE:
                return "BLUE";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

}
