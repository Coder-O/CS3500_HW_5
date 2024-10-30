package cs3500.ThreeTrios.model;

import java.util.Objects;

public class Card implements ThreeTriosCard {

    private final ThreeTriosAttackValue north;
    private final ThreeTriosAttackValue east;
    private final ThreeTriosAttackValue south;
    private final ThreeTriosAttackValue west;
    private ThreeTriosPlayer player;

    /**
     * Card constructor.
     */
    public Card(ThreeTriosAttackValue north,
    ThreeTriosAttackValue east,
    ThreeTriosAttackValue west,
    ThreeTriosAttackValue south,
    ThreeTriosPlayer player) {
        this.north = north;
        this.east = east;
        this.west = west;
        this.south = south;
        this.player = player;
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
     * Gets the attack value for the corresponding direction
     * @param direction The direction to get the attack value for.
     * @return The attack value for the corresponding direction.
     */
    @Override
    public ThreeTriosAttackValue getAttackValue(ThreeTriosDirection direction) {
        switch (direction) {
          case NORTH -> {
              return this.north;
          }
          case EAST -> {
              return this.east;
          }
          case SOUTH -> {
              return this.south;
          }
          case WEST -> {
              return this.west;
          }
        }
        throw new IllegalArgumentException(
                "Somehow, the direction parameter is not one of the enum values..."
        );
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
//        return this.north == otherCard.getNorth()
//        && this.east == otherCard.getEast()
//        && this.west == otherCard.getWest()
//        && this.south == otherCard.getSouth()
//        && this.player == otherCard.getPlayer();
      }
      return false;
    }
    
}
