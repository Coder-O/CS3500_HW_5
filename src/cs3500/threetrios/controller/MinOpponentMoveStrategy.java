package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of ThreeTrios.
 * Minimizes the opponent's next move.
 * To do this, makes an assumption on what strategy the opponent would make.
 * Assumes that higher scores are better.
 */
class MinOpponentMoveStrategy implements FallibleStrategy {
  private final FullyCompleteStrategy opponentStrategy;

  /**
   * Creates a strategy that minimizes the value of the opponents next move.
   * @param opponentStrategy the strategy that the opponent is presumed to be using.
   */
  MinOpponentMoveStrategy(FullyCompleteStrategy opponentStrategy) {
    this.opponentStrategy = opponentStrategy;
  }


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
  public List<ThreeTriosMove> findBestMoves(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor
  ) {
    List<ThreeTriosMove> bestMoves = new ArrayList<>();
    int worstOpponentScore = Integer.MAX_VALUE;

    ThreeTriosGrid grid = model.getGrid();
    List<ThreeTriosCard>  hand = model.getHand(playerFor);

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int column = 0; column < grid.getNumColumns(); column++) {
        for (int cardIdxInHand = 0; cardIdxInHand < hand.size(); cardIdxInHand++) {
          if (model.canPlayToGrid(playerFor, cardIdxInHand, row, column).isEmpty()) {
            ThreeTriosModel modelCopy = model.getMutableCopy();
            modelCopy.playToGrid(playerFor, cardIdxInHand, row, column);
            worstOpponentScore = considerOpponentMove(
                    modelCopy,
                    playerFor,
                    worstOpponentScore,
                    bestMoves,
                    cardIdxInHand,
                    row,
                    column
            );
          }
        }
      }
    }
    return bestMoves;
  }

  /**
   * A helper method that considers how much an opponent would score on their next turn given a game
   * state.
   * @param modelCopy The prediction of the next model state.
   * @param playerFor The player this strategy is playing as.
   * @param worstOpponentScore The opponent previously the worst score.
   * @param bestMoves The list of best moves so far.
   * @param cardIdxInHand The card index in hand of the current move.
   * @param row The row of the current move.
   * @param column The collumn of the current move.
   * @return The new worst opponent score.
   */
  private int considerOpponentMove(
          ThreeTriosModel modelCopy,
          ThreeTriosPlayer playerFor,
          int worstOpponentScore,
          List<ThreeTriosMove> bestMoves,
          int cardIdxInHand,
          int row,
          int column
  ) {
    try {
      ThreeTriosMove opponentMove = opponentStrategy.findBestMove(
              modelCopy,
              playerFor.getOpposingPlayer()
      );
      int currentScore = opponentMove.getScore();

      // If the current move's score is as good or better, include it.
      if (currentScore <= worstOpponentScore) {
        // If the current move's score is better, ignore the previously included moves.
        if (currentScore < worstOpponentScore) {
          worstOpponentScore = currentScore;
          bestMoves = new ArrayList<>();
        }

        // Include the new move.
        bestMoves.add(new Move(
                playerFor,
                cardIdxInHand,
                row,
                column,
                - worstOpponentScore
        ));
      }
    } catch (IllegalStateException ignored) {

      // If the opponent can't even find a move, all the better!
      if (worstOpponentScore != Integer.MIN_VALUE) {
        worstOpponentScore = Integer.MIN_VALUE;
        bestMoves = new ArrayList<>();
      }

      bestMoves.add(new Move(
              playerFor,
              cardIdxInHand,
              row,
              column,
              Integer.MAX_VALUE
      ));
    }

    return worstOpponentScore;
  }

  /**
   * Whether this implementation is guaranteed to find at least one move, if any legal move exists.
   * Useful for determining if a composition of strategies is Fully Complete.
   *
   * @return Whether this implementation is guaranteed to find at least one move, if any legal move
   *          exists.
   */
  @Override
  public boolean findsAtLeastOneMove() {
    return true;
  }
}
