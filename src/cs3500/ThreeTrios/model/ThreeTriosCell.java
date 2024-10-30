package cs3500.ThreeTrios.model;

/**
 * Represents a cell in a game of ThreeTrios.
 * Each cell can be a hole or hold a card.
 */
interface ThreeTriosCell {

  /**
   * Returns whether or not this card is a hole.
   * @return Whether this card is a hole.
   */
  boolean isHole();

  /**
   * Returns the card of this cell, if it isn't a hole.
   * @return The card of this cell.
   * @throws IllegalStateException If the cell is a hole.
   */
  ThreeTriosCard getCard();

  /**
   * Adds the specified card to this cell, if it isn't a hole.
   * This will overwrite whatever card might have been there before.
   * @throws IllegalStateException If the cell is a hole.
   */
  ThreeTriosCard setCard(ThreeTriosCard card);
}
