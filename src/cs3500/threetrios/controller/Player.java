package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;

/**
 * An object that implements every action we want a player to be able to take.
 * A player should be able to:
 * <ul>
 *   <li>Select a card</li>
 *   <li>Select a cell</li>
 * </ul>
 * To do this, a player should be able to:
 * <ul>
 *   <li>Know the model state (through code or physically through a view)</li>
 *   <li>Know what controller to call</li>
 * </ul>
 */
public interface Player {
  /**
   * Adds the controller that this Player will call on.
   * @param controller
   */
  void addControllerListener(PlayerController controller);

  /**
   * Lets this Player know that it should make a move.
   * @throws IllegalStateException if the player cannot make a move
   */
  void itsYourTurn() throws IllegalStateException;
}
