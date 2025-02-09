package cs3500.threetrios.model;

/**
* Builder for ThreeTriosGrid.
* The setCell method will be called for every non-card cell in the grid.
* Cells not initialized using the setCell method will be initialized to empty CardCells.
*/
public interface ThreeTriosGridBuilder<T extends ThreeTriosGrid> {

  /**
   * Sets the cell at the specified position to the given cell.
   *
   * @param row    of the cell we want to change.
   * @param column of the cell we want to change.
   * @param cell   that will be placed.
   */
  GridBuilder setCell(int row, int column, ThreeTriosCell cell);
    
  /**
   * Returns the Grid created by this builder.
   * @return The grid created by this builder.
   */
  T buildGrid();
}

