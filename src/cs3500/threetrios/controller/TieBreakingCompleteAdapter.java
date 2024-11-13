package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * An adapter that adapts a {@link TieBreakingStrategy} into a {@link FullyCompleteStrategy}
 * by providing it every legal move.
 */
class TieBreakingCompleteAdapter implements FullyCompleteStrategy {
  private final TieBreakingStrategy tieBreakingStrategy;

  /**
   * Creates a CompleteStrategy using the given tieBreakingStrategy, by providing the tie breaking strategy evey possible move.
   * @param tieBreakingStrategy The tie-breaker to use.
   * @throws NullPointerException If tieBreakingStrategy is null.
   */
  public TieBreakingCompleteAdapter(TieBreakingStrategy tieBreakingStrategy)
          throws NullPointerException {
    this.tieBreakingStrategy = Objects.requireNonNull(tieBreakingStrategy);
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
  public ThreeTriosMove findBestMove(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) throws IllegalStateException {
    List<ThreeTriosMove> legalMoves = findAllLegalMoves(model, playerFor);
    return tieBreakingStrategy.findBestMove(model, playerFor, legalMoves);
  }

  /**
   * A helper method that finds all legal moves for a given board state.
   * @param model The model to find moves for.
   * @param playerFor The player to find moves for.
   * @return a List containing every single legal move.
   */
  private List<ThreeTriosMove> findAllLegalMoves(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) {
    List<ThreeTriosMove> legalMoves = new ArrayList<>();

    ThreeTriosGrid grid = model.getGrid();
    int handSize = model.getHand(playerFor).size();

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int column = 0; column < grid.getNumColumns(); column++) {

        for (int cardIdx = 0; cardIdx < handSize; cardIdx++) {

          if (model.canPlayToGrid(playerFor, cardIdx, row, column).isEmpty()) {
            legalMoves.add(new Move(playerFor, cardIdx, row, column));
          }

        }

      }
    }

    return legalMoves;
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
