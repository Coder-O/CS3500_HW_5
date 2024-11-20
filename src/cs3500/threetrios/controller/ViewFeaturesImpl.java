package cs3500.threetrios.controller;

import java.util.Objects;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.view.ThreeTriosGameGUIView;

/**
 * Features interface describing the actions a player can perform in the game.
 * The controller implements this interface to handle player actions.
 */
public class ViewFeaturesImpl implements ViewFeatures {

  private final ReadOnlyThreeTriosGameModel model;
  private final ThreeTriosGameGUIView view;

  /**
   * Constructor for ViewFeaturesImpl.
   */
  public ViewFeaturesImpl(ReadOnlyThreeTriosGameModel model, ThreeTriosGameGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);

    this.view.addFeatures(this);
  }

  /**
   * Handles when a player clicks a card in their hand.
   * @param card the clicked card.
   * @param player the current player.
   */
  @Override
  public void handleCardSelection(ThreeTriosCard card, ThreeTriosPlayer player) {

  }

  /**
   * Handles when a player clicks a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  @Override
  public void handleGridCellClick(int row, int col) {

  }

  /**
   * Updates the view with the new model.
   * @param model The current game model
   */
  @Override
  public void updateModel(ReadOnlyThreeTriosGameModel model) {
    new ThreeTriosGameGUIView(model);
  }
}
