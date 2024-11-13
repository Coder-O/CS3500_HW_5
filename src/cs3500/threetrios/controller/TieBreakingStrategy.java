package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of ThreeTrios that can break a tie between several Moves.
 */
interface TieBreakingStrategy extends Strategy {

  /**
   * Finds the best move provided, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   *
   * @param model The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @param tiedMoves The moves to consider.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no legal move could be found.
   */
  Move findBestMove(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          List<Move> tiedMoves
  ) throws IllegalStateException;
}
