package cs3500.ThreeTrios.model;

/**
* Possible Attack Values for a ThreeTrios Card.
*/
public enum ThreeTriosAttackValue {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    A("A", 10);

    private final String symbol;
    private final int value;

    ThreeTriosAttackValue(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
    * Returns the Symbol of the AttackValue.
    * @return the Symbol of the AttackValue.
    */
    public String getSymbol() {
        return symbol;
    }

    /**
    * Returns the value of the AttackValue.
    * @return the value of the AttackValue.
    */
    public int getValue() {
        return value;
    }

}
