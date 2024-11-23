package cs3500.threetrios.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosDirection;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A strategy for a game of ThreeTrios.
 * This strategy consists of: minimizing the number of ways an opponent can flip this card.
 */
class MinCanFlipStrategy implements FallibleStrategy {

  // To prevent the code from being one large method, it was split into many sections.
  // However, many variables are accesses throughout the code.
  // So, there are many private variables that are reset every time findBestMoves is run.
  private List<ThreeTriosCard> hand;
  private int bestScore;
  private List<ThreeTriosMove> bestMoves;
  private ThreeTriosGrid grid;
  private int numCols;
  private int numRows;
  private boolean checkNorth;
  private boolean checkSouth;
  private boolean checkWest;
  private boolean checkEast;


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
  public List<ThreeTriosMove> findBestMoves(ReadOnlyThreeTriosModel model,
                                            ThreeTriosPlayer playerFor) {
    setUp(model, playerFor);

    // Calling consider move on every legal move
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numCols; column++) {

        // If it is impossible to play to this position, move on.
        if (model.canPlayToGrid(
                playerFor,
                0,
                row,
                column
        ).isPresent()) {
          continue;
        }

        getDirections(row, column);

        for (int cardIdxInHand = 0; cardIdxInHand < hand.size(); cardIdxInHand++) {

          // If the move is impossible, move on to the next.
          if (model.canPlayToGrid(
                  playerFor,
                  cardIdxInHand,
                  row,
                  column
          ).isPresent()) {
            continue;
          }

          // Called on every legal move
          considerMove(model, playerFor, row, column, cardIdxInHand);
        }
      }
    }

    // Reset the state, but still return the bestMoves
    List<ThreeTriosMove> tempBestMoves = List.copyOf(bestMoves);
    reset();
    return tempBestMoves;
  }

  // Helper method.
  // Determines if each direction should be checked when determining the score for a given position.
  private void getDirections(int row, int column) {
    checkNorth = row > 0
            && !grid.getCell(row - 1, column).isHole()
            && grid.getCell(row - 1, column).getCard() == null;

    checkSouth = row < numRows - 1
            && !grid.getCell(row + 1, column).isHole()
            && grid.getCell(row + 1, column).getCard() == null;

    checkWest = column > 0
            && !grid.getCell(row, column - 1).isHole()
            && grid.getCell(row, column - 1).getCard() == null;

    checkEast = column < numCols - 1
            && !grid.getCell(row, column + 1).isHole()
            && grid.getCell(row, column + 1).getCard() == null;
  }

  /**
   * Helper method. For a given move, determines if it should be added to the list of best moves
   * and if the list of best moves should be reset.
   * @param model The model to consider.
   * @param playerFor The player to play for.
   * @param row The row to play to.
   * @param column The column to play to.
   * @param cardIdxInHand The card's index in playerFor's hand.
   */
  private void considerMove(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          int row,
          int column,
          int cardIdxInHand
  ) {
    ThreeTriosCard card = hand.get(cardIdxInHand);

    int currentScore = getCurrentScore(model, playerFor, card);

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
              row,
              column,
              currentScore
      ));
    }
  }

  /**
   * Finds the score of the given card played to the given position.
   * The score is how easy the card will be to flip: that is, how many ways the
   * opponent might flip this card with the cards they have in hand
   * (ignoring the state of the board, such as holes next to this corner).
   * As greater scores are considered better, these scores are not positive.
   * @param model The model to consider.
   * @param playerFor The player to consider a move for.
   * @param card The card to play.
   * @return The score of such a move.
   */
  private int getCurrentScore(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerFor,
          ThreeTriosCard card
  ) {
    int currentScore = 0;

    for (ThreeTriosCard opposingCard : model.getHand(playerFor.getOpposingPlayer())) {
      currentScore = checkDirection(
              card,
              opposingCard,
              checkNorth,
              currentScore,
              ThreeTriosDirection.NORTH
      );

      currentScore = checkDirection(
              card,
              opposingCard,
              checkSouth,
              currentScore,
              ThreeTriosDirection.SOUTH
      );
      currentScore = checkDirection(
              card,
              opposingCard,
              checkEast,
              currentScore,
              ThreeTriosDirection.EAST
      );
      currentScore = checkDirection(
              card,
              opposingCard,
              checkWest,
              currentScore,
              ThreeTriosDirection.WEST
      );
    }

    return currentScore;
  }

  // Helper method for get score. Checks against a given direction.
  private static int checkDirection(
          ThreeTriosCard card,
          ThreeTriosCard opposingCard,
          boolean checkDirection,
          int currentScore,
          ThreeTriosDirection directionToCheck
  ) {
    int opposingDirectionStrength;
    int moveDirectionStrength;
    if (checkDirection) {
      moveDirectionStrength = card
              .getAttackValue(
                      directionToCheck
              ).getValue();


      opposingDirectionStrength = opposingCard
              .getAttackValue(
                      directionToCheck.getOpposite()
              ).getValue();

      // If the opposing card beats it in this direction, decrease its score.
      if (moveDirectionStrength < opposingDirectionStrength) {
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
    grid = model.getGrid();
    hand = model.getHand(playerFor);
    numCols = grid.getNumColumns();
    numRows = grid.getNumRows();

    checkNorth = true;
    checkEast = true;
    checkSouth = true;
    checkWest = true;


    bestMoves = new ArrayList<>();
    bestScore = Integer.MIN_VALUE;
  }

  /**
   * Resets the state of this strategy so that it may be used again.
   * Should be unnecessary, but good practice.
   */
  private void reset() {
    hand = null;
    bestMoves = new ArrayList<>();
    bestScore = 0;
    grid = null;
    numCols = 0;
    numRows = 0;
    checkNorth = true;
    checkEast = true;
    checkSouth = true;
    checkWest = true;
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
