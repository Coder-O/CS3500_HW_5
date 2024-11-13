package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of three trios.
 * This strategy consists of: maximizing the score of the move to play.
 * This strategy assumes that the ruleset does not allow moves of negative values.
 */
class MaximizeScoreStrategy implements FallibleStrategy {
  /**
   * Finds the best move, according to this strategy.
   * If multiple moves are just as desirable, returns all tied moves.
   * If no desired moves exist, returns an empty list.
   *
   * @param model     The model to choose a move for.
   * @param playerFor The player to choose a move for.
   * @return A list of the best moves this strategy could find.
   */
  @Override
  public List<Move> findBestMoves(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) {
    ThreeTriosGrid grid = model.getGrid();
    List<ThreeTriosCard> hand = model.getHand(playerFor);

    List<Move> bestMoves = new ArrayList<>();
    int bestScore = 0;

    // For every cell
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int column = 0; column < grid.getNumColumns(); column++) {
        // For every card in hand
        for (int cardIdxInHand = 0; cardIdxInHand < hand.size(); cardIdxInHand++) {

          // If the move is illegal, ignore it.
          if (model.canPlayToGrid(playerFor, cardIdxInHand, row, column).isPresent()) {
            continue;
          }

          int currentScore = model.getMoveScore(playerFor, cardIdxInHand, row, column);

          // If the current move's score is as good or better, include it.
          if (currentScore >= bestScore) {
            // If the current move's score is better, ignore the previously included moves.
            if (currentScore > bestScore) {
              bestScore = currentScore;
              bestMoves = new ArrayList<>();
            }

            // Include the new move.
            bestMoves.add(new Move(playerFor, cardIdxInHand, row, column));
          }

        }
      }
    }

    return bestMoves;
  }

  /**
   * Whether this implementation is guaranteed to find at least one move, if any legal move exists.
   * Useful for determining if a composition of strategies is Fully Complete.
   *
   * @return Whether this implementation is guaranteed to find at least one move, if any legal move
   * exists.
   */
  @Override
  public boolean findsAtLeastOneMove() {
    return true;
  }
}
