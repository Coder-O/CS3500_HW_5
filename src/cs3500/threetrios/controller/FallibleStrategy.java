package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents a fallible strategy for a game of Three Trios.
 * A fallible strategy may not find exactly one best move.
 */
interface FallibleStrategy {
  /**
   * Finds the best move, according to this strategy.
   * If multiple moves are just as desirable, returns all tied moves.
   * If no desired moves exist, returns an empty list.
   * @param model The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return A list of the best moves this strategy could find.
   */
  List<Move> findBestMoves(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor);
}
