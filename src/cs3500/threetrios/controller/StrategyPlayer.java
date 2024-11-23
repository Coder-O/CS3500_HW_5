package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A machine representation of a player. It makes deterministic moves based on a strategy.
 */
public class StrategyPlayer implements Player {
  private final FullyCompleteStrategy strategy;
  private final ThreeTriosPlayer player;
  private final List<PlayerController> controllerListeners;
  private final ReadOnlyThreeTriosModel model;


  /**
   * Creates a new machine representation of a player.
   * @param strategy The strategy for this "Player" to use.
   * @param player The three trios player in the game this object controls.
   * @throws IllegalArgumentException If any of the arguments are null.
   */
  public StrategyPlayer(
          ReadOnlyThreeTriosModel model,
          FullyCompleteStrategy strategy,
          ThreeTriosPlayer player
  )
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null!");
    }

    if (strategy == null) {
      throw new IllegalArgumentException("The strategy cannot be null!");
    }

    if (player == null) {
      throw new IllegalArgumentException("The three trios player cannot be null!");
    }

    this.model = model;
    this.strategy = strategy;
    this.player = player;
    this.controllerListeners = new ArrayList<>();
  }


  /**
   * Adds the controller that this Player will call on.
   *
   * @param controller The controller this Player should call on.
   */
  @Override
  public void addControllerListener(PlayerController controller) {
    controllerListeners.add(controller);
  }

  /**
   * Lets this Player know that it should make a move.
   *
   * @throws IllegalStateException if the player cannot make a move
   */
  @Override
  public void itsYourTurn() throws IllegalStateException {
    if (model == null) {
      throw new IllegalStateException("Cannot make a move when update was never called!");
    }

    ThreeTriosMove move = strategy.findBestMove(model, player);

    System.out.println(player.getName() + " attempted to play move:");
    System.out.println("Hand: " + move.getCardIdxInHand());
    System.out.println("Row: " + move.getRowIdx());
    System.out.println("Col: " + move.getCollumnIdx());

    for (PlayerController controller : controllerListeners) {
      controller.handleCardSelection(move.getCardIdxInHand(), player);
    }

    for (PlayerController controller : controllerListeners) {
      controller.handleGridCellSelection(move.getRowIdx(), move.getCollumnIdx());
    }
  }

}
