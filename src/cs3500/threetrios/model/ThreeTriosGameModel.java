
package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is set up and started in the constructor.
*/
public class ThreeTriosGameModel implements ThreeTriosModel {
  
  private final List<ThreeTriosCard> deck;
  /* INVARIANT: Every card in the game has a unique name */
  private final ThreeTriosGrid grid;
  private final Map<ThreeTriosPlayer, List<ThreeTriosCard>> playerHands;
  private final ThreeTriosPlayer playerRed;
  private final ThreeTriosPlayer playerBlue;
  private final ThreeTriosBattleRules battleRules;
  private ThreeTriosPlayer currentPlayer;
  
  /**
   * Constructor for ThreeTrios Model.
   * To start the game, there must be enough cards to fill both players’ hands 
   * and fill every card cell. 
   * Therefore, if N is the number of card cells on the grid, 
   * there must be at least N+1 cards available in the game to split between the players.
   * With a valid grid and list of cards to play with, each player is dealt their cards at random 
   * from the list. 
   * Each player’s hand is filled with exactly N+1/2 cards 
   * where N is the number of card cells on the grid.
   *
   * @param grid The grid to use, given by a configuration class.
   *       To preserve the integrity of the game state, this should not have any cards
   *       played to it yet.
   * @param deck The deck of cards to use.
   * @throws IllegalArgumentException if the number of cards is not exactly N+1, 
   *     where N is the number of card cells.
   * @throws IllegalArgumentException if the provided grid has been played to already.
   * @throws IllegalArgumentException if the provided cards are not unique.
   */
  public ThreeTriosGameModel(
      ThreeTriosGrid grid,
      List<ThreeTriosCard> deck,
      ThreeTriosBattleRules battleRules,
      boolean shuffle
  ) throws IllegalArgumentException {
    if (!(deck.size() > grid.getNumCardCells())) {
      throw new IllegalArgumentException(
          "There are "
              + deck.size()
              + " cards in the deck when there should be at least "
              + (grid.getNumCardCells() + 1)
      );
    }
    if (grid.getNumCards() != 0) {
      throw new IllegalArgumentException(
          "The grid should not have any cards played to it yet!"
      );
    }
    this.grid = grid;
    this.playerRed = ThreeTriosPlayer.RED;
    this.playerBlue = ThreeTriosPlayer.BLUE;
    this.currentPlayer = ThreeTriosPlayer.RED;
    this.battleRules = battleRules;

    // Initializing deck.
    this.deck = new ArrayList<>();
    Set<String> uniqueNames = new HashSet<String>();
    for (ThreeTriosCard card : deck) {
      if (uniqueNames.contains(card.getName())) {
        throw new IllegalArgumentException(
            "The provided deck has duplicate names of cards,"
                + " which violates an invariant!!!"
        );
      }
      uniqueNames.add(card.getName());
      this.deck.add(card);
    }

    // Initialize hands
    Random random = new Random();
    this.playerHands = new HashMap<ThreeTriosPlayer, List<ThreeTriosCard>>();
    for (ThreeTriosPlayer player : ThreeTriosPlayer.values()) {
      List<ThreeTriosCard> playerCards = new ArrayList<>();
      for (int i = 0; i < (grid.getNumCardCells() + 1) / 2; ++i) {
        int index = 0;
        if (shuffle) {
          index = random.nextInt(deck.size());
        }
        playerCards.add(deck.remove(index));
      }
      playerHands.put(player, playerCards);
    }
  }

  /**
   * Constructor for ThreeTriosGameModel (No random for testing). 
   *
   * To start the game, there must be enough cards to fill both players’ hands and fill every card
   * cell.
   * Therefore, if N is the number of card cells on the grid,
   * there must be at least N+1 cards available in the game to split between the players.
   * With a valid grid and list of cards to play with, each player is dealt their cards at random
   * from the list.
   * Each player’s hand is filled with exactly N+1/2 cards where N is the number of card cells on
   * the grid.
   *
   * @param grid The grid to use, given by a configuration class.
   *       To preserve the integrity of the game state, this should not have any cards
   *       played to it yet.
   * @param deck The deck of cards to use.
   * @param battleRules The rules to use in battles.
   * @throws IllegalArgumentException if the number of cards is not exactly N+1, where N is the
   *                                    number of card cells.
   * @throws IllegalArgumentException if the provided grid has been played to already.
   * @throws IllegalArgumentException if the provided cards are not unique.
   */
  public ThreeTriosGameModel(
      ThreeTriosGrid grid,
      List<ThreeTriosCard> deck,
      ThreeTriosBattleRules battleRules
  ) throws IllegalArgumentException {
    this(grid, deck, battleRules, true);
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
   * The winner is determined by counting the number of cards each player owns both on the grid
   * and in their hands. 
   * The player with the most owned cards wins. If no such player exists, the game is a tie.
   * @return true if the game has been won, false otherwise
   */
  public boolean isGameWon() {
    return (isGameOver() 
    && (this.getNumOwnedCards(playerRed) + this.getHand(playerRed).size()) 
    > (this.getNumOwnedCards(playerBlue) + this.getHand(playerBlue).size())) 
    || (isGameOver()
    && (this.getNumOwnedCards(playerRed) + this.getHand(playerRed).size())
    < (this.getNumOwnedCards(playerBlue) + this.getHand(playerBlue).size()));
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
        if (
            !cell.isHole() && cell.getCard() 
                != null && cell.getCard().getPlayer() == player) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Plays a card from the cardIdxInHand position of the given player's hand to the specified row
   * and collumn of the grid.
   * All indices are zero indexed.
   *
   * @param player    The player whose hand the card is being played from.
   * @param cardIdxInHand The index in the specified hand to play the card from.
   * @param row       The row in the grid to play the card to.
   * @param column    The column in the grid to play the card to.
   * @throws IllegalStateException  If it is not the specified player's turn
   * @throws IndexOutOfBoundsException If the cardIdxInHand, row, or column parameters are
   *                  out-of-bounds. (handled in playToCellMethod in Grid).
   * @throws IllegalStateException If the specified move is invalid
   *                (such as playing to a hole or a filled Card Cell) (handled in
   *                playToCellMethod in Grid).
   */
  @Override
  public void playToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
      throws IllegalStateException, IllegalArgumentException {
    if (!player.equals(getCurrentPlayer())) {
      throw new IllegalStateException(
          player.name() + " cannot play, it is " + getCurrentPlayer() + "'s turn."
      );
    }

    ThreeTriosCard playerCard = playerHands.get(player).remove(cardIdxInHand);

    grid.playToCell(row, column, playerCard);

    // Mutation is desired, so we use grid as opposed to getGrid();
    battleRules.battle(playerCard, grid);

    endTurn();
  }
  
  /**
  * Returns the Grid in its current state.
  * @return Current Grid.
  */
  public ThreeTriosGrid getGrid() {
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
   *
   * @param p whose hand we want.
   * @return a copy of the hand of the specified Player p.
   * @throws IllegalArgumentException if player p does not exist or is null
   */
  public ArrayList<ThreeTriosCard> getHand(ThreeTriosPlayer p) {
    if (p == null || !playerHands.containsKey(p)) {
      throw new IllegalArgumentException("Invalid player.");
    }
    return new ArrayList<ThreeTriosCard>(playerHands.get(p));
  }

  /**
  * Ends the current player's turn.
  */
  private void endTurn() {
    if (this.currentPlayer == playerRed) {
      this.currentPlayer = playerBlue;
    }
    else if (this.currentPlayer == playerBlue) {
      this.currentPlayer = playerRed;
    }
  }
}