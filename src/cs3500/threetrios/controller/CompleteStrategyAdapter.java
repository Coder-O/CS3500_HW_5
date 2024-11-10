package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * An adapter that adapts a {@link FallibleStrategy} into part of a {@link CompleteStrategy}.
 */
class CompleteStrategyAdapter implements CompleteStrategy {

  // INVARIANT: no member variable is null.
  private final FallibleStrategy fallibleStrategy;
  private final CompleteStrategy completeStrategy;
  private final List<TieNarrowingStrategy> tieNarrowingStrategies;

  /**
   * Creates a complete strategy from the given fallible, tie-narrowing, and complete strategies.
   * The fallible strategy is tried first.
   * If it returns multiple moves, the best move is decided by the tieNarrowingStrategies,
   * which are applied in the provided order.
   * If all else fails, the completeStrategy is used.
   * @param fallibleStrategy The fallible strategy to try.
   * @param completeStrategy The complete strategy to fall back on.
   * @param tieNarrowingStrategies The strategies to break ties in the fallible strategy.
   * @throws IllegalArgumentException If the completeStrategy or the fallibleStrategy is null.
   */
  CompleteStrategyAdapter(
          FallibleStrategy fallibleStrategy,
          CompleteStrategy completeStrategy,
          List<TieNarrowingStrategy> tieNarrowingStrategies
  ) throws IllegalArgumentException {
    if (completeStrategy == null || fallibleStrategy == null) {
      throw new IllegalArgumentException(
              "Neither the complete nor fallible strategies can be null!"
      );
    }

    if (tieNarrowingStrategies == null) {
      tieNarrowingStrategies = new ArrayList<TieNarrowingStrategy>();
    }
    this.fallibleStrategy = fallibleStrategy;
    this.completeStrategy = completeStrategy;
    this.tieNarrowingStrategies = tieNarrowingStrategies;
  }

  /**
   * Creates a complete strategy from the given fallible and complete strategies.
   * The fallible strategy is tried first.
   * If that fails, the completeStrategy is used.
   * @param fallibleStrategy The fallible strategy to try.
   * @param completeStrategy The complete strategy to fall back on.
   * @throws IllegalArgumentException If the completeStrategy or the fallibleStrategy is null.
   */
  CompleteStrategyAdapter(
          FallibleStrategy fallibleStrategy,
          CompleteStrategy completeStrategy
  ) throws IllegalArgumentException {
    this(fallibleStrategy, completeStrategy, null);
  }


  /**
   * Finds the best move, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   *
   * <p>The fallible strategy is tried first.
   *  If it returns multiple moves and any tieNarrowingStrategies were given,
   *  the tieNarrowingStrategies are applied to attempt to find the best move.
   *  If all else fails to find exactly one best move, the completeStrategy is used instead.</p>
   *
   * @param model     The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no legal move could be found.
   */
  @Override
  public Move findBestMove(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) throws IllegalStateException {
    return null;
  }
}
