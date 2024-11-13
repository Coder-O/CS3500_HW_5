package cs3500.threetrios.model;

/**
 * A builder for Cells.
 * A cell can be a hole or a card cell.
 */
public class CellBuilder implements ThreeTriosCellBuilder {
  /**
   * Makes a ThreeTriosCell.
   * @param isHole If the cell should be a hole.
   * @return The created cell.
   */
  @Override
  public ThreeTriosCell makeCell(boolean isHole) {
    return new Cell(isHole);
  }
}
