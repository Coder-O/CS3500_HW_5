package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;

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
   * The model will call this method whenever it finishes updating.
   */
  void updateModel(ReadOnlyThreeTriosModel model);

  /**
   * PlayerActions implementations will call this method to attempt to make a move in the model.
   * @param move
   */
  void makeMove(ThreeTriosMove move);

}
