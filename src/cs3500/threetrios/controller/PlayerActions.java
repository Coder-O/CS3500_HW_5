package cs3500.threetrios.controller;

/**
 * An object that implements every action we want a player to be able to take.
 */
public interface PlayerActions {
  /**
   * Adds the controller that this Player will call on.
   * @param controller
   */
  void addControllerListener(PlayerController controller);
}
