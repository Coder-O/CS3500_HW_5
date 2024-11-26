package cs3500.threetrios.model;

import java.util.ArrayList;

import cs3500.threetrios.provider.controller.Features;
import cs3500.threetrios.provider.model.IThreeTriosModel;

/**
 * Adapts a ThreeTriosModel to fit the IThreeTriosModel interface.
 */
public class ModelToProviderAdapter implements IThreeTriosModel {

  private final ThreeTriosModel model;

  public ModelToProviderAdapter(ThreeTriosModel model) {
    this.model = model;
  }

  /**
   * Counts the number of CardCells on the model's grid and
   * alters the gridCount field to reflect that.
   */
  @Override
  public int numCardCellOnBoard() {
    return 0;
  }

  /**
   * Returns a String representation of the turn field.
   *
   * @return String, RED if true, BLUE if false.
   */
  @Override
  public String getTurn() {
    return null;
  }

  /**
   * Returns a copy of the grid for observation.
   *
   * @return a 2D ArrayList of Card copy of game grid.
   */
  @Override
  public ArrayList<ArrayList<Cell>> getGrid() {
    return null;
  }

  /**
   * Returns the specified player's hand.
   *
   * @param player String, either RED or BLUE
   * @return an ArrayList of Card of player's hand if valid input is given.
   * @throws IllegalArgumentException if invalid player name is given.
   */
  @Override
  public ArrayList<Card> getPlayerHand(String player) {
    return null;
  }

  /**
   * Determines whether the game is over.
   *
   * @return boolean true if the game is over, false if not.
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Gets the number of rows in the grid.
   *
   * @return the number of rows.
   */
  @Override
  public int getRows() {
    return 0;
  }

  /**
   * Gets the number of columns in the grid.
   *
   * @return the number of columns.
   */
  @Override
  public int getCols() {
    return 0;
  }

  /**
   * Checks if a certain move is legal on the board.
   *
   * @param row the row of that move.
   * @param col the column of that move.
   */
  @Override
  public void isLegalMove(int row, int col) {

  }

  /**
   * Gets a copy of the grid.
   *
   * @return a copy of the grid.
   */
  @Override
  public ArrayList<ArrayList<Cell>> getGridCopy() {
    return null;
  }

  /**
   * Determines who won the game, if it's over.
   *
   * @return String based on who won the game.
   * @throws IllegalStateException if game isn't over yet.
   */
  @Override
  public String whoWonGame() {
    return null;
  }

  /**
   * Gets the number of cards that are flipped when a certain card is played to a certain spot.
   *
   * @param row  the row of the spot being played to.
   * @param col  the column of the spot being plaued to.
   * @param card the card being played to that spot.
   * @return the number of cards flipped to the owner of the card that was played.
   */
  @Override
  public int getCardsFlipped(int row, int col, Card card) {
    return 0;
  }

  /**
   * Returns all cards owned by a player.
   *
   * @param player   the player that's cards are being listed.
   * @param tempGrid the current game grid that is being checked for a player's cards.
   * @return all the player's owned cards in the game.
   */
  @Override
  public int playerOwnedCards(boolean player, ArrayList<ArrayList<Cell>> tempGrid) {
    return 0;
  }

  /**
   * Takes in a location on the board and places the current player's chosen card there.
   * Then calls battle on that location in order to initiate the battle phase on the
   * surrounding cards.
   *
   * @param row     row to place card.
   * @param col     col to place card.
   * @param handIdx index of card to be placed in whoever's turn it is hand.
   * @throws IllegalStateException    if a Card can't be placed in the chosen spot.
   * @throws IllegalArgumentException if the spot chosen is outside the board.
   */
  @Override
  public void placeCard(int row, int col, int handIdx) {

  }

  @Override
  public void addFeaturesListener(Features listener) {

  }
}
