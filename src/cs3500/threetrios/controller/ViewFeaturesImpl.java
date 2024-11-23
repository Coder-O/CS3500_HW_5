package cs3500.threetrios.controller;

import java.util.Objects;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.view.ThreeTriosGameGUIView;

/**
 * Features interface describing the actions a player can perform in the game.
 * The controller implements this interface to handle player actions.
 */
public class ViewFeaturesImpl implements ViewFeatures {

  private ReadOnlyThreeTriosModel model;
  private ThreeTriosGameGUIView view;
  private Integer selectedCardIdx;

  /**
   * Constructor for ViewFeaturesImpl.
   */
  public ViewFeaturesImpl(ReadOnlyThreeTriosModel model, ThreeTriosGameGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);

    this.view.addFeatures(this);
  }

  /**
   * Handles when a player clicks a card in their hand.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  @Override
  public void handleCardSelection(Integer index, ThreeTriosPlayer player) {

    //Confirm it is the player's turn
    if (!model.getCurrentPlayer().equals(player)) {
      view.showErrorMessage("It's not your turn.");
    }

    //Confirm selected card belongs to player
    if (!model.getHand(player).get(index).getPlayer().equals(player)) {
      view.showErrorMessage("Selected card does not belong to you.");
    }

    selectedCardIdx = index;
  }

  /**
   * Handles when a player deselects a card.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  @Override
  public void handleCardDeselection(Integer index, ThreeTriosPlayer player) {
    if (selectedCardIdx != null) {
      selectedCardIdx = index;
      handleCardSelection(selectedCardIdx, player);
    }
  }

  /**
   * Handles when a player selects a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  @Override
  public void handleGridCellSelection(int row, int col) {
    // Check if a card is selected before attempting to place it
    if (selectedCardIdx == null) {
      view.showErrorMessage("Please select a card before choosing a cell.");
    }

    //Confirm cell is not a hole or does not have a card already
    if (model.getGrid().getCell(row, col).isHole()
            || model.getGrid().getCell(row, col).getCard() != null) {
      view.showErrorMessage("Invalid move.");
    }

    view.refresh();
  }

  /**
   * Updates the view with the new model.
   * @param model The current game model
   */
  @Override
  public void updateModel(ReadOnlyThreeTriosModel model) {
    this.model = model;
  }

  /**
   * Displays information about the error to the user.
   *
   * @param e The exception to display to the user.
   */
  @Override
  public void showError(Exception e) {

  }
}
