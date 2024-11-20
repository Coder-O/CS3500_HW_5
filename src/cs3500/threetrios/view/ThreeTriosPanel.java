package cs3500.threetrios.view;

import javax.swing.JComponent;

import cs3500.threetrios.controller.ViewFeatures;

/**
 * Describes what the Panel is capable of.
 * There is a Grid Panel and a Card Panel. They can be clicked by the player.
 */
public interface ThreeTriosPanel {

  /**
   * Updates displayed items.
   */
  void update();

  /**
   * The game component.
   * @return game component.
   */
  JComponent getComponent();

}
