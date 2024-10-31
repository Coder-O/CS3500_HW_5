package cs3500.ThreeTrios.model;

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

}
