package cs3500.threetrios.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;


/**
 * A mock model that tracks when each of its methods were called.
 * Actual implementation of its methods is handled by an adaptee.
 */
public class TranscriptMockModelAdapter implements ReadOnlyThreeTriosModel {

  private final ReadOnlyThreeTriosModel adaptee;
  private final Appendable appendable;
  private final Set<Coordinate> coordinatesChecked;

  /**
   * Represents a single coordinate.
   */
  public static class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
      this.row = row;
      this.column = column;
    }

    public int getRow() {
      return row;
    }

    public int getColumn() {
      return column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Coordinate other = (Coordinate) o;
      return row == other.row && column == other.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, column);
    }
  }

  /**
   * Creates a new mock model that adapts a adaptee so that it tracks how many times it's
   * methods are called.
   * @param adaptee The adaptee to delegate to.
   * @param appendable The appendable to write to.
   */
  public TranscriptMockModelAdapter(ReadOnlyThreeTriosModel adaptee, Appendable appendable) {
    this.adaptee = adaptee;
    this.appendable = Objects.requireNonNull(appendable);
    this.coordinatesChecked = new HashSet<>();
  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    try {
      appendable.append("isGameOver was called\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.isGameOver();
  }

  /**
   * Returns the winning player or null if the game was a tie.
   *
   * @return The wining player.
   * @throws IllegalStateException If the game isn't over.
   */
  @Override
  public ThreeTriosPlayer getWinner() throws IllegalStateException {
    try {
      appendable.append("getWinner was called\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.getWinner();
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
    try {
      appendable.append("getScore was called with player = ")
              .append(player.toString())
              .append("\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.getScore(player);
  }

  /**
   * Returns the Grid in its current state.
   *
   * @return Current Grid.
   */
  @Override
  public ThreeTriosGrid getGrid() {
    try {
      appendable.append("getGrid was called\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.getGrid();
  }

  /**
   * Returns the Player whose turn it is to play.
   *
   * @return Player who needs to play.
   */
  @Override
  public ThreeTriosPlayer getCurrentPlayer() {
    try {
      appendable.append("getCurrentPlayer was called\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.getCurrentPlayer();
  }

  /**
   * Returns a copy of the hand of the specified Player.
   *
   * @param p whose hand we want.
   * @return a copy of the hand of the specified Player p.
   */
  @Override
  public List<ThreeTriosCard> getHand(ThreeTriosPlayer p) {
    try {
      appendable.append("getHand was called with p = ")
              .append(p.toString())
              .append("\n");
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }
    return adaptee.getHand(p);
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
    try {
      appendable.append("getMoveScore was called with player = ")
              .append(player.toString())
              .append(", cardIdxInHand = ")
              .append(String.valueOf(cardIdxInHand))
              .append(", row = ")
              .append(String.valueOf(row))
              .append(", and column = ")
              .append(String.valueOf(column));
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }

    return adaptee.getMoveScore(player, cardIdxInHand, row, column);
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
   * or null if the move is valid.
   */
  @Override
  public Optional<Exception> canPlayToGrid(
          ThreeTriosPlayer player,
          int cardIdxInHand,
          int row,
          int column
  ) {
    try {
      appendable.append("canPlayToGrid was called with player = ")
              .append(player.toString())
              .append(", cardIdxInHand = ")
              .append(String.valueOf(cardIdxInHand))
              .append(", row = ")
              .append(String.valueOf(row))
              .append(", and column = ")
              .append(String.valueOf(column));
    } catch (IOException e) {
      throw new RuntimeException("The appendable failed!", e);
    }

    coordinatesChecked.add(new Coordinate(row, column));

    return adaptee.canPlayToGrid(player, cardIdxInHand, row , column);
  }

  /**
   * Returns the set of all coordinates that have been checked by canPlayToGrid.
   * @return The set of all coordinates that have been checked by canPlayToGrid.
   */
  public Set<Coordinate> getCoordinatesChecked() {
    return coordinatesChecked;
  }
}
