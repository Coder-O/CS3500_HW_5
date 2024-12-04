package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.provider.controller.Features;
import cs3500.threetrios.provider.model.IThreeTriosModel;
import cs3500.threetrios.provider.view.IPlayerView;

/**
 * A controller for the provider's code that we have adapted to.
 * THis controller also implements our {@link PlayerController} interface,
 * so that it can work with existing code.
 */
public class ProviderController implements Features, PlayerController  {
  private final IThreeTriosModel model;
  private final ThreeTriosPlayer player;
  private final IPlayerView view;
  private Integer selectedCardIdx;
  private boolean isMyTurn;


  /**
   * Creates a new ProviderController that controls a view and model for a specific player.
   * @param model The model to use.
   * @param player The player to control for.
   * @param view The view to use.
   */
  public ProviderController(IThreeTriosModel model, ThreeTriosPlayer player, IPlayerView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    isMyTurn = false;

    this.view.addFeaturesListener(this);
    this.model.addFeaturesListener(this);
  }

  // Methods from Features //

  /**
   * Takes in hand index selected from view and
   * sets the hand index in the controller to that val.
   *
   * @param handIdx
   */
  @Override
  public void selectCard(int handIdx) {
    if (selectedCardIdx == null || selectedCardIdx != handIdx) {
      selectedCardIdx = handIdx;
    } else {
      selectedCardIdx = null;
      view.resetHighlight();
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
    System.out.println("Card place attempted at " + row + ", " + col);
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
    view.blurScreen(!turn); // Does this prevent card selection?
    view.refresh();
  }

  /**
   * Model tells the controller the game is over and haas it handle it.
   */
  @Override
  public void gameOver() {
    view.displayEndGame(model.whoWonGame());
  }

  // Methods from PlayerController //

  /**
   * Handles when a player clicks a card in their hand.
   *
   * @param index  the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  @Override
  public void handleCardSelection(Integer index, ThreeTriosPlayer player) {
    if (player == this.player) {
      this.selectCard(index);
    }
  }

  /**
   * Handles when a player selects a cell on the grid.
   *
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  @Override
  public void handleGridCellSelection(int row, int col) {
    this.placeCard(row, col);
  }

  /**
   * The model will call this method whenever it finishes updating.
   * The controller will update everything that listens to it with the model.
   */
  @Override
  public void update() {
    System.out.println("Provider updated with turn = " + model.getTurn());
    System.out.println("Provider is " + player.getName());
    this.setTurn(model.getTurn().equals(player.getName()));
    if (model.isGameOver()) {
      gameOver();
    }
  }

  /**
   * Informs this controller that it is its turn.
   */
  @Override
  public void isYourTurn() {
    System.out.println("It's the provider's turn.");
    this.setTurn(true);
  }

  /**
   * Returns the player of this controller.
   *
   * @return This controller's player.
   */
  @Override
  public ThreeTriosPlayer getPlayer() {
    return player;
  }
}
