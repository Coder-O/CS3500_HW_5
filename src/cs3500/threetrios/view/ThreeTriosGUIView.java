package cs3500.threetrios.view;

import cs3500.threetrios.controller.ViewFeatures;

/**
 * Represents the GUI view for the ThreeTrios Game.
 */
public interface ThreeTriosGUIView {

  /**
   * Updates the view based on the model's current state.
   */
  void refresh();

  /**
   * Displays the game window.
   */
  void makeVisible();

  /**
   * Register the controller as a listener for player actions.
   * @param features player actions.
   */
  void addFeatures(ViewFeatures features);

  /**
   * Display an error message.
   * @param message the message we want to display.
   */
  void showErrorMessage(String message);

}
