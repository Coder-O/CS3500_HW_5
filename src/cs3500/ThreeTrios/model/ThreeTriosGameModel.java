
package cs3500.ThreeTrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is setup and started in the constructor.
*/
public class ThreeTriosGameModel implements ThreeTriosModel {
    
    private List<Card> deck;
    private Grid grid;
    private Map<ThreeTriosPlayer, List<Card>> hand;
    private ThreeTriosPlayer playerOne;
    private ThreeTriosPlayer playerTwo;
    private ThreeTriosBattleRules battleRules;
    private ThreeTriosPlayer currentPlayer;
    
    /**
     * Constructor for ThreeTrios Model.
     *
     * @param grid The grid to use, given by a configuration class.
     */
    public ThreeTriosGameModel(Grid grid) {
        //todo
        //To start the game, there must be enough cards to fill both players’ hands and fill every card cell. 
        //Therefore, if N is the number of card cells on the grid, 
        //there must be at least N+1 cards available in the game to split between the players.

        //With a valid grid and list of cards to play with, each player is dealt their cards at random 
        //from the list. 
        //Each player’s hand is filled with exactly N+1/2 cards where N is the number of card cells on the grid.
        this.deck = new ArrayList<>();
        this.grid = grid;
        this.hand = new HashMap<ThreeTriosPlayer, List<Card>>();
        this.playerOne = ThreeTriosPlayer.ONE;
        this.playerTwo = ThreeTriosPlayer.TWO;
        this.currentPlayer = playerOne;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        //TODO
        //The game ends when all empty card cells are filled.
        return false;
    }

    /**
     * Checks if the game has been won.
     * @return true if the game has been won, false otherwise
     */
    public boolean isGameWon() {
        //todo
        //The winner is determined by counting the number of cards each player owns both on the grid and in their hands. 
        //The player with the most owned cards wins. If no such player exists, the game is a tie.
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
        if (!player.equals(getCurrentPlayer())) {
            throw new IllegalStateException(
                    player.name() + " cannot play, it is " + getCurrentPlayer() +"'s turn."
            );
        }

        ThreeTriosCard playerCard = hand.get(player).get(cardIdxInHand);

        // getCell inherently throws an IllegalArgumentException if there is no cell there.
        ThreeTriosCell cell = grid.getCell(row, column);

        if (cell.isHole()) {
            throw new IllegalArgumentException("Cannot play to a hole!!!");
        }
        if (cell.getCard() != null) {
            throw new IllegalArgumentException("Cannot play where a card has already been played!");
        }

        cell.setCard(playerCard);

        // Mutation is desired, so wwe use grid as opposed to getGrid();
        battleRules.battle(playerCard, grid);
    }

    /**
    * Returns the current deck.
    * @return the current deck.
    */
    public List<Card> getDeck() {
        return this.deck;
    }
    
    /**
    * Returns the Grid in its current state.
    * @return Current Grid.
    */
    public Grid getGrid() {
        return this.grid;
    }
    
    /**
    * Returns the Player whose turn it is to play.
    * @return Player who needs to play.
    */
    public ThreeTriosPlayer getCurrentPlayer() {
        //todo
        return currentPlayer;
    }
    
    /**
    * Returns a copy of the hand of the specified Player.
    * @return a copy of the hand of the specified Player p.
    * @param p whose hand we want. 
    * @throws IllegalArgumentException if player p does not exist or is null
    */
    public ArrayList<Card> getHand(ThreeTriosPlayer p) {
        if (p == null || !hand.containsKey(p)) {
            throw new IllegalArgumentException("Invalid player.");
        }
        return new ArrayList<Card>(hand.get(p));
    }
}