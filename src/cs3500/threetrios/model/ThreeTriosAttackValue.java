package cs3500.threetrios.model;

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

    /**
     * A factory for ThreeTriosAttackValues.
     * Given a sting, returns a version of this enum.
     * @param symbol The identifier for the desired enum. Symbols should be 1 character long.
     *               Valid symbols are: '1', '2', '3', '4', '5', '6', '7', '8','9', and 'A'.
     * @return A value of this enum, determined by the symbol.
     * @throws IllegalArgumentException if an invalid symbol is passed.
     */
    public static ThreeTriosAttackValue attackValueFactory(
            String symbol) throws IllegalArgumentException {
        switch (symbol) {
            case "1" :
                return ONE;
            case "2" :
                return TWO;
            case "3" :
                return THREE;
            case "4" :
                return FOUR;
            case "5" :
                return FIVE;
            case "6" :
                return SIX;
            case "7" :
                return SEVEN;
            case "8" :
                return EIGHT;
            case "9" :
                return NINE;
            case "A":
                return A;
            default:
                throw new IllegalArgumentException("No valid symbol was given.");
        }
    }

}
