package cs3500.ThreeTrios.model;

/**
 * A builder for {@link Grid}.
 */
public class GridBuilder implements ThreeTriosGridBuilder<Grid> {
  ThreeTriosCell[][] grid;


  public GridBuilder(int rows, int columns) {
    grid = new ThreeTriosCell[rows][columns];
  }

  /**
   * Sets the cell at the specified position to the given cell.
   * @param row    of the cell we want to change.
   * @param column of the cell we want to change.
   * @param cell   that will be placed.
   */
  @Override
  public void setCell(int row, int column, ThreeTriosCell cell) {
    grid[row][column]
  }

  /**
   * Returns the Grid created by this builder.
   *
   * @return The grid created by this builder.
   */
  @Override
  public Grid buildGrid() {
    return new Grid(grid);
  }
}
