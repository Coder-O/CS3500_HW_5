package cs3500.threetrios.model;

import java.util.List;

/**
 * All observations methods of the model.
 */
public interface ReadOnlyThreeTriosModel {

  /**
   * Checks if the game is over.
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Returns the winning player or null if the game was a tie.
   * @return The wining player.
   * @throws IllegalStateException If the game isn't over.
   */
  ThreeTriosPlayer getWinner() throws IllegalStateException;

  /**
   * Returns this player's score.
   * A player’s score is the number of cards that player owns in their hand
   * plus the number of cards that player owns on the grid.
   * @param player the player whose score we want
   * @return the score
   */
  int getScore(ThreeTriosPlayer player);

  /**
   * Returns the Grid in its current state.
   * @return Current Grid.
   */
  ThreeTriosGrid getGrid();

  /**
   * Returns the Player whose turn it is to play.
   * @return Player who needs to play.
   */
  ThreeTriosPlayer getCurrentPlayer();

  /**
   * Returns a copy of the hand of the specified Player.
   *
   * @param p whose hand we want.
   * @return a copy of the hand of the specified Player p.
   */
  List<ThreeTriosCard> getHand(ThreeTriosPlayer p);
}
