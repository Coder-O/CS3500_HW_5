package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A controller that manages interactions between a player implementation suite and a model.
 * There is one instance of a controller for every player.
 * A controller can react to any player event.
 * A controller can be updated with a more current model.
 */
public interface PlayerController extends PlayerActionEvents {
  /**
   * The model will call this method whenever it finishes updating.
   * The controller will update everything that listens to it with the model.
   */
  void update();

  /**
   * Informs this controller that it is its turn.
   */
  void isYourTurn();

  /**
   * Returns the player of this controller.
   * @return This controller's player.
   */
  ThreeTriosPlayer getPlayer();
}
