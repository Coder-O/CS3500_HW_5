package cs3500.ThreeTrios.model;

public class SimpleGridBuilder implements ThreeTriosGridBuilder<SimpleGrid> {
  /**
   * Sets the cell at the specified position to the given cell.
   *
   * @param row    of the cell we want to change.
   * @param column of the cell we want to change.
   * @param cell   that will be placed.
   */
  @Override
  public void setCell(int row, int column, ThreeTriosCell cell) {

  }

  /**
   * Returns the Grid created by this builder.
   *
   * @return The grid created by this builder.
   */
  @Override
  public SimpleGrid buildGrid() {
    return null;
  }
}
