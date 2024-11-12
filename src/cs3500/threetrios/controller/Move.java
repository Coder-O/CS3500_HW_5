package cs3500.threetrios.controller;

import java.util.Objects;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents a move in a game of Three Trios.
 * A way of tracking and organizing all data required to make a move.
 * Moves are immutable.
 * All indices are zero indexed.
 */
class Move {
  private final ThreeTriosPlayer player;
  private final int cardIdxInHand;
  private final int rowIdx;
  private final int collumnIdx;

  /**
   * Creates a new move.
   * @param player The player to make the move.
   * @param cardIdxInHand The index of the card to play in the player's hand.
   * @param rowIdx The index of the row to play to.
   * @param collumnIdx The index og the column to play to.
   * @throws IllegalArgumentException If player is null.
   */
  Move(ThreeTriosPlayer player, int cardIdxInHand, int rowIdx, int collumnIdx)
          throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null!");
    }

    this.player = player;
    this.cardIdxInHand = cardIdxInHand;
    this.rowIdx = rowIdx;
    this.collumnIdx = collumnIdx;
  }

  /**
   * Returns the player of this move.
   * @return the player of this move.
   */
  public ThreeTriosPlayer getPlayer() {
    return player;
  }

  /**
   * Returns the index of the desired card in the hand of this move's player.
   * @return the index of the desired card.
   */
  public int getCardIdxInHand() {
    return cardIdxInHand;
  }

  /**
   * Returns the index of the row to play to.
   * @return the index of the row to play to.
   */
  public int getRowIdx() {
    return rowIdx;
  }

  /**
   * Returns the index of the collumn to play to.
   * @return The index of the collumn to play to.
   */
  public int getCollumnIdx() {
    return collumnIdx;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Move other = (Move) o;
    return cardIdxInHand == other.cardIdxInHand
            && rowIdx == other.rowIdx
            && collumnIdx == other.collumnIdx
            && player == other.player;
  }

  @Override
  public int hashCode() {
    return Objects.hash(player, cardIdxInHand, rowIdx, collumnIdx);
  }
}
