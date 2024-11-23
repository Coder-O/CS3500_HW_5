package cs3500.threetrios.controller;

import java.io.IOException;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A mock for a player controller that tracks a transcript of its method calls.
 */
public class MockPlayerController implements PlayerController {
  private final Appendable appendable;

  /**
   * Creates a new mock player with the given appendable.
   * @param appendable The appendable to record a trnascript to.
   */
  public MockPlayerController(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Handles when a player selects a card in their hand.
   *
   * @param cardIdx the chosen card's index in the player's hand (0-indexed).
   * @param player  the current player.
   */
  @Override
  public void handleCardSelection(int cardIdx, ThreeTriosPlayer player) {
    try {
      appendable.append("handleCardSelection called with cardIdx = ");
      appendable.append(cardIdx + "");
      appendable.append(" and player = ");
      appendable.append(player.toString());
      appendable.append(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Handles when a player selects a cell on the grid.
   *
   * @param row the row of the chosen cell.
   * @param col the column of the chosen cell.
   */
  @Override
  public void handleGridCellSelection(int row, int col) {
    try {
      appendable.append("handleGridCellSelection called with row = ");
      appendable.append(row + "");
      appendable.append(" and col = ");
      appendable.append(col + "");
      appendable.append(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * The model will call this method whenever it finishes updating.
   * The controller will update everything that listens to it with the model.
   */
  @Override
  public void update() {
    try {
      appendable.append("update was called");
      appendable.append(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Informs this controller that it is its turn.
   */
  @Override
  public void isYourTurn() {
    try {
      appendable.append("isYourTurn was called");
      appendable.append(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
