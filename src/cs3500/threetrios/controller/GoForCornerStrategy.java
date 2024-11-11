package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosDirection;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of three trios.
 * This strategy consists of: Attempting to play to the corners first.
 */
class GoForCornerStrategy implements FallibleStrategy {

  private List<ThreeTriosCard> hand;
  private Corner[] corners;

  /**
   * Represents a corner.
   * Stores relevant information such as its index in the grid and its directions that aren't
   * blocked.
   */
  private class Corner {
    private final int row;
    private final int column;
    private final ThreeTriosDirection desiredRowDirection;
    private final ThreeTriosDirection desiredColumnDirection;

    public Corner(int row, int column) {
      this.row = row;
      this.column = column;

      if (row == 0) {
        this.desiredRowDirection = ThreeTriosDirection.SOUTH;
      } else {
        this.desiredRowDirection = ThreeTriosDirection.NORTH;
      }

      if (column == 0) {
        this.desiredColumnDirection = ThreeTriosDirection.EAST;
      } else {
        this.desiredColumnDirection = ThreeTriosDirection.WEST;
      }

    }

    /**
     * Returns the row index of this corner.
     * @return The row index of this corner.
     */
    public int getRow() {
      return row;
    }

    /**
     * Returns the column index of this corner.
     * @return The column index of this corner.
     */
    public int getColumn() {
      return column;
    }

    /**
     * Returns the vertical direction in which this corner is vulnerable.
     * @return The vertical direction in which this corner is vulnerable.
     */
    public ThreeTriosDirection getDesiredRowDirection() {
      return desiredRowDirection;
    }

    /**
     * Returns the horizontal direction in which this corner is vulnerable.
     * @return The horizontal direction in which this corner is vulnerable.
     */
    public ThreeTriosDirection getDesiredColumnDirection() {
      return desiredColumnDirection;
    }
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
  public List<Move> findBestMoves(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) {
    setUp(model, playerFor);

    List<Move> bestMoves = new ArrayList<>();
    int bestScore = 0;

    for (Corner corner : corners) {

      // If it is impossible to play to this corner, move on.
      if (model.canPlayToGrid(
              playerFor,
              0,
              corner.getRow(),
              corner.getColumn()
      ).isEmpty()) {
        continue;
      }

      for (int cardIdxInHand = 0; cardIdxInHand < hand.size(); cardIdxInHand++) {

        // If the move is impossible, move on to the next.
        if (model.canPlayToGrid(
                playerFor,
                cardIdxInHand,
                corner.getRow(),
                corner.getColumn()
        ).isEmpty()) {
          continue;
        }

        ThreeTriosCard card = hand.get(cardIdxInHand);

        int currentScore = getCurrentScore(model, playerFor, card,  corner);

        // If the current move's score is as good or better, include it.
        if (currentScore >= bestScore) {
          // If the current move's score is better, ignore the previously included moves.
          if (currentScore > bestScore) {
            bestScore = currentScore;
            bestMoves = new ArrayList<>();
          }

          // Include the new move.
          bestMoves.add(new Move(
                  playerFor,
                  cardIdxInHand,
                  corner.getRow(),
                  corner.getColumn(),
                  model.getMoveScore(
                          playerFor,
                          cardIdxInHand,
                          corner.getRow(),
                          corner.getColumn()
                  )
          ));
        }
      }
    }

    reset();
    return bestMoves;
  }

  /**
   * Finds the score of the given card played to the given corner.
   * The score is how easy the card will be to flip: that is, how many ways the
   * opponent might flip this card with the cards they have in hand
   * (ignoring the state of the board, such as holes next to this corner).
   * As greater scores are considered better, these scores are not positive.
   * @param model The model to consider.
   * @param playerFor The player to consider a move for.
   * @param card The card to play.
   * @param corner The corner to play to.
   * @return The score of such a move.
   */
  private static int getCurrentScore(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          ThreeTriosCard card,
          Corner corner
  ) {
    int currentScore = 0;

    for (ThreeTriosCard opposingCard : model.getHand(playerFor.getOpposingPlayer())) {
      if (
              card.getAttackValue(corner.getDesiredColumnDirection()).getValue()
                      < opposingCard.getAttackValue(corner.getDesiredColumnDirection().getOpposite()).getValue()
      ) {
        currentScore--;
      }

      if (
              card.getAttackValue(corner.getDesiredRowDirection()).getValue()
                      < opposingCard.getAttackValue(corner.getDesiredRowDirection().getOpposite()).getValue()
      ) {
        currentScore--;
      }
    }

    return currentScore;
  }

  /**
   * Sets up for the findBestMoves method.
   * @param model The model to work with.
   * @param playerFor The player to find a move for.
   */
  private void setUp(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerFor) {
    // Setup
    ThreeTriosGrid grid = model.getGrid();
    hand = model.getHand(playerFor);
    int maxColIndex = grid.getNumRows() - 1;
    int maxRowIndex = grid.getNumColumns() - 1;

    // Stores the indices of each corner.
    corners = new Corner[]{
            new Corner(0, 0),
            new Corner(0, maxColIndex),
            new Corner(maxRowIndex, 0),
            new Corner(maxRowIndex, maxColIndex)
    };
  }

  /**
   * Resets the state of this strategy sp that it may be used again.
   * Should be unnecessary, but good practice.
   */
  private void reset() {
    hand = null;
    corners = null;
  }

}
