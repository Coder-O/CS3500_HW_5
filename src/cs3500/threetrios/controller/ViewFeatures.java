package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Features interface describing the actions a player can perform in the game.
 * The controller implements this interface to handle player actions.
 */
public interface ViewFeatures {

  /**
   * Handles when a player clicks a card in their hand.
   * @param card the clicked card.
   * @param player the current player.
   */
  void handleCardSelection(ThreeTriosCard card, ThreeTriosPlayer player);

  /**
   * Handles when a player clicks a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  void handleGridCellClick(int row, int col);

  /**
   * Updates the view with the new model.
   * @param model the current model
   */
  void updateModel(ReadOnlyThreeTriosGameModel model);
}
