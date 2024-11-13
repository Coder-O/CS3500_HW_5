package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents a complete strategy for a game of Three Trios.
 * A complete strategy always finds exactly one move, or throws an exception.
 */
interface CompleteStrategy extends Strategy {
  /**
   * Finds the best move, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   * @param model The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no legal move could be found.
   */
  Move findBestMove(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor)
          throws IllegalStateException;
}
