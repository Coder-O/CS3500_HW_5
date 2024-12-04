package cs3500.threetrios.controller;

import cs3500.threetrios.provider.controller.Features;
import cs3500.threetrios.provider.model.IThreeTriosModel;
import cs3500.threetrios.provider.controller.Player; // Or whatever they end up giving us
import cs3500.threetrios.provider.view.IThreeTriosViewGUI;
import cs3500.threetrios.provider.view.PlayerView;

/**
 * A controller for the provider's code that we have adapted to.
 */
public class ProviderController implements Features {
  private final IThreeTriosModel model;
  private final Player player;
  private final PlayerView view;
  private Integer selectedCardIdx;
  private boolean isMyTurn;

  public ProviderController(IThreeTriosModel model, Player player, PlayerView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    isMyTurn = false;

    this.view.addFeaturesListener(this);
  }

  /**
   * Takes in hand index selected from view and
   * sets the hand index in the controller to that val.
   *
   * @param handIdx
   */
  @Override
  public void selectCard(int handIdx) {
    if (selectedCardIdx == handIdx) {
      selectedCardIdx = null;
      view.resetHighlight();
    } else {
      selectedCardIdx = handIdx;
    }
    view.refresh();
  }

  /**
   * Takes in the spot on the board that was selected and places the saved hand index there.
   * If the hand index wasn't selected first, it tells the player.
   *
   * @param row
   * @param col
   */
  @Override
  public boolean placeCard(int row, int col) {
    if (selectedCardIdx == null) {
      view.error("Please select a card in the hand first!");
      view.refresh();
      return false;
    }

    try {
      model.isLegalMove(row, col);
    } catch (Exception e) {
      view.error(e.getMessage());
      view.refresh();
      return false;
    }

    model.placeCard(row, col, selectedCardIdx);
    view.refresh();
    return true;
  }

  /**
   * Model tells the controller whose turn it is, and the controller informs the view.
   *
   * @param turn whose turn it is.
   */
  @Override
  public void setTurn(boolean turn) {
    // Assuming that this informs this controller if it is this specific controller object's turn.
    isMyTurn = turn;
    view.blurScreen(turn); // Does this prevent card selection?
    view.refresh();
  }

  /**
   * Model tells the controller the game is over and haas it handle it.
   */
  @Override
  public void gameOver() {
    view.displayEndGame(model.whoWonGame());
  }
}
