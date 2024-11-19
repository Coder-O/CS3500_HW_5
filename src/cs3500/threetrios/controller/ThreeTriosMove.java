package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents a move in a game of Three Trios.
 * A way of tracking and organizing all data required to make a move.
 * Moves are immutable.
 * All indices are zero indexed.
 */
public interface ThreeTriosMove {
  ThreeTriosPlayer getPlayer();

  int getCardIdxInHand();

  int getRowIdx();

  int getCollumnIdx();

  int getScore();

  /**
   * Sets the score of this move.
   * @param score The new score.
   */
  void setScore(int score);
}
