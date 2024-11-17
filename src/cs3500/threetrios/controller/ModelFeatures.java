package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

public interface ModelFeatures {

  /**
   * Attempts to make a move in the model. (may throw an exception if the move is illegal).
   * @param move
   */
  void makeMove(Move move);

  /**
   * Adds a controller for a specific player
   * @param controller
   * @param playerFor
   */
  void addControllerListener(PlayerController controller, ThreeTriosPlayer playerFor);
}
