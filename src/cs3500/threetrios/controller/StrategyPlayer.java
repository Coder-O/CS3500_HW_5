package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A machine representation of a player. It makes deterministic moves based on a strategy.
 */
public class StrategyPlayer implements PlayerActions {
  private final FullyCompleteStrategy strategy;
  private final ThreeTriosPlayer player;
  private final List<PlayerController> controllerListeners;
  private ReadOnlyThreeTriosModel model;


  /**
   * Creates a new machine representation of a player.
   * @param strategy The strategy for this "Player" to use.
   * @param player The three trios player in the game this object controls.
   * @throws IllegalArgumentException If any of the arguments are null.
   */
  public StrategyPlayer(FullyCompleteStrategy strategy, ThreeTriosPlayer player)
          throws IllegalArgumentException {
    if (strategy == null) {
      throw new IllegalArgumentException("The strategy cannot be null!");
    }

    if (player == null) {
      throw new IllegalArgumentException("The three trios player cannot be null!");
    }

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
   * Updates the player with the given model.
   *
   * @param model The updated model.
   */
  @Override
  public void update(ReadOnlyThreeTriosModel model) {
    this.model = model;
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

    for (PlayerController controller : controllerListeners) {
      controller.handleCardSelection(move.getCardIdxInHand(), player);
    }
  }

}
