package cs3500.threetrios.model;

import cs3500.threetrios.provider.model.Cell;

/**
 * An adapter that adapts the provider's Cell to our Cell implementation.
 */
public class CellToCellAdapter implements Cell {

  private final int row;
  private final int col;

  /**
   * Constructor for the adapter.
   * @param cell our cell impl
   * @param row the cell's row
   * @param col the cell's col
   */
  public CellToCellAdapter(ThreeTriosCell cell, int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  public Cell toCell() {
    return this;
  }
}
