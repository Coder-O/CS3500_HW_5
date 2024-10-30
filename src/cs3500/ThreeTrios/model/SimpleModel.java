package cs3500.ThreeTrios.model;

import java.util.List;
import java.util.Stack;

public class SimpleModel implements ThreeTriosModel {


  public SimpleModel() {

  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Checks if the gae has been won.
   *
   * @return true if the game has been won, false otherwise
   */
  @Override
  public boolean isGameWon() {
    return false;
  }

  /**
   * Plays a card from the cardIdxInHand position of the given player's hand to the specified row and collumn of the grid.
   * All indices are zero indexed.
   *
   * @param player        The player who's hand the card is being played from.
   * @param cardIdxInHand The index in the specified hand to play the card from.
   * @param row           The row in the grid to play the card to.
   * @param column        The column in the grid to play the card to.
   * @throws IllegalStateException    If it is not the specified player's turn
   * @throws IllegalArgumentException If the cardIdxInHand, row, or column parameters are out-of-bounds.
   * @throws IllegalArgumentException If the specified move is invalid (such as playing to a hole or a filled Card Cell)
   */
  @Override
  public void playToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row, int column) throws IllegalStateException, IllegalArgumentException {

  }

  /**
   * Returns the current deck.
   *
   * @return the current deck.
   */
  @Override
  public List<ThreeTriosCard> getDeck() {
    return null;
  }

  /**
   * Returns the Grid in its current state.
   *
   * @return Current Grid.
   */
  @Override
  public ThreeTriosGrid getGrid() {
    return null;
  }

  /**
   * Returns the Player whose turn it is to play.
   *
   * @return Player who needs to play.
   */
  @Override
  public ThreeTriosPlayer getCurrentPlayer() {
    return null;
  }

  /**
   * Returns a copy of the hand of the specified Player.
   *
   * @param p whose hand we want.
   * @return a copy of the hand of the specified Player p.
   */
  @Override
  public int getHand(ThreeTriosPlayer p) {
    return 0;
  }
}
