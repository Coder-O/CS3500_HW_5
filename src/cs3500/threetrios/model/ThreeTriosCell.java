package cs3500.threetrios.model;

/**
 * Represents a cell in a game of ThreeTrios.
 * Each cell can be a hole or hold a card.
 * Class Invariant: game must have an odd number of cells.
 */
public interface ThreeTriosCell {

  /**
   * Returns true if this card is a hole.
   * @return true if this card is a hole.
   */
  boolean isHole();

  /**
   * Returns the card of this cell, if it isn't a hole.
   * @return The card of this cell.
   * @throws IllegalStateException If the cell is a hole.
   */
  ThreeTriosCard getCard();

  /**
   * Adds the specified card to this cell, if it isn't a hole and there isn't already a card here.
   * @param card The card to place in the cell.
   * @throws IllegalStateException If the cell is a hole.
   * @throws IllegalStateException If this cell already holds a card.
   */
  void setCard(ThreeTriosCard card) throws IllegalStateException;


  /**
   * Returns a depp copy of this cell. Mutating this has no effect on the original cell.
   * @return A deep copy of this cell.
   */
  ThreeTriosCell copy();
}
