package cs3500.ThreeTrios.model;

import java.util.Objects;

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
    ThreeTriosPlayer player, String name) {
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
        if (this.player == ThreeTriosPlayer.ONE) {
            this.player = ThreeTriosPlayer.TWO;
        } else {
            this.player = ThreeTriosPlayer.ONE;
        }
    }

    /**
    * Prints the values of the card.
    * Ex: 7 3 9 A
    * @return a four character representation of the card
    */
    @Override
    public String toString() {
        return this.north.getSymbol() + this.east.getSymbol() + this.west.getSymbol() + this.south.getSymbol();
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
        && this.name == otherCard.getName();
      }
      return false;
    }

    /**
     * Gets the attack value for the corresponding direction.
     * @return The attack value for the corresponding direction.
     */
    public ThreeTriosAttackValue getAttackValue(ThreeTriosDirection direction) {
        if (direction.getName() == "north") {
            return this.getNorth();
        }
        if (direction.getName() == "east") {
            return this.getEast();
        }
        if (direction.getName() == "west") {
            return this.getWest();
        }
        else {
            return this.getSouth();
        }
    }
    
}
