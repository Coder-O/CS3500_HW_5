package cs3500.threetrios.model;

/**
* Represents a Card in the ThreeTrios Game.
 * Every card has a unique name.
*/
public interface ThreeTriosCard {

    /**
     * Gets the northward facing attackValue of this card.
     * @return The northward facing attackValue of this card.
     */
    public ThreeTriosAttackValue getNorth();
    
    /**
     * Gets the eastward facing attackValue of this card.
     * @return The eastward facing attackValue of this card
     */
    public ThreeTriosAttackValue getEast();

    /**
     * Gets the southward facing attackValue of this card.
     * @return The southward facing attackValue of this card.
     */
    public ThreeTriosAttackValue getSouth();

    /**
     * Gets the westward facing attackValue of this card.
     * @return The westward facing attackValue of this card.
     */
    public ThreeTriosAttackValue getWest();

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
}
