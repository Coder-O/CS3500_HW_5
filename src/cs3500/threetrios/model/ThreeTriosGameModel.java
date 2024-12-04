
package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cs3500.threetrios.controller.PlayerController;

/**
* Represents the model for the ThreeTrios Game.
* Model provides methods to perform game actions and retrieve the game state.
* The game is set up and started in the constructor.
*/
public class ThreeTriosGameModel extends ReadOnlyThreeTriosGameModel implements ThreeTriosModel {
  private final Map<ThreeTriosPlayer, PlayerController> playerControllers;

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
    this.playerControllers = new HashMap<>();
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
    this.playerControllers = new HashMap<>();
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
    this.playerControllers = new HashMap<>();
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
  private ThreeTriosGameModel(ThreeTriosGrid grid, Map<ThreeTriosPlayer, List<ThreeTriosCard>> map,
                              ThreeTriosPlayer playerRed, ThreeTriosPlayer playerBlue,
                              ThreeTriosBattleRules battleRules, ThreeTriosPlayer currentPlayer) {
    super(grid, map, playerRed, playerBlue, battleRules, currentPlayer);
    this.playerControllers = new HashMap<>();
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
    update();
  }

  @Override
  public ThreeTriosModel getMutableCopy() {
    return new ThreeTriosGameModel(
            grid.copy(),
            Map.copyOf(playerHands),
            playerRed,
            playerBlue,
            battleRules,
            currentPlayer
    );
  }

  /**
   * Adds a controller listener for a specific player.
   *
   * @param controller The controller to add.
   * @param playerFor What three trios player the controller controls.
   */
  @Override
  public void addControllerListener(PlayerController controller, ThreeTriosPlayer playerFor) {
    playerControllers.put(playerFor, controller);
  }

  /**
   * Starts the game by updating all controllers
   * and informing the starting player's controller that it is their turn.
   */
  @Override
  public void startGame() throws IllegalStateException {
    update();
  }

  /**
   * Updates all controllers and asks the current player to make a move.
   * Should be called after every move.
   */
  private void update() {
    for (PlayerController controller : playerControllers.values()) {
      controller.update();
    }

    if (!isGameOver() && !playerControllers.isEmpty()) {
      playerControllers.get(currentPlayer).isYourTurn();
    }
  }
}