package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.controller.PlayerController;
import cs3500.threetrios.provider.controller.Features;
import cs3500.threetrios.provider.model.ICard;
import cs3500.threetrios.provider.model.IThreeTriosModel;

/**
 * Adapts a ThreeTriosModel to fit the IThreeTriosModel interface.
 */
public class ModelToProviderAdapter implements IThreeTriosModel {

  private final ThreeTriosModel model;

  private ThreeTriosPlayer currPlayer;

  /**
   * Adapts a {@link ThreeTriosModel} into a {@link IThreeTriosModel}.
   * @param model the model to adapt.
   */
  public ModelToProviderAdapter(ThreeTriosModel model) {
    this.model = model;
  }

  /**
   * Counts the number of CardCells on the model's grid and
   * alters the gridCount field to reflect that.
   */
  @Override
  public int numCardCellOnBoard() {
    return model.getGrid().getNumCardCells();
  }

  /**
   * Returns a String representation of the turn field.
   *
   * @return String, RED if true, BLUE if false.
   */
  @Override
  public String getTurn() {
    currPlayer = model.getCurrentPlayer();

    return currPlayer.getName();
  }

  /**
   * Returns a copy of the grid for observation.
   *
   * @return a 2D ArrayList of Card copy of game grid.
   */
  @Override
  public ArrayList<ArrayList<cs3500.threetrios.provider.model.Cell>> getGrid() {

    //The outer ArrayList represents the columns, inner ArrayList the rows


    // Initialize the 2D ArrayList to hold the grid copy
    ArrayList<ArrayList<cs3500.threetrios.provider.model.Cell>> gridCopy = new ArrayList<>();

    // Assuming the model provides the number of rows and columns
    int numRows = model.getGrid().getNumRows();
    int numCols = model.getGrid().getNumColumns();

    // Iterate over columns first
    for (int col = 0; col < numCols; col++) {
      // Create a new column for the grid copy
      ArrayList<cs3500.threetrios.provider.model.Cell> columnCopy = new ArrayList<>();

      // Iterate over rows for the current column
      for (int row = 0; row < numRows; row++) {
        // Get the ThreeTriosCell from the model
        ThreeTriosCell originalCell = model.getGrid().getCell(row, col);

        if (originalCell.isHole()) {
          // Wrap it in the adapter and add to the column
          columnCopy.add(new CellToCellAdapter(originalCell, row, col).toCell());
        } else {
          columnCopy.add(new CellToICardCellAdapter(originalCell, row, col));
        }
      }

      // Add the column to the grid copy
      gridCopy.add(columnCopy);
    }

    return gridCopy;
  }

  /**
   * Returns the specified player's hand.
   *
   * @param player String, either RED or BLUE
   * @return an ArrayList of Card of player's hand if valid input is given.
   * @throws IllegalArgumentException if invalid player name is given.
   */
  @Override
  public ArrayList<ICard> getPlayerHand(String player) {

    // Initialize the list to hold the adapted hand
    ArrayList<ICard> adaptedHand = new ArrayList<ICard>();

    // Get the hand based on the player's color
    if (player.equals("RED")) {
      List<ThreeTriosCard> redHand = model.getHand(ThreeTriosPlayer.RED);
      for (ThreeTriosCard card : redHand) {
        adaptedHand.add(new CardToCardAdapter(card).toCard());
      }
    } else {
      List<ThreeTriosCard> blueHand = model.getHand(ThreeTriosPlayer.BLUE);
      for (ThreeTriosCard card : blueHand) {
        adaptedHand.add(new CardToCardAdapter(card).toCard());
      }
    }

    return adaptedHand;
  }

  /**
   * Determines whether the game is over.
   *
   * @return boolean true if the game is over, false if not.
   */
  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  /**
   * Returns all cards owned by a player.
   *
   * @param player the player that's cards are being listed.
   * @param tempGrid the current game grid that is being checked for a player's cards.
   * @return all the player's owned cards in the game.
   */
  @Override
  public int playerOwnedCards(boolean player,
                              ArrayList<ArrayList<cs3500.threetrios.provider.model.Cell>>
                                      tempGrid) {
    if (player) {
      return model.getNumOwnedCards(ThreeTriosPlayer.RED);
    }
    return model.getNumOwnedCards(ThreeTriosPlayer.BLUE);
  }

