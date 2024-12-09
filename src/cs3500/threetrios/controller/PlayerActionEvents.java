package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Handles the actions a player might do.
 */
public interface PlayerActionEvents {
  /**
   * Handles when a player clicks a card in their hand.
   * @param index the index of the clicked card in the player's hand.
   * @param player the current player.
   */
  void handleCardSelection(Integer index, ThreeTriosPlayer player);

  /**
   * Handles when a player selects a cell on the grid.
   * @param row the row of the clicked cell.
   * @param col the column of the clicked cell.
   */
  void handleGridCellSelection(int row, int col);

  /**
   * Returns the selected card's index.
   * @return the selected card's index.
   */
  public int getSelectedCardIdx();
}
