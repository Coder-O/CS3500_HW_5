package cs3500.threetrios.controller;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A tie narrowing strategy for a game of ThreeTrios.
 * This strategy consists of: choosing the upper-leftmost move from the list of moves provided.
 * If any moves are given, this strategy is guaranteed to return exactly one move.
 * Otherwise, throws an {@link IllegalStateException}.
 */
class UpperLeftmostStrategy implements TieBreakingStrategy {

  /**
   * Finds the best move provided, according to this strategy.
   * Guaranteed to find exactly one move or throw an exception.
   *
   * @param model     The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @param tiedMoves The moves to consider.
   * @return The best move this strategy could find.
   * @throws IllegalStateException If no Moves were given.
   */
  @Override
  public ThreeTriosMove findBestMove(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          List<ThreeTriosMove> tiedMoves
  )
          throws IllegalStateException {
    if (tiedMoves == null || tiedMoves.isEmpty()) {
      throw new IllegalStateException(
              "The provided list of moved is empty! No move could be found!"
      );
    }

    ThreeTriosMove bestMove = tiedMoves.get(0);

    for (ThreeTriosMove move : tiedMoves) {
      // Using Pythagorean theorem a^2 + b^2 = c^2.
      double moveUpperLeftScore = Math.sqrt(
              Math.pow(move.getCollumnIdx(), 2) + Math.pow(move.getRowIdx(), 2)
      );
      double bestMoveUpperLeftScore = Math.sqrt(
              Math.pow(bestMove.getCollumnIdx(), 2) + Math.pow(bestMove.getRowIdx(), 2)
      );

      // If the current move is closer to the top left, it is the best move.
      if (moveUpperLeftScore < bestMoveUpperLeftScore) {
        bestMove = move;
      }

      // If the current move is just as close to the top left and has a lower index in hand,
      // it is the best move.
      if (
              moveUpperLeftScore == bestMoveUpperLeftScore
                      && move.getCardIdxInHand() < bestMove.getCardIdxInHand()
      ) {
        bestMove = move;
      }
    }

    return bestMove;
  }

  /**
   * Whether this implementation is guaranteed to find at least one move, if any legal move exists.
   * Useful for determining if a composition of strategies is Fully Complete.
   *
   * @return Whether this implementation is guaranteed to find at least one move, if any legal move
   *         exists.
   */
  @Override
  public boolean findsAtLeastOneMove() {
    return false;
  }
}
