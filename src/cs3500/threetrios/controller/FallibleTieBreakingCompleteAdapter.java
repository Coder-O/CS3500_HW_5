package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * An adapter that adapts a fallible and a tie-breaking strategy into a complete strategy.
 * This adapter requires that the fallible strategy return at least one move,
 * otherwise an exception will be thrown.
 */
class FallibleTieBreakingCompleteAdapter implements CompleteStrategy {
  private final FallibleStrategy fallibleStrategy;
  private final TieBreakingStrategy tieBreakingStrategy;

  /**
   * Creates an adapter that adapts a fallible and a tie-breaking strategy into a complete strategy.
   * This adapter requires that the fallible strategy return at least one move,
   * otherwise an exception will be thrown.
   * @param fallibleStrategy The fallible strategy to use.
   * @param tieBreakingStrategy The tie-breaking strategy to use.
   */
  public FallibleTieBreakingCompleteAdapter(
          FallibleStrategy fallibleStrategy,
          TieBreakingStrategy tieBreakingStrategy
  ) {
    this.fallibleStrategy = fallibleStrategy;
    this.tieBreakingStrategy = tieBreakingStrategy;
  }

  /**
   * Finds the best move, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   *
   * @param model     The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no legal move could be found.
   */
  @Override
  public Move findBestMove(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) throws IllegalStateException {
    List<Move> bestMoves = fallibleStrategy.findBestMoves(model, playerFor);

    if (bestMoves.isEmpty()) {
      throw new IllegalStateException("The fallible strategy failed to find any moves!");
    }

    return tieBreakingStrategy.findBestMove(model, playerFor, bestMoves);
  }
}
