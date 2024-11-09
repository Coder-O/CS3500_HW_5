package cs3500.threetrios.model;

/**
 * A builder for {@link Grid}.
 */
public class GridBuilder implements ThreeTriosGridBuilder<Grid> {
  private final ThreeTriosCell[][] grid;
  /* INVARIANT: All card names are unique. */
  private final int rows;
  private final int columns;
  private final ThreeTriosCellBuilder cellBuilder;

  /**
   * Creates a builder for a Grid.
   * @param rows The number of rows the grid should be.
   * @param columns the number of columns the grid should be.
   * @throws IllegalArgumentException If the rows or columns are not positive.
   */
  public GridBuilder(
          int rows,
          int columns,
          ThreeTriosCellBuilder cellBuilder
  ) throws IllegalArgumentException {
    if (rows <= 0 || columns <= 0) {
      throw new IllegalArgumentException("Must have a positive number of rows and columns!!!!");
    }

    grid = new ThreeTriosCell[rows][columns];
    this.rows = rows;
    this.columns = columns;
    this.cellBuilder = cellBuilder;
  }

  /**
   * Sets the cell at the specified position to the given cell.
   *
   * @param row    of the cell we want to change.
   * @param column of the cell we want to change.
   * @param cell   that will be placed.
   * @return
   */
  @Override
  public GridBuilder setCell(int row, int column, ThreeTriosCell cell) {
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

    grid[row][column] = cell;
    return this;
  }

  /**
   * Returns the Grid created by this builder.
   *
   * @return The grid created by this builder.
   * @throws IllegalStateException If there is an even number of card cells in the grid.
   */
  @Override
  public Grid buildGrid() throws IllegalStateException {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        if (grid[row][column] == null) {
          grid[row][column] = cellBuilder.makeCell(false);
        }
      }
    }
    try {
      return new Grid(grid);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e);
    }
  }
}
