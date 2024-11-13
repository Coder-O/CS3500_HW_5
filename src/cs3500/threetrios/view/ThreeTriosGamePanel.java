package cs3500.threetrios.view;

import java.util.Objects;

import javax.swing.*;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;

/**
 * A ThreeTriosGamePanel allow users to click and play the game.
 */
public class ThreeTriosGamePanel extends JPanel {

  private final ReadOnlyThreeTriosGameModel model;

  //todo finish
  public ThreeTriosGamePanel(ReadOnlyThreeTriosGameModel model) {
    this.model = Objects.requireNonNull(model);
  }

}
