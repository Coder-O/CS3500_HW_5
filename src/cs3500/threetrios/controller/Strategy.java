package cs3500.threetrios.controller;

/**
 * The basic interface for a strategy for a game of ThreeTrios.
 * Not useful on its own, except as a base that the other strategy types extend.
 */
interface Strategy {
  /**
   * Whether this implementation is guaranteed to find at least one move, if any legal move exists.
   * Useful for determining if a composition of strategies is Fully Complete.
   * @return Whether this implementation is guaranteed to find at least one move, if any legal move
   *          exists.
   */
  boolean findsAtLeastOneMove();
}
