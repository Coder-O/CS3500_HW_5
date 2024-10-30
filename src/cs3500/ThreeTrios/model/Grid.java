package cs3500.ThreeTrios.model;

import java.util.Map;

/**
 * Represents the playing grid of the ThreeTriosGame.
 */
public class Grid implements ThreeTriosGrid {
  
  /**
   * Gets a reference to the cell at the specified row and index.
   *
   * @param row    The row to get the cell from.
   * @param column The collumn to get the cell from.
   * @return The cell at the specified position. NOTE: Allows for mutation.
   */
  @Override
  public ThreeTriosCell getCell(int row, int column) {
    return null;
  }

  /**
   * Returns the number of rows in the Grid.
   *
   * @return the number of rows in the Grid.
   */
  @Override
  public int getNumRows() {
    return 0;
  }

  /**
   * Returns the number of columns in the Grid.
   *
   * @return the number of columns in the Grid.
   */
  @Override
  public int getNumColumns() {
    return 0;
  }

  /**
   * Returns the North neighbor of a specified Card.
   *
   * @param card whose North neighbor we want.
   * @return a Card.
   */
  @Override
  public ThreeTriosCard getNorthNeighbor(ThreeTriosCard card) {
    return null;
  }

  /**
   * Returns the South neighbor of a specified Card.
   *
   * @param card whose South neighbor we want.
   * @return a Card.
   */
  @Override
  public ThreeTriosCard getSouthNeighbor(ThreeTriosCard card) {
    return null;
  }

  /**
   * Returns the East neighbor of a specified Card.
   *
   * @param card whose East neighbor we want.
   * @return a Card.
   */
  @Override
  public ThreeTriosCard getEastNeighbor(ThreeTriosCard card) {
    return null;
  }

  /**
   * Returns the West neighbor of a specified Card.
   *
   * @param card whose West neighbor we want.
   * @return a Card.
   */
  @Override
  public ThreeTriosCard getWestNeighbor(ThreeTriosCard card) {
    return null;
  }

  /**
   * Returns a map containing all neighbors of this card.
   *
   * @param card The card to find the neighbors for.
   * @return A map containing the neighbors of this card. If a neighbor is missing,
   * this map does not include an entry or key for it.
   */
  @Override
  public Map<ThreeTriosDirection, ThreeTriosCard> getNeighbors(ThreeTriosCard card) {
    return null;
  }
}
