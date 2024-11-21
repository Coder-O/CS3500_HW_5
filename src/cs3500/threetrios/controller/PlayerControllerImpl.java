package cs3500.threetrios.controller;

import java.util.Objects;

import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.view.ThreeTriosGameGUIView;

/**
 * A controller that manages interactions between a player implementation suite and a model.
 * There is one instance of a controller for every player.
 */
public class PlayerControllerImpl implements PlayerController {

  private ThreeTriosGameModel model;
  private ThreeTriosGameGUIView view;
  private ViewFeaturesImpl features;

  /**
   * Constructor for PlayerControllerImpl.
   * @param model the game model.
   * @param view the game view.
   */
  public PlayerControllerImpl(ThreeTriosGameModel model, ThreeTriosGameGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    features = new ViewFeaturesImpl(model, view);
  }

  /**
   * Add a view as a listener to this controller.
   * @param view
   */
  @Override
  public void addViewListener(ViewFeatures view) {

  }

  /**
   * The model will call this method whenever it finishes updating.
   */
  @Override
  public void updateModel(ThreeTriosGameModel model) {
    this.model = model;
  }

  /**
   * PlayerActions implementations will call this method to attempt to make a move in the model.
   * @param move the move the player wants to make.
   */
  @Override
  public void makeMove(ThreeTriosMove move) {

    //Handle Card Selection
    features.handleCardSelection(move.getCardIdxInHand(), move.getPlayer());

    //todo add handleCardDeselection

    //Handle Cell Selection
    features.handleGridCellSelection(move.getRowIdx(), move.getCollumnIdx());

    //Play selected card to selected cell
    model.playToGrid(move.getPlayer(), move.getCardIdxInHand(),
            move.getRowIdx(), move.getCollumnIdx());
  }
}
