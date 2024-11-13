
package cs3500.threetrios.model;

import java.util.List;
import java.util.Random;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is set up and started in the constructor.
*/
public class ThreeTriosGameModel extends ReadOnlyThreeTriosGameModel implements ThreeTriosModel {

  /**
   * Constructor for the mutable ThreeTriosGameModel, initializing it with
   * the grid, deck, and battle rules, and optionally shuffling the deck.
   *
   * @param grid The grid to use, given by a configuration class.
   * @param deck The deck of cards to use.
   * @param battleRules The rules to use in battles.
   * @param shuffle Whether to shuffle the deck.
   * @param random The random object to use for shuffling.
   */
  public ThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules,
          boolean shuffle,
          Random random
  ) {
    super(grid, deck, battleRules, shuffle, random);
  }

  /**
   * Constructor for ThreeTriosGameModel with shuffling option and a default Random.
   *
   * @param grid The grid to use.
   * @param deck The deck of cards to use.
   * @param battleRules The rules to use in battles.
   * @param shuffle Whether to shuffle the deck.
   */
  public ThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules,
          boolean shuffle) {
    super(grid, deck, battleRules, shuffle);
  }

  /**
   * Constructor for ThreeTriosGameModel without shuffling.
   *
   * @param grid The grid to use.
   * @param deck The deck of cards to use.
   * @param battleRules The rules to use in battles.
   */
  public ThreeTriosGameModel(
          ThreeTriosGrid grid,
          List<ThreeTriosCard> deck,
          ThreeTriosBattleRules battleRules) {
    super(grid, deck, battleRules);
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
   * @throws IllegalStateException  If the game is over.
   * @throws IllegalArgumentException If player is null.
   * @throws IllegalStateException  If it is not the specified player's turn.
   * @throws IndexOutOfBoundsException If the cardIdxInHand, row, or column parameters are
   *                  out-of-bounds. (handled in playToCellMethod in Grid).
   * @throws IllegalStateException If the specified move is invalid
   *                (such as playing to a hole or a filled Card Cell) (handled in
   *                playToCellMethod in Grid).
   */
  @Override
  public void playToGrid(ThreeTriosPlayer player, int cardIdxInHand, int row, int column)
      throws IllegalStateException, IndexOutOfBoundsException, IllegalArgumentException {
    if (isGameOver()) {
      throw new IllegalStateException("The game is over!");
    }

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null!");
    }

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