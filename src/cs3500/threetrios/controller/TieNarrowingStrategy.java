package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of ThreeTrios that can narrow a tie between several Moves.
 * In other words, it can reduce the number of tied moves.
 */
interface TieNarrowingStrategy extends Strategy {
  /**
   * Finds the best selection of the given moves, according to this strategy.
   *
   * <p>If neverReturnsZeroMoves() is true, then this is guaranteed to return a non-empty list
   * when provided a non-empty list.
   * @param model The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @param tiedMoves The moves to consider.
   * @return A list of the best moves this strategy could find.
   */
  List<ThreeTriosMove> findBestMoves(

          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          List<ThreeTriosMove> tiedMoves
  ) throws IllegalStateException;

  /**
   * Returns whether this strategy's findBestMoves method
   * is guaranteed to never return an empty list, provided it received a non-empty list.
   * @return whether this strategy's findBestMoves method
   *         is guaranteed to never return an empty list, provided it received a non-empty list.
   */
  boolean neverReturnsZeroMoves();
}
