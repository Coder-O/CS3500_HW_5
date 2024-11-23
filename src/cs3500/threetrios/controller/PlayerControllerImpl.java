package cs3500.threetrios.controller;

import java.util.Objects;
import java.util.Optional;

import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A controller that manages interactions between a player implementation suite and a model.
 * There is one instance of a controller for every player.
 */
public class PlayerControllerImpl implements PlayerController {
  private final ThreeTriosModel model;
  private final ViewFeatures view;
  private final Player player;
  private final ThreeTriosPlayer playerColor;

  private int indexSelected;

  /**
   * Constructor for a machine PlayerControllerImpl.
   * @param model the game model.
   * @param view the game view.
   */
  public PlayerControllerImpl(
          ThreeTriosGameModel model,
          ViewFeatures view,
          Player player,
          ThreeTriosPlayer playerColor
  ) {
    this.model = Objects.requireNonNull(model);
    this.playerColor = Objects.requireNonNull(playerColor);
    model.addControllerListener(this, this.playerColor);

    this.view = Objects.requireNonNull(view);
    this.view.addFeatures(this);

    this.player = player;
    if (player != null) {
      player.addControllerListener(this);
    }
  }

  /**
   * Constructor for a human PlayerControllerImpl.
   * @param model the game model.
   * @param view the game view.
   */
  public PlayerControllerImpl(
          ThreeTriosGameModel model,
          ViewFeatures view,
          ThreeTriosPlayer playerColor
  ) {
    this(model, view, null, playerColor);
  }

  /**
   * Updates this controller and all of its listeners with a new model.
   */
  @Override
  public void update() {
    view.update();
  }

  /**
   * Informs this controller that it is its turn.
   */
  @Override
  public void isYourTurn() {
    if (player != null) {
      player.itsYourTurn();
    }
  }

  /**
   * Handles when a player selects a card in their hand.
   *
   * @param cardIdx the chosen card's index in the player's hand (0-indexed).
   * @param player  the current player.
   */
  @Override
  public void handleCardSelection(int cardIdx, ThreeTriosPlayer player) {
    System.out.println("Card clicked!!!");

    indexSelected = cardIdx;

    view.handleCardSelection(cardIdx, player);
  }

  /**
   * Handles when a player selects a cell on the grid.
   *
   * @param row the row of the chosen cell.
   * @param col the column of the chosen cell.
   */
  @Override
  public void handleGridCellSelection(int row, int col) {
    System.out.println("Cell Clicked!!!");

    view.handleGridCellSelection(row, col);

    // Play selected card to selected cell
    Optional<Exception> e = model.canPlayToGrid(playerColor, indexSelected, row, col);
    if (e.isEmpty()) {
      model.playToGrid(playerColor, indexSelected, row, col);
    } else {
      view.showError(e.get());
    }
  }
}
