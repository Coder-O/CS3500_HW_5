package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * An adapter that adapts a {@link FallibleStrategy}, any number of {@link TieNarrowingStrategy}s,
 * and at least one of a {@link CompleteStrategy} or {@link TieBreakingStrategy}
 * into part of a {@link FullyCompleteStrategy}.
 */
class CompleteStrategyAdapter implements FullyCompleteStrategy {

  // INVARIANT: no member variable is null.
  // INVARIANT: this.findsAtLeastOneMove() is true.
  private final FallibleStrategy fallibleStrategy;
  private final Optional<CompleteStrategy> completeStrategy;
  private final List<TieNarrowingStrategy> tieNarrowingStrategies;
  private final Optional<TieBreakingStrategy> tieBreakingStrategy;
  private final boolean stopAtOneMove;

  /**
   * Creates a complete strategy from the given fallible, tie-narrowing, and complete strategies.
   * The fallible strategy is tried first.
   * If it fails to return a single move, the best move is decided by the tieNarrowingStrategies,
   * which are applied in the provided order.
   * If that fails to find a single move, the TieBreakingStrategy is called.
   * The above should find a legal move, but if all else fails, the completeStrategy is used.
   *
   * @param fallibleStrategy       The fallible strategy to try.
   * @param completeStrategy       The complete strategy to fall back on.
   * @param tieNarrowingStrategies The strategies to narrow ties in the fallible strategy.
   * @param stopAtOneMove          Whether the tie narrowing strategies should stop being applied
   *                               when they have narrowed the selection down to one move.
   *                               If this is false, all narrowing strategies and the
   *                               tieBreakingStrategy will be applied, even if a
   *                               single best move was found part way through.
   * @param tieBreakingStrategy    The final strategy to break a tie.
   * @throws IllegalArgumentException If the fallibleStrategy is null or
   *                               both the complete and tie-breaking strategies are null
   */
  CompleteStrategyAdapter(
          FallibleStrategy fallibleStrategy,
          CompleteStrategy completeStrategy,
          List<TieNarrowingStrategy> tieNarrowingStrategies,
          boolean stopAtOneMove,
          TieBreakingStrategy tieBreakingStrategy
  ) throws IllegalArgumentException {
    if (fallibleStrategy == null) {
      throw new IllegalArgumentException(
              "The fallible strategy cannot be null!"
      );
    }

    if (completeStrategy == null && tieBreakingStrategy == null) {
      throw new IllegalArgumentException(
              "At least one of the Tie-Breaking or Complete strategies must not be null!"
      );
    }

    if (tieNarrowingStrategies == null) {
      tieNarrowingStrategies = new ArrayList<TieNarrowingStrategy>();
    }
    this.fallibleStrategy = fallibleStrategy;
    this.completeStrategy = Optional.ofNullable(completeStrategy);
    this.tieNarrowingStrategies = tieNarrowingStrategies;
    this.tieBreakingStrategy = Optional.ofNullable(tieBreakingStrategy);
    this.stopAtOneMove = stopAtOneMove;

    if (!this.findsAtLeastOneMove()) {
      throw new IllegalArgumentException(
              "The given composition of strategies is not fully complete!"
      );
    }
  }

  /**
   * Creates a complete strategy from the given fallible, tie-breaking, and complete strategies.
   * The fallible strategy is tried first.
   * If that fails to find a single move, the TieBreakingStrategy is called.
   * The above should find a legal move, but if all else fails, the completeStrategy is used.
   *
   * @param fallibleStrategy       The fallible strategy to try.
   * @param completeStrategy       The complete strategy to fall back on.
   * @param tieBreakingStrategy    The final strategy to break a tie.
   * @throws IllegalArgumentException If the fallibleStrategy is null or
   *                               both the complete and tie-breaking strategies are null
   */
  CompleteStrategyAdapter(
          FallibleStrategy fallibleStrategy,
          CompleteStrategy completeStrategy,
          TieBreakingStrategy tieBreakingStrategy
  ) throws IllegalArgumentException {
    this(fallibleStrategy, completeStrategy, null, true, tieBreakingStrategy);
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
    this(fallibleStrategy, completeStrategy, null, true, null);
  }

