package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of ThreeTrios that can narrow a tie between several Moves.
 * In other words, it can reduce the number of tied moves.
 */
interface TieNarrowingStrategy {
  /**
   * Finds the best selection of the given moves, according to this strategy.
   * @param model The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @param tiedMoves The moves to consider.
   * @return A list of the best moves this strategy could find.
   */
  List<Move> findBestMoves(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          List<Move> tiedMoves
  ) throws IllegalStateException;
}
