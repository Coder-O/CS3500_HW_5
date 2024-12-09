package cs3500.threetrios.view;

import javax.swing.*;

import cs3500.threetrios.controller.PlayerActionEvents;

/**
 * An abstract decorator for the grid.
 */
public class GridDecorator implements ThreeTriosPanel {
  protected GridPanel gridPanel;
  private PlayerActionEvents features;

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

//  /**
//   * Adds the features to enable communication with the controller.
//   * @param features the controller's features interface.
//   */
//  public void addFeatures(PlayerActionEvents features) {
//    this.features = features;
//  }
}
