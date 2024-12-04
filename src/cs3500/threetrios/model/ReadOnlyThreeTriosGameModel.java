package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * All observations methods of the model.
 */
public class ReadOnlyThreeTriosGameModel implements ReadOnlyThreeTriosModel {

  /* INVARIANT: Every card in the game has a unique name */
  protected final ThreeTriosGrid grid;
  protected final Map<ThreeTriosPlayer, List<ThreeTriosCard>> playerHands;
  protected final ThreeTriosPlayer playerRed;
  protected final ThreeTriosPlayer playerBlue;
  final ThreeTriosBattleRules battleRules;
  protected ThreeTriosPlayer currentPlayer;

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
   * @param battleRules The rules to use.
   * @param shuffle Whether to shuffle.
   * @param random The random object to use
   * @throws IllegalArgumentException if the number of cards is not exactly N+1,
   *     where N is the number of card cells.
   * @throws IllegalArgumentException if the provided grid has been played to already.
   * @throws IllegalArgumentException if the provided cards are not unique.
   */
  public ReadOnlyThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules,
          boolean shuffle,
          Random random
  ) throws IllegalArgumentException {
    if (deck.size() < grid.getNumCardCells()) {
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
    List<ThreeTriosCard> deck1 = new ArrayList<>();
    Set<String> uniqueNames = new HashSet<String>();
    for (ThreeTriosCard card : deck) {
      if (uniqueNames.contains(card.getName())) {
        throw new IllegalArgumentException(
                "The provided deck has duplicate names of cards,"
                        + " which violates an invariant!!!"
        );
      }
      uniqueNames.add(card.getName());
      deck1.add(card);
    }

    // Initialize hands
    this.playerHands = new HashMap<ThreeTriosPlayer, List<ThreeTriosCard>>();
    for (ThreeTriosPlayer player : ThreeTriosPlayer.values()) {
      List<ThreeTriosCard> playerCards = new ArrayList<>();
      for (int i = 0; i < (grid.getNumCardCells() + 1) / 2; ++i) {
        int index = 0;
        if (shuffle) {
          index = random.nextInt(deck1.size());
        }
        ThreeTriosCard card = deck1.remove(index);
        card.setPlayer(player);
        playerCards.add(card);
      }
      playerHands.put(player, playerCards);
    }
  }

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
   * @param battleRules The rules to use.
   * @param shuffle Whether to shuffle.
   * @throws IllegalArgumentException if the number of cards is not exactly N+1,
   *     where N is the number of card cells.
   * @throws IllegalArgumentException if the provided grid has been played to already.
   * @throws IllegalArgumentException if the provided cards are not unique.
   */
  public ReadOnlyThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules,
          boolean shuffle
  ) throws IllegalArgumentException {
    this(grid, deck, battleRules, shuffle, new Random());
  }

  /**
   * Constructor for ThreeTriosGameModel (No random for testing).
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
  public ReadOnlyThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules
  ) throws IllegalArgumentException {
    this(grid, deck, battleRules, true);
  }

  /**
   * A constructor for creating a copy of a given game model.
   * This should not be generally used, as it lacks the protections of a standard constructor.
   * @param grid The grid.
   * @param map The player hands.
   * @param playerRed the red player.
   * @param playerBlue the blue player.
   * @param battleRules The battle rules to use.
   * @param currentPlayer The current player.
   */
  protected ReadOnlyThreeTriosGameModel(
          ThreeTriosGrid grid,
          Map<ThreeTriosPlayer, List<ThreeTriosCard>> map,
          ThreeTriosPlayer playerRed,
          ThreeTriosPlayer playerBlue,
          ThreeTriosBattleRules battleRules,
          ThreeTriosPlayer currentPlayer
  ) {
    this.grid = grid.copy();
    this.playerHands = Map.copyOf(map);
    this.playerRed = playerRed;
    this.playerBlue = playerBlue;
    this.battleRules = battleRules;
    this.currentPlayer = currentPlayer;
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
   * Returns the winning player or null if the game was a tie.
   * @return The wining player.
   * @throws IllegalStateException If the game isn't over.
   */
  @Override
  public ThreeTriosPlayer getWinner() {
    if (!isGameOver()) {
      throw new IllegalStateException("The game isn't over! There can be no winner!");
    }

    if (getScore(playerRed) > getScore(playerBlue)) {
      return playerRed;
    }

    if (getScore(playerBlue) > getScore(playerRed)) {
      return playerBlue;
    }
    return null;
  }

  /**
   * Returns this player's score.
   * A player’s score is the number of cards that player owns in their hand
   * plus the number of cards that player owns on the grid.
   * @param player the player whose score we want
   * @return the score
   */
  public int getScore(ThreeTriosPlayer player) {
    return getNumOwnedCards(player) + getHand(player).size();
  }

  /**
   * Count the number of cards each player owns on the grid.
   * @param player a player
   * @return the number of cards.
   */
  @Override
  public int getNumOwnedCards(ThreeTriosPlayer player) {
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
   * Returns the Grid in its current state.
   * @return Current Grid.
   */
  public ThreeTriosGrid getGrid() {
    return this.grid.copy();
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
  public List<ThreeTriosCard> getHand(ThreeTriosPlayer p) {
    if (p == null || !playerHands.containsKey(p)) {
      throw new IllegalArgumentException("Invalid player.");
    }
    ArrayList<ThreeTriosCard> toReturn = new ArrayList<>();
    for (ThreeTriosCard card : playerHands.get(p)) {
      toReturn.add(card.copy());
    }
    return toReturn;
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
   * @throws IllegalStateException    If the game is over.
   * @throws IllegalArgumentException If player is null.
   * @throws IllegalStateException    If it is not the specified player's turn.
   * @throws IndexOutOfBoundsException If the cardIdxInHand, row,
   *                                  or column parameters are out-of-bounds.
   * @throws IllegalArgumentException If the specified move is invalid
   *                                  (such as playing to a hole or a filled Card Cell).
   */
  @Override
  public int getMoveScore(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
          throws IllegalStateException, IndexOutOfBoundsException, IllegalArgumentException {
    if (isGameOver()) {
      throw new IllegalStateException("The game is over!");
    }

    if (player == null) {
      throw new IllegalArgumentException("Player should not be null!");
    }

    if (!player.equals(getCurrentPlayer())) {
      throw new IllegalStateException(
              player.name() + " cannot play, it is " + getCurrentPlayer() + "'s turn."
      );
    }
    ThreeTriosCard copyPlayerCard = playerHands.get(player).get(cardIdxInHand).copy();

    ThreeTriosGrid copyGrid = grid.copy();

    copyGrid.playToCell(row, column, copyPlayerCard);

    // Mutation is not desired, so we use a copy of the grid
    return battleRules.battle(copyPlayerCard, copyGrid);
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
  public Optional<Exception> canPlayToGrid(ThreeTriosPlayer player,
                                           int cardIdxInHand, int row, int column) {
    try {
      getMoveScore(player, cardIdxInHand, row, column);
    } catch (Exception e) {
      return Optional.of(e);
    }

    return Optional.empty();
  }

  /**
   * Returns a mutable copy of this model.
   *
   * @return A mutable copy of this model.
   */
  @Override
  public ThreeTriosModel getMutableCopy() {
    throw new RuntimeException("This model cannot be mutable!");
  }
}
