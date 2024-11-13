package cs3500.threetrios.model;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is setup and started in the constructor.
*/
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel {

  /**
   * Plays a card from the cardIdxInHand position of the given player's hand
   * to the specified row and collumn of the grid.
   * All indices are zero indexed.
   * @param player The player whose hand the card is being played from.
   * @param cardIdxInHand The index in the specified hand to play the card from.
   * @param row The row in the grid to play the card to.
   * @param column The column in the grid to play the card to.
   * @throws IllegalStateException If the game is over.
   * @throws IllegalArgumentException If player is null.
   * @throws IllegalStateException If it is not the specified player's turn
   * @throws IndexOutOfBoundsException If the cardIdxInHand, row,
   *         or column parameters are out-of-bounds.
   * @throws IllegalStateException If the specified move is invalid
   *         (such as playing to a hole or a filled Card Cell).
   */
  void playToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
        throws IllegalStateException, IndexOutOfBoundsException, IllegalArgumentException;


}
