package cs3500.ThreeTrios.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the playing grid of the ThreeTriosGame.
 */
public class Grid implements ThreeTriosGrid {

  private final ThreeTriosCell[][] grid;
  private final int rows;
  private final int columns;
  private final int numCardCells;
  /* INVARIANT: the number of card cells is odd */
  private int numCards;

  /**
   * Creates a new grid from the given parameter.
   * This is only intended to be used by a builder class,
   * users should use the builder to construct this object
   * @param grid The array for this grid to store.
   * @throws IllegalArgumentException if the number of card cells is not odd.
   */
  Grid(ThreeTriosCell[][] grid) throws IllegalArgumentException{
    this.rows = grid.length;
    this.columns = grid[0].length;

    int numCardCells = 0;
    this.numCards = 0;
    this.grid = new ThreeTriosCell[rows][columns];
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        this.grid[row][column] = grid[row][column];
        if (!this.grid[row][column].isHole()) {
          ++numCardCells;
          if (this.grid[row][column].getCard() != null) {
            ++numCards;
          }
        }
      }
    }
    if (numCardCells % 2 != 1) {
      throw new IllegalArgumentException(
              "The number of card cells is " + numCardCells + ", which is not odd!!"
      );
    }
    this.numCardCells = numCardCells;
  }

  /**
   * Gets a reference to the cell at the specified row and index.
   * This grid is 0-indexed
   * @param row    The row to get the cell from.
   * @param column The collumn to get the cell from.
   * @return The cell at the specified position. NOTE: Allows for mutation.
   * @throws IllegalArgumentException If the given indices are outside the grid's range.
   */
  @Override
  public ThreeTriosCell getCell(int row, int column) throws IllegalArgumentException {
    if (row > rows || row < 0) {
      throw new IllegalArgumentException(
              row + " is an invalid row value!!! Valid indices are: 0-" + rows
      );
    }
    if (column > columns || column < 0) {
      throw new IllegalArgumentException(
              column + " is an invalid column value!!! Valid indices are: 0-" + columns
      );
    }

    return grid[row][column];
  }

  /**
   * Returns the number of rows in the Grid.
   *
   * @return the number of rows in the Grid.
   */
  @Override
  public int getNumRows() {
    return rows;
  }

  /**
   * Returns the number of columns in the Grid.
   *
   * @return the number of columns in the Grid.
   */
  @Override
  public int getNumColumns() {
    return columns;
  }

  /**
   * Returns the number of card cells in the grid, which is always odd.
   *
   * @return the number of card cells in the grid, which is always odd.
   */
  @Override
  public int getNumCardCells() {
    return numCardCells;
  }

  /**
   * Returns the number of cards in the grid.
   * @return the number of cards in the grid.
   */
  int getNumCards() {
    return numCards;
  }

  /**
   * Returns a map containing all neighbors of this card.
   *
   * @param card The card to find the neighbors for.
   * @return A map containing the neighbors of this card. If a neighbor is missing,
   * this map does not include an entry or key for it.
   * @throws IllegalArgumentException If the card does not appear in the grid.
   */
  @Override
  public Map<ThreeTriosDirection, ThreeTriosCard> getNeighbors(
          ThreeTriosCard card
  ) throws IllegalArgumentException {
    // Relies on the model's invariant that all card names are unique.
    ThreeTriosCell currentCell;

    // finds the location of the card and gets its neighbors from the helper method.
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        currentCell = grid[row][column];
        if (
                !currentCell.isHole()
                        && currentCell.getCard().getName().equals(card.getName())
        ) {
          return helpGetNeighbors(row, column);
        }
      }
    }
    throw new IllegalArgumentException(
            "The card '" + card.getName() + "' was not found in the grid."
    );
  }

  /**
   * Helper method for getNeighbors.
   * @param row The row of the card to get neighbors for.
   * @param column The column of the card to get neighbors for.
   * @return A map containing the neighbors of this card. If a neighbor is missing,
   * this map does not include an entry or key for it.
   */
  private Map<ThreeTriosDirection, ThreeTriosCard> helpGetNeighbors(int row, int column) {
    Map<ThreeTriosDirection, ThreeTriosCard> toReturn = new HashMap<>();

    ThreeTriosCell currentCell;

    // check each direction and add the card int that direction to the map if appropriate.

    if (row > 0) {
      currentCell = grid[row - 1][column];
      if (!currentCell.isHole() && currentCell.getCard() != null) {
        toReturn.put(ThreeTriosDirection.NORTH, currentCell.getCard());
      }
    }

    if (row + 1 < rows) {
      currentCell = grid[row + 1][column];
      if (!currentCell.isHole() && currentCell.getCard() != null) {
        toReturn.put(ThreeTriosDirection.SOUTH, currentCell.getCard());
      }
    }

    if (column > 0) {
      currentCell = grid[row][column - 1];
      if (!currentCell.isHole() && currentCell.getCard() != null) {
        toReturn.put(ThreeTriosDirection.WEST, currentCell.getCard());
      }
    }

    if (column + 1 < columns) {
      currentCell = grid[row][column + 1];
      if (!currentCell.isHole() && currentCell.getCard() != null) {
        toReturn.put(ThreeTriosDirection.EAST, currentCell.getCard());
      }
    }

    return toReturn;
  }
}
