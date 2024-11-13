package cs3500.threetrios.model;

/**
 * A builder for TreeTriosCells.
 * A cell can be a hole or a card cell.
 */
public interface ThreeTriosCellBuilder {

  /**
   * Makes a ThreeTriosCell.
   * @param isHole If the cell should be a hole.
   * @return The created cell.
   */
  ThreeTriosCell makeCell(boolean isHole);
}
