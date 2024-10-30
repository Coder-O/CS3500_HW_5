package cs3500.ThreeTrios.model;

import java.util.Objects;

public class Card implements ThreeTriosCard {

    private final ThreeTriosAttackValues north;
    private final ThreeTriosAttackValues east;
    private final ThreeTriosAttackValues south;
    private final ThreeTriosAttackValues west;
    private ThreeTriosPlayer player;

    /**
     * Card constructor.
     */
    public Card(ThreeTriosAttackValues north, 
    ThreeTriosAttackValues east, 
    ThreeTriosAttackValues west, 
    ThreeTriosAttackValues south, 
    ThreeTriosPlayer player) {
        this.north = north;
        this.east = east;
        this.west = west;
        this.south = south;
        this.player = player;
    }

    /**
     * Gets the northward facing attackValue of this card.
     * @return The northward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValues getNorth() {
        return this.north;
    }
    
    /**
     * Gets the eastward facing attackValue of this card.
     * @return The eastward facing attackValue of this card
     */
    @Override
    public ThreeTriosAttackValues getEast() {
        return this.east;
    }

    /**
     * Gets the southward facing attackValue of this card.
     * @return The southward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValues getSouth() {
        return this.south;
    }

    /**
     * Gets the westward facing attackValue of this card.
     * @return The westward facing attackValue of this card.
     */
    @Override
    public ThreeTriosAttackValues getWest() {
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
    * Flips the Card's Player to the other one.
    */
    @Override
    public void changePlayer() {
        //todo
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
        && this.player == otherCard.getPlayer();
      }
      return false;
    }
    
}
