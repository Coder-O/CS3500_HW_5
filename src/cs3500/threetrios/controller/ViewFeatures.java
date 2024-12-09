package cs3500.threetrios.controller;

/**
 * Features for a view to interact with a controller.
 * A view can react to any action the player can perform in the game.
 * A view can be updated with a model and show an error.
 */
public interface ViewFeatures extends PlayerActionEvents {

  /**
   * Tells the view it should update/refresh.
   * This method is called by the controller when the model updates to update the view.
   */
  void update();

  /**
   * Displays information about the error to the user.
   * @param e The exception to display to the user.
   */
  void showError(Exception e);

  /**
   * Adds a PlayerActionEvents object as a listener.
   * @param features The object that should be called when a player action is performed.
   */
  void addFeatures(PlayerActionEvents features);

}
