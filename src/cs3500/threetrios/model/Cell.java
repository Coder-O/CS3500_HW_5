package cs3500.threetrios.model;

/**
 * A cell for a game of three trios. Represents a location on the grid,
 * and is either a hole or a card cell.
 */
class Cell implements ThreeTriosCell {
  private final boolean isHole;
  private ThreeTriosCard card;

  /**
   * Creates a new card cell.
   * @param card The card to place in the cell.
   */
  Cell(ThreeTriosCard card) {
    this.isHole = false;
    this.card = card;
  }

  /**
   * Creates a new cell.
   * @param isHole Whether the cell is a hole.
   */
  Cell(boolean isHole) {
    this.isHole = isHole;
    this.card = null;
  }

  /**
   * Creates a new card cell.
   */
  Cell() {
    this.isHole = false;
    this.card = null;
  }

  /**
   * Creates a new card cell.
   * @param isHole Whether this cell is a hole.
   * @param card The card to place in the cell.
   */
  private Cell(boolean isHole, ThreeTriosCard card) {
    this.isHole = isHole;
    this.card = card;
  }


  /**
   * Returns whether or not this card is a hole.
   *
   * @return Whether this card is a hole.
   */
  @Override
  public boolean isHole() {
    return this.isHole;
  }

  /**
   * Returns the card of this cell, if it isn't a hole.
   * @return The card of this cell. May be null.
   * @throws IllegalStateException If the cell is a hole.
   */
  @Override
  public ThreeTriosCard getCard() throws IllegalStateException {
    if (isHole) {
      throw new IllegalStateException("This cell is a hole! It has no card!!!");
    }
    if (card == null) {
      return null;
    }
    return card;
  }

  /**
   * Adds the specified card to this cell, if it isn't a hole and there isn't already a card here.
   * @param card The card to place in the cell.
   * @throws IllegalStateException If the cell is a hole.
   * @throws IllegalStateException If this cell already holds a card.
   */
  @Override
  public void setCard(ThreeTriosCard card) throws IllegalStateException {
    if (isHole || this.card != null) {
      throw new IllegalStateException("Cannot add a card to this cell!");
    }

    this.card = card;
  }

  /**
   * Returns a copy of this cell.
   *
   * @return A copy of this cell.
   */
  @Override
  public ThreeTriosCell copy() {
    if (card != null) {
      return new Cell(
              isHole,
              card.copy()
      );
    }
    return new Cell(
            isHole,
            null
    );
  }
}
