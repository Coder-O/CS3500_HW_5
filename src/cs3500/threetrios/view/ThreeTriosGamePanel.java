package cs3500.threetrios.view;

import java.util.Objects;

import javax.swing.JPanel;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;

/**
 * A ThreeTriosGamePanel allow users to click and play the game.
 */
public class ThreeTriosGamePanel extends JPanel {

  /**
   * Constructor for ThreeTriosGamePanel.
   * @param model The game model.
   */
  public ThreeTriosGamePanel(ReadOnlyThreeTriosGameModel model) {
    ReadOnlyThreeTriosGameModel model1 = Objects.requireNonNull(model);
  }

}
