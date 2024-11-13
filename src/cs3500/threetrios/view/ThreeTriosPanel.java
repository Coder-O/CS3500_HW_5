package cs3500.threetrios.view;

import javax.swing.*;

/**
 * Describes what the Panel is capable of.
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
