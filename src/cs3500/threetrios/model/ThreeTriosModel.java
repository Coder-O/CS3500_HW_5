package cs3500.threetrios.model;

import java.util.List;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is setup and started in the constructor.
*/
public interface ThreeTriosModel {
    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();

    /**
     * Checks if the game has been won.
     * @return true if the game has been won, false otherwise
     */
    boolean isGameWon();

    /**
     * Returns the winning player.
     * @return The wining player.
     * @throws IllegalStateException If the game isn't over.
     */
    ThreeTriosPlayer getWinner() throws IllegalStateException;

    /**
     * Plays a card from the cardIdxInHand position of the given player's hand 
     * to the specified row and collumn of the grid.
     * All indicies are zero indexed.
     * @param player The player whose hand the card is being played from.
     * @param cardIdxInHand The index in the spcified habd to play the card from.
     * @param row The row in the grid to play the card to.
     * @param column The column in the grid to play the card to.
     * @throws IllegalStateException If it is not the specified player's turn
     * @throws IllegalArgumentException If the cardIdxInHand, row, 
     * or column parameters are out-of-bounds.
     * @throws IllegalArgumentException If the specified move is invalid 
     * (such as playing to a hole or a filled Card Cell).
     */
    void playToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
    throws IllegalStateException, IllegalArgumentException;
    
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
