package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.model.ThreeTriosGameModel;

/**
 * A controller that manages interactions between a player implementation suite and a model.
 * There is one instance of a controller for every player.
 */
public interface PlayerController {

  /**
   * Add a view as a listener to this controller.
   * @param view
   */
  void addViewListener(ViewFeatures view);

  /**
   * Adds a player to listens to this controller.
   * @param player The player to listen (for model updates).
   */
  void addPlayerListener(PlayerActions player);

  /**
   * The model will call this method whenever it finishes updating.
   * The controller will update everything that listens to it with the model.
   */
  void updateModel(ThreeTriosGameModel model);

  /**
   * Handles when a player selects a card in their hand.
   * @param cardIdx the chosen card's index in the player's hand (0-indexed).
   * @param player the current player.
   */
  void handleCardSelection(int cardIdx, ThreeTriosPlayer player);

  /**
   * Handles when a player selects a cell on the grid.
   * @param row the row of the chosen cell.
   * @param col the column of the chosen cell.
   */
  void handleGridCellSelection(int row, int col);

}
