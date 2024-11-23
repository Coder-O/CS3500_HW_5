package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Features for a model to interact with a controller.
 */
public interface ModelFeatures {

  /**
   * Attempts to make a move in the model. (may throw an exception if the move is illegal).
   * @param move The move to attempt
   */
  void makeMove(Move move);

  /**
   * Adds a controller listener for a specific player.
   * @param controller the game controller.
   * @param playerFor player we want to listen to.
   */
  void addControllerListener(PlayerController controller, ThreeTriosPlayer playerFor);

  /**
   * Starts the game by calling update on the first player's controller.
   */
  void startGame();

}
