package cs3500.threetrios.controller;

import java.util.Objects;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.view.ThreeTriosGUIView;

/**
 * An adapter/controller that manages a ThreeTriosGUIView.
 */
public class ViewFeaturesImpl implements ViewFeatures {

  private ThreeTriosGUIView view;
  private Integer selectedCardIdx;
  private PlayerActionEvents features;

  /**
   * Constructor for ViewFeaturesImpl.
   */
  public ViewFeaturesImpl(ReadOnlyThreeTriosModel model, ThreeTriosGUIView view) {
    ReadOnlyThreeTriosModel model1 = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void addFeatures(PlayerActionEvents features) {
    this.view.addFeatures(features);
    this.features = features;
  }

  /**
   * Handles when a player clicks a card in their hand.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  @Override
  public void handleCardSelection(Integer index, ThreeTriosPlayer player) {
    if (index != null) {
      selectedCardIdx = index;
    }
  }

  /**
   * Handles when a player selects a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  @Override
  public void handleGridCellSelection(int row, int col) {
    System.out.println("Calling viewfeaturesimpl.update() from: "
            + Thread.currentThread().getStackTrace()[2]);
    update();
  }

  /**
   * Updates the view with the new model.
   */
  @Override
  public void update() {
    view.refresh();
    view.addFeatures(features);
  }

  /**
   * Displays information about the error to the user.
   *
   * @param e The exception to display to the user.
   */
  @Override
  public void showError(Exception e) {
    view.showErrorMessage(e.getMessage());
  }
}
