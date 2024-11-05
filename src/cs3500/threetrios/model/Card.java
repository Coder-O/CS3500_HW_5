package cs3500.threetrios.model;

import java.util.Objects;

/**
 * Represents a Card in the game of ThreeTrios.
 * A Card must have four values, a player, and a name.
 */
public class Card implements ThreeTriosCard {
    
    private final ThreeTriosAttackValue north;
    private final ThreeTriosAttackValue east;
    private final ThreeTriosAttackValue south;
    private final ThreeTriosAttackValue west;
    private ThreeTriosPlayer player;
    private final String name;

    /**
     * Card constructor.
     */
    public Card(ThreeTriosAttackValue north,
    ThreeTriosAttackValue east,
    ThreeTriosAttackValue west,
    ThreeTriosAttackValue south,
    ThreeTriosPlayer player,
    String name) {
        this.north = north;
        this.east = east;
        this.west = west;
        this.south = south;
        this.player = player;
        this.name = name;
    }

    /**
     * Gets the northward facing attackValue of this card.
     * @return The northward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValue getNorth() {
        return this.north;
    }
    
    /**
     * Gets the eastward facing attackValue of this card.
     * @return The eastward facing attackValue of this card
     */
    @Override
    public ThreeTriosAttackValue getEast() {
        return this.east;
    }

    /**
     * Gets the southward facing attackValue of this card.
     * @return The southward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValue getSouth() {
        return this.south;
    }

    /**
     * Gets the westward facing attackValue of this card.
     * @return The westward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValue getWest() {
        return this.west;
    }

    /**
    * Returns the Card's Player.
    * @return the player of the Card.
    */
    @Override
    public ThreeTriosPlayer getPlayer() {
        return this.player;
    }

    /**
    * Returns the Card's Name.
    * @return the player of the Card.
    */
    public String getName() {
        return this.name;
    }

    /**
    * Flips the Card's Player to the other one.
    */
    @Override
    public void changePlayer() {
        if (this.player == ThreeTriosPlayer.RED) {
            this.player = ThreeTriosPlayer.BLUE;
        } else {
            this.player = ThreeTriosPlayer.RED;
        }
    }

    /**
     * Sets this card's player to given player
     * @param player The player that shall now own this card.
     */
    @Override
    public void setPlayer(ThreeTriosPlayer player) {
        this.player = player;
    }

    /**
     * Gets the attack value for the corresponding direction.
     * @param direction The direction to get the attack value for.
     * @return The attack value for the corresponding direction.
     */
    @Override
    public ThreeTriosAttackValue getAttackValue(ThreeTriosDirection direction) {
        switch (direction) {
            case NORTH:
            return this.north;
            case EAST:
            return this.east;
            case SOUTH:
            return this.south;
            case WEST:
            return this.west;
            default:
            throw new IllegalStateException(
                "Somehow, a ThreeTriosDirection was not one of its enumerated possibilities.");
            }
    }

    /**
    * Prints the card.
    * Ex: card1 7 3 9 A
    * @return a textual representation of the card
    */
    @Override
    public String toString() {
        return this.getName() + " " 
        + this.north.getSymbol() + " " 
        + this.east.getSymbol() + " "
        + this.west.getSymbol()  + " "
        + this.south.getSymbol();
    }

    /**
     * Returns a depp copy of this card. Mutating this has no effect on the original card.
     *
     * @return A deep copy of this card.
     */
    @Override
    public ThreeTriosCard copy() {
        return new Card(
                north,
                east,
                west,
                south,
                player,
                name
        );
    }

    /*
     * Override hashCode.
     */
    @Override
    public int hashCode() {
      return Objects.hash(north, east, west, south, player);
    }
  
    /*
     * Checks if this Card is equal to another Card.
     * @return true if both cards are equal.
     */
    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Card) {
        Card otherCard = (Card) obj;
        return this.north == otherCard.getNorth()
        && this.east == otherCard.getEast()
        && this.west == otherCard.getWest() 
        && this.south == otherCard.getSouth() 
        && this.player == otherCard.getPlayer()
        && Objects.equals(this.name, otherCard.getName());
      }
      return false;
    }
    
}
