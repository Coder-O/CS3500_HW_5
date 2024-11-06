package cs3500.threetrios.model;

/**
 * A builder for TreeTriosCells
 */
public interface ThreeTriosCellBuilder {

  /**
   * Makes a ThreeTriosCell
   * @param isHole If the cell should be a hole.
   * @return The created cell.
   */
  ThreeTriosCell makeCell(boolean isHole);
}
