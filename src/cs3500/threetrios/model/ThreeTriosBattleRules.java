package cs3500.threetrios.model;

/**
 * Represents the rules of battle for a game of ThreeTrios.
 */
public interface ThreeTriosBattleRules {

    /**
     * Handles the battling and flipping of cards after a card has been played.
     * Details of interactions vary depending upon the implementation.
     * @param cardPlayed The card that was played this turn.
     * @param grid The grid the card is in.
     * @return the score resulting from the battle.
     */
    int battle(ThreeTriosCard cardPlayed, ThreeTriosGrid grid);
}
