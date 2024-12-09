package cs3500.threetrios.view;

import javax.swing.JComponent;

/**
 * An abstract decorator for the grid.
 */
public class GridDecorator implements ThreeTriosPanel {
  protected GridPanel gridPanel;

  /**
   * Constructor for the decorator.
   * @param gridPanel the gridPanel.
   */
  public GridDecorator(GridPanel gridPanel) {
    this.gridPanel = gridPanel;
  }

  /**
   * Updates the grid.
   * When a cell is clicked, it will print its row and col.
   * Card cells are drawn in grey, holes are drawn in yellow.
   */
  @Override
  public void update() {
    this.gridPanel.update();
  }

  /**
   * Returns the current grid.
   * @return the current grid.
   */
  @Override
  public JComponent getComponent() {
    return gridPanel.getComponent();
  }

}
