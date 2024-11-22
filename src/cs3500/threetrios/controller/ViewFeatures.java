package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Features interface describing the actions a player can perform in the game.
 * The controller implements this interface to handle player actions.
 */
public interface ViewFeatures {

  /**
   * Handles when a player clicks a card in their hand.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  void handleCardSelection(Integer index, ThreeTriosPlayer player);

  /**
   * Handles when a player deselects a card.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  void handleCardDeselection(Integer index, ThreeTriosPlayer player);

  /**
   * Handles when a player selects a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  void handleGridCellSelection(int row, int col);

  /**
   * Updates the view with the new model.
   * This method is called by the controller when the model updates to update the view.
   * @param model the current model
   */
  void updateModel(ReadOnlyThreeTriosModel model);

  /**
   * Displays information about the error to the user.
   * @param e The exception to display to the user.
   */
  void showError(Exception e);
}
