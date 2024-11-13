package cs3500.threetrios.model;

/**
* Represents a Card in the ThreeTrios Game.
 * Every card has a unique name.
*/
public interface ThreeTriosCard {

    /**
    * Returns the Card's Player.
    * @return the player of the Card.
    */
    public ThreeTriosPlayer getPlayer();

    /**
    * Returns the Card's Name.
    * @return the player of the Card.
    */
    public String getName();

    /**
    * Flips the Card's Player to the other one.
    */
    public void changePlayer();

    void setPlayer(ThreeTriosPlayer player);

    /**
     * Gets the attack value for the corresponding direction.
     * @return The attack value for the corresponding direction.
     */
    public ThreeTriosAttackValue getAttackValue(ThreeTriosDirection direction);

    /**
    * Prints the card.
    * Ex: card1 7 3 9 A
    * @return a textual representation of the card
    */
    public String toString();


    /**
     * Returns a deep copy of this card. Mutating this has no effect on the original card.
     * @return A deep copy of this card.
     */
    ThreeTriosCard copy();
}
