
package cs3500.ThreeTrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is setup and started in the constructor.
*/
public class ThreeTriosGameModel implements ThreeTriosModel {
    
    private List<ThreeTriosCard> deck;
    /* INVARIANT: Every card in the game has a unique name */
    private Grid grid;
    private Map<ThreeTriosPlayer, List<Card>> hand;
    private ThreeTriosPlayer playerRed;
    private ThreeTriosPlayer playerBlue;
    private ThreeTriosBattleRules battleRules;
    private final ThreeTriosPlayer playerOne;
    private final ThreeTriosPlayer playerTwo;
    private ThreeTriosPlayer currentPlayer;
    
    /**
     * Constructor for ThreeTrios Model.
     * 
     * To start the game, there must be enough cards to fill both players’ hands and fill every card cell. 
     * Therefore, if N is the number of card cells on the grid, 
     * there must be at least N+1 cards available in the game to split between the players.
     * With a valid grid and list of cards to play with, each player is dealt their cards at random 
     * from the list. 
     * Each player’s hand is filled with exactly N+1/2 cards where N is the number of card cells on the grid.
     *
     * @param grid The grid to use, given by a configuration class.
     *             To preserve the integrity of the game state, this should not have any cards
     *             played to it yet.
     * @param deck The deck of cards to use.
     * @throws IllegalArgumentException if the number of cards is not exactly N+1, where N is the number of card cells.
     * @throws IllegalArgumentException if the provided grid has been played to already.
     * @throws IllegalArgumentException if the provided cards are not unique.
     */
    public ThreeTriosGameModel(Grid grid, List<ThreeTriosCard> deck, ThreeTriosBattleRules battleRules, ThreeTriosPlayer startingPlayer) {
        if (deck.size() != grid.getNumCardCells() + 1 ) {
            throw new IllegalArgumentException(
                    "There are "
                            + deck.size()
                            + " cards in the deck when there should be "
                            + (grid.getNumCardCells() + 1)
                            + " instead."
            );
        }
        if (grid.getNumCards() != 0) {
            throw new IllegalArgumentException(
                    "The grid should not have any cards played to it yet!"
            );
        }
        this.grid = grid;
        this.hand = new HashMap<ThreeTriosPlayer, List<Card>>();
        this.playerRed = ThreeTriosPlayer.RED;
        this.playerBlue = ThreeTriosPlayer.RED;
        this.currentPlayer = playerRed;

        this.deck = new ArrayList<>();

        // Initializing deck.
        Set<String> uniqueNames = new HashSet<String>();
        for (ThreeTriosCard card : deck) {
            if (uniqueNames.contains(card.getName())) {
                throw new IllegalArgumentException(
                        "The provided deck has duplicate names of cards,"
                                + " which violates an invariant!!!"
                );
            }

            uniqueNames.add(card.getName());
            deck.add(card);
        }

        this.battleRules = battleRules;
        this.playerHands = new HashMap<ThreeTriosPlayer, List<Card>>();
        this.playerOne = ThreeTriosPlayer.ONE;
        this.playerTwo = ThreeTriosPlayer.TWO;
        this.currentPlayer = startingPlayer;

        //todo random 
        //todo intialize deck (random)
        //todo intialize hands
        //todo intialize grid

    }

    /**
     * Checks if the game is over.
     * The game ends when all empty card cells are filled.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int column = 0; column < grid.getNumColumns(); column++) {
                ThreeTriosCell cell = grid.getCell(row, column);
                // If the cell is a card cell (not a hole) and is empty, the game isn't over
                if (!cell.isHole() && cell.getCard() == null) {
                    return false;
                }
            }
        }
        return true; //if no non-empty cell was found, the game is over
    }

    /**
     * Checks if the game has been won.
     * The winner is determined by counting the number of cards each player owns both on the grid and in their hands. 
     * The player with the most owned cards wins. If no such player exists, the game is a tie.
     * @return true if the game has been won, false otherwise
     */
    public boolean isGameWon() {
        if (isGameOver()
        && this.getNumOwnedCards(playerRed) + this.getHand(playerRed).size() 
        > this.getNumOwnedCards(playerBlue) + this.getHand(playerBlue).size()) {
            return true;
        }
        if (isGameOver()
        && this.getNumOwnedCards(playerRed) + this.getHand(playerRed).size() 
        < this.getNumOwnedCards(playerBlue) + this.getHand(playerBlue).size()) {
            return true;
        }
        return false;
    }

    /**
     * Count the number of cards each player owns on the grid.
     * @param player a player
     * @return the number of cards.
     */
    private int getNumOwnedCards(ThreeTriosPlayer player) {
        int count = 0;
        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int column = 0; column < grid.getNumColumns(); column++) {
                ThreeTriosCell cell = grid.getCell(row, column);
                if (!cell.isHole() && cell.getCard() != null && cell.getCard().getPlayer() == player) {
                    count++;
                }
            }
        }
        return count;
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
                    player.name() + " cannot play, it is " + getCurrentPlayer() + "'s turn."
            );
        }

        ThreeTriosCard playerCard = playerHands.get(player).get(cardIdxInHand);

        // getCell inherently throws an IllegalArgumentException if there is no cell there.
        ThreeTriosCell cell = grid.getCell(row, column);

        if (cell.isHole()) {
            throw new IllegalArgumentException("Cannot play to a hole!!!");
        }
        if (cell.getCard() != null) {
            throw new IllegalArgumentException("Cannot play where a card has already been played!");
        }

        cell.setCard(playerCard);

        // Mutation is desired, so we use grid as opposed to getGrid();
        battleRules.battle(playerCard, grid);

        endTurn();
    }

    /**
    * Returns the current deck.
    * @return the current deck.
    */
    public List<ThreeTriosCard> getDeck() {
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
        return currentPlayer;
    }
    
    /**
    * Returns a copy of the hand of the specified Player.
    * @return a copy of the hand of the specified Player p.
    * @param p whose hand we want.
    * @throws IllegalArgumentException if player p does not exist or is null
    */
    public ArrayList<Card> getHand(ThreeTriosPlayer p) {
        if (p == null || !playerHands.containsKey(p)) {
            throw new IllegalArgumentException("Invalid player.");
        }
        return new ArrayList<Card>(playerHands.get(p));
    }

    /**
    * Ends the current player's turn.
    */
    public void endTurn() {
        if (this.currentPlayer == playerRed) {
            this.currentPlayer = playerBlue;
        }
        else if (this.currentPlayer == playerBlue) {
            this.currentPlayer = playerRed;
        }
    }
}