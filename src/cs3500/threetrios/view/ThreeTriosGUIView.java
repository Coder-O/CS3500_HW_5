package cs3500.threetrios.view;

import cs3500.threetrios.controller.PlayerActionEvents;
import cs3500.threetrios.model.ThreeTriosPlayer;

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
  void addFeatures(PlayerActionEvents features);

  /**
   * Display an error message.
   * @param message the message we want to display.
   */
  void showErrorMessage(String message);

  /**
   * Display Results Message.
   * If the game was won, will display who won and score.
   * If the game was a tie, will display that it was a tie.
   */
  void showResultsMessage();

  /**
   * Updates necessary parts of the view when a card is selected.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  void handleCardSelection(Integer index, ThreeTriosPlayer player);

}
