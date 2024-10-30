package cs3500.ThreeTrios.model;

/**
* Represents a Card in the ThreeTrios Game.
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

    /**
     * Gets the attack value for the corresponding direction.
     * @return The attack value for the corresponding direction.
     */
    public ThreeTriosAttackValue getAttackValue(ThreeTriosDirection direction);
}
