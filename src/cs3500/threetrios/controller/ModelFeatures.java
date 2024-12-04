package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Features for a model to interact with a controller.
 */
public interface ModelFeatures {

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
