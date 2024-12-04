package cs3500.threetrios.model;

import cs3500.threetrios.provider.model.Cell;

/**
 * An adapter that adapts the provider's Cell to our Cell implementation.
 */
public class CellToCellAdapter implements Cell {

  private final ThreeTriosCell cell;

  public CellToCellAdapter(ThreeTriosCell cell) {
    this.cell = cell;
  }

  @Override
  public int getRow() {
    return 0;
  }

  @Override
  public int getCol() {
    return 0;
  }

  public Cell toCell() {
    return this;
  }
}
