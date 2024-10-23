/**
 * Represents the rules of battle for a game of ThreeTrios.
 */
interface BattleRules {

    /**
     * Compares the attacker to the defender, and returns which one wins.
     * Also flips the provided cards, according to rules that vary depending upon the implmentation.
     */
    public ThreeTriosCard compare(ThreeTriosCard attacker, ThreeTriosCard defender, Direction direction);
}
