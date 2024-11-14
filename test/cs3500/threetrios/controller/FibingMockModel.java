package cs3500.threetrios.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A decorator for a {@link ReadOnlyThreeTriosModel}, for use testing strategies.
 * It asserts that playing to position (1, 2) with cardIndexInHand = 3 gives a score of the maximum
 * integer value,
 * and all other moves give a random score between 1 and 50.
 * It also asserts that every move is legal.
 */
public class FibingMockModel implements ReadOnlyThreeTriosModel {
  private final ReadOnlyThreeTriosModel delegate;

  /**
   * Creates a new FibingMockModel, which lies about the value and legality of moves.
   * @param delegate The delegate to delegate to.
   */
  public FibingMockModel(ReadOnlyThreeTriosModel delegate) {
    this.delegate = delegate;
  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return delegate.isGameOver();
  }

  /**
   * Returns the winning player or null if the game was a tie.
   *
   * @return The wining player.
   * @throws IllegalStateException If the game isn't over.
   */
  @Override
  public ThreeTriosPlayer getWinner() throws IllegalStateException {
    return delegate.getWinner();
  }

  /**
   * Returns this player's score.
   * A player’s score is the number of cards that player owns in their hand
   * plus the number of cards that player owns on the grid.
   *
   * @param player the player whose score we want
   * @return the score
   */
  @Override
  public int getScore(ThreeTriosPlayer player) {
    return delegate.getScore(player);
  }

  /**
   * Returns the Grid in its current state.
   *
   * @return Current Grid.
   */
  @Override
  public ThreeTriosGrid getGrid() {
    return delegate.getGrid();
  }

  /**
   * Returns the Player whose turn it is to play.
   *
   * @return Player who needs to play.
   */
  @Override
  public ThreeTriosPlayer getCurrentPlayer() {
    return delegate.getCurrentPlayer();
  }

  /**
   * Returns a copy of the hand of the specified Player.
   *
   * @param p whose hand we want.
   * @return a copy of the hand of the specified Player p.
   */
  @Override
  public List<ThreeTriosCard> getHand(ThreeTriosPlayer p) {
    return delegate.getHand(p);
  }

  /**
   * Gets the projected score of playing a card from the cardIdxInHand position of the given
   * player's hand to the specified row and collumn of the grid. All indices are zero indexed.
   *
   * @param player        The player whose hand the card would be played from.
   * @param cardIdxInHand The index of the card in the specified hand.
   * @param row           The row in the grid the card would be played to.
   * @param column        The column in the grid the card would be played to.
   * @return The projected score of such a move.
   * @throws IllegalStateException     If the game is over.
   * @throws IllegalArgumentException  If player is null.
   * @throws IllegalStateException     If it is not the specified player's turn.
   * @throws IndexOutOfBoundsException If the cardIdxInHand, row,
   *                                   or column parameters are out-of-bounds.
   * @throws IllegalStateException     If the specified move is invalid
   *                                   (such as playing to a hole or a filled Card Cell).
   */
  @Override
  public int getMoveScore(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
          throws IllegalStateException, IndexOutOfBoundsException, IllegalArgumentException {
    if (row == 1 && column == 2 && cardIdxInHand == 3) {
      return Integer.MAX_VALUE;
    }
    Random randal = new Random();
    return randal.nextInt(50);
  }

  /**
   * Determines if it is legal to play a card from the cardIdxInHand position of the given
   * player's hand to the specified row and collumn of the grid, and returns the corresponding
   * exception that would be thrown. If the move is legal, returns null.
   * All indices are zero indexed.
   *
   * <p>The exceptions returned are as follows:
   * <ul>
   *   <li>{@link IllegalStateException} If the game is over.</li>
   *   <li>{@link IllegalArgumentException} If player is null.</li>
   *   <li>{@link IllegalStateException} If it is not the specified player's turn.</li>
   *   <li>{@link IndexOutOfBoundsException} If the cardIdxInHand, row, or column parameters are
   *        out-of-bounds.</li>
   *   <li>{@link IllegalStateException} If the specified move is otherwise invalid,
   *       such as playing to a hole or a filled Card Cell.</li>
   * </ul>
   *
   * @param player        The player whose hand the card would be played from.
   * @param cardIdxInHand The index of the card in the specified hand.
   * @param row           The row in the grid the card would be played to.
   * @param column        The column in the grid the card would be played to.
   * @return The exception that would be thrown by attempting the move,
   *         or null if the move is valid.
   */
  @Override
  public Optional<Exception> canPlayToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row,
                                           int column) {
    return Optional.empty();
  }

  /**
   * Returns a mutable copy of this model.
   *
   * @return A mutable copy of this model.
   */
  @Override
  public ThreeTriosModel getMutableCopy() {
    return delegate.getMutableCopy();
  }
}