  /**
   * Gets the number of rows in the grid.
   *
   * @return the number of rows.
   */
  @Override
  public int getRows() {
    return model.getGrid().getNumRows();
  }

  /**
   * Gets the number of columns in the grid.
   *
   * @return the number of columns.
   */
  @Override
  public int getCols() {
    return model.getGrid().getNumColumns();
  }

  /**
   * Gets the number of cards that are flipped when a certain card is played to a certain spot.
   *
   * @param row the row of the spot being played to.
   * @param col the column of the spot being plaued to.
   * @param card the card being played to that spot.
   * @return the number of cards flipped to the owner of the card that was played.
   */
  public int getCardsFlipped(int row, int col, ICard card) {
    List<ICard> hand = this.getPlayerHand(currPlayer.getName());

    for (int i = 0; i < hand.size(); i++) {
      if (hand.get(i).equals(card)) {
        return model.getMoveScore(currPlayer, i, row, col);
      }
    }
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
    model.canPlayToGrid(currPlayer, 0, row, col);
  }

  /**
   * Gets a copy of the grid.
   *
   * @return a copy of the grid.
   */
  @Override
  public ArrayList<ArrayList<cs3500.threetrios.provider.model.Cell>> getGridCopy() {

    //The outer ArrayList represents the columns, inner ArrayList the rows


    // Initialize the 2D ArrayList to hold the grid copy
    ArrayList<ArrayList<cs3500.threetrios.provider.model.Cell>> gridCopy = new ArrayList<>();

    // Assuming the model provides the number of rows and columns
    int numRows = model.getGrid().getNumRows();
    int numCols = model.getGrid().getNumColumns();

    // Iterate over columns first
    for (int col = 0; col < numCols; col++) {
      // Create a new column for the grid copy
      ArrayList<cs3500.threetrios.provider.model.Cell> columnCopy = new ArrayList<>();

      // Iterate over rows for the current column
      for (int row = 0; row < numRows; row++) {
        // Get the ThreeTriosCell from the model
        ThreeTriosCell originalCell = model.getGrid().getCell(row, col);

        if (originalCell.isHole()) {
          // Wrap it in the adapter and add to the column
          columnCopy.add(new CellToCellAdapter(originalCell, row, col).toCell());
        } else {
          columnCopy.add(new CellToICardCellAdapter(originalCell, row, col));
        }
      }

      // Add the column to the grid copy
      gridCopy.add(columnCopy);
    }

    return gridCopy;
  }

  /**
   * Determines who won the game, if it's over.
   *
   * @return String based on who won the game.
   * @throws IllegalStateException if game isn't over yet.
   */
  @Override
  public String whoWonGame() {
    return model.getWinner().toString();
  }

  /**
   * Sets initial turn in controller to the correct one for each player.
   */
  public void startGame() {
    model.startGame();
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
    model.playToGrid(currPlayer, handIdx, row, col);
  }

  /**
   * Adds an observer (the controller) to this model
   * so that the model can communicate with the controller.
   *
   * <p>NOTE: This implementation has limited functionality, and can only add listeners that
   * also implement {@link PlayerController}.
   *
   * @param listener the controller being added.
   * @throws IllegalArgumentException If the provided listener does not also
   *                                  implement {@link PlayerController}
   */
  @Override
  public void addFeaturesListener(Features listener) throws IllegalArgumentException {

    // Actually implementing full functionality for a different controller
    // would require restructuring our program or making assumptions on when and how a model calls
    // its controllers. Either way, we lose generality, so this choice was made.
    if (listener instanceof PlayerController) {
      model.addControllerListener(
              (PlayerController) listener,
              ((PlayerController) listener).getPlayer()
      );
    } else {
      throw new IllegalArgumentException(
              "This implementation can only deal with PlayerControllers!"
                      + " The provided listener was not a PlayerController!"
      );
    }
  }
}