  /**
   * Creates a complete strategy from the given fallible, tie-narrowing, and complete strategies.
   * The fallible strategy is tried first.
   * If that fails to find a single move, the TieBreakingStrategy is called.
   *
   * @param fallibleStrategy       The fallible strategy to try.
   * @param tieBreakingStrategy    The final strategy to break a tie.
   * @throws IllegalArgumentException If either the fallibleStrategy or tieBreakingStrategy is null.
   */
  CompleteStrategyAdapter(
          FallibleStrategy fallibleStrategy,
          TieBreakingStrategy tieBreakingStrategy
  ) throws IllegalArgumentException {
    this(fallibleStrategy, null, null, true, tieBreakingStrategy);
  }


  /**
   * Finds the best move, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   *
   * <p>The fallible strategy is tried first.
   *  If it fails to return a single move and any tieNarrowingStrategies were given,
   *  the tieNarrowingStrategies are applied to attempt to find the best move.
   *  If all else fails to find exactly one best move, the completeStrategy is used instead.</p>
   *
   * @param model     The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no legal move could be found.
   */
  @Override
  public ThreeTriosMove findBestMove(ReadOnlyThreeTriosModel model,
                                     ThreeTriosPlayer playerFor) throws IllegalStateException {
    List<ThreeTriosMove> currentMoves = fallibleStrategy.findBestMoves(model, playerFor);

    // If the fallible strategy worked, skip the rest.
    if (currentMoves.size() == 1) {
      return currentMoves.get(0);
    }

    // If the fallible strategy failed, attempt to find a single move through applying the
    // tie-narrowing strategies.
    for (TieNarrowingStrategy narrowingStrategy : tieNarrowingStrategies) {
      List<ThreeTriosMove> newMoves = narrowingStrategy.findBestMoves(model,
              playerFor, currentMoves);
      if (newMoves == null) {
        newMoves = new ArrayList<>();
      }
      currentMoves = newMoves;
      if (stopAtOneMove && currentMoves.size() == 1) {
        return currentMoves.get(0);
      }
    }

    // If a tie-breaking strategy was provided, use it on the results
    if (tieBreakingStrategy.isPresent()) {
      try {
        currentMoves = List.of(
                tieBreakingStrategy.get().findBestMove(model, playerFor, currentMoves)
        );
      } catch (IllegalStateException ignored) {
        // If the tie-breaker failed in an expected way, try the complete strategy.
      }
    }

    // If the tie-narrowing/breaking strategies worked, skip the rest.
    if (currentMoves.size() == 1) {
      return currentMoves.get(0);
    }

    // If the process has come to rely on the complete strategy, but there is none,
    // throw an exception.
    if (completeStrategy.isEmpty()) {
      throw new IllegalStateException("No legal move could be found!!!");
    }
    // If all else has failed, return the result of the complete strategy.
    return completeStrategy.get().findBestMove(model, playerFor);
  }

  /**
   * Whether this implementation is guaranteed to find at least one move, if any legal move exists.
   * Useful for determining if a composition of strategies is Fully Complete.
   * This adapter is guaranteed to be fully complete in 2 scenarios:
   *
   *     <p>1.) The {@link CompleteStrategy} always finds one move.
   *
   *     <p>2.) The {@link FallibleStrategy} always finds at least 1 move,
   *     the {@link TieNarrowingStrategy}s never decrease the number of possible moves to zero,
   *     and the {@link TieBreakingStrategy} exists.
   *
   * @return Whether this implementation is guaranteed to find at least one move, if any legal move
   *         exists.
   */
  @Override
  public boolean findsAtLeastOneMove() {
    if (completeStrategy.isPresent() && completeStrategy.get().findsAtLeastOneMove()) {
      return true;
    }

    if (!fallibleStrategy.findsAtLeastOneMove() || tieBreakingStrategy.isEmpty()) {
      return false;
    }

    for (TieNarrowingStrategy strategy : tieNarrowingStrategies) {
      if (!strategy.neverReturnsZeroMoves()) {
        return false;
      }
    }

    return true;
  }
}
