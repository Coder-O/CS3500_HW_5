package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A set of rules for when cards do battle in a game of Three Trios.
 * By these rules, an attacker wins a battle if it's attack value in 
 * the direction of its opponent is greater than that of its opponent's 
 * in its direction. Otherwise, the defender wins.
 *
 *  <p>If the attacker wins, then the defender is flipped, and battles with its neighbors</p>
 */
public class SimpleRules implements ThreeTriosBattleRules {

  private List<ThreeTriosCard> flippedThisTurn;
  private int numFlippedThisTurn;
  private ThreeTriosGrid grid;
  private final ThreeTriosBattleComparison comparisonStrategy;

  /**
   * A set of rules for when cards do battle in a game of Three Trios.
   * By these rules, an attacker wins a battle if it's attack value in
   * the direction of its opponent is greater than that of its opponent's
   * in its direction, as determined by the provided comparisonStrategy.
   * Otherwise, the defender wins.
   *
   *  <p>If the attacker wins, then the defender is flipped, and battles with its neighbors</p>
   *
   * @param comparisonStrategy The strategy to compare two attack values.
   */
  public SimpleRules(ThreeTriosBattleComparison comparisonStrategy) {
    this.comparisonStrategy = comparisonStrategy;
    flippedThisTurn = new ArrayList<>();
  }

  /**
   * Handles the battling and flipping of cards after a card has been played.
   *
   * <p>By these rules, an attacker wins a battle if it's attack value in
   * the direction of its opponent is greater than that of its opponent's
   * in its direction. Otherwise, the defender wins.</p>
   *
   *  <p>If the attacker wins, then the defender is flipped, and battles with it's neighbors.</p>
   *
   * @param cardPlayed The card that was played this turn.
   * @param grid       The grid the card is in.
   * @return The number of cards flipped by this action, including the original card played.
   */
  @Override
  public int battle(ThreeTriosCard cardPlayed, ThreeTriosGrid grid) {
    flippedThisTurn = new ArrayList<>();
    numFlippedThisTurn = 1;
    this.grid = grid;

    flippedThisTurn.add(cardPlayed);

    ThreeTriosCard attacker;

    while (!flippedThisTurn.isEmpty()) {
      attacker = flippedThisTurn.remove(0);
      battleWithNeighbors(attacker);
    }

    return numFlippedThisTurn;
  }

  /**
   * Battles the given card with its neighbors who are not off the same player.
   * @param attacker the card to initiate battle.
   */
  private void battleWithNeighbors(ThreeTriosCard attacker) {
    // For every neighbor of the attacker...
    Map<ThreeTriosDirection, ThreeTriosCard> neighbors = grid.getNeighbors(attacker);
    for (ThreeTriosDirection direction : neighbors.keySet()) {
      ThreeTriosCard neighbor = neighbors.get(direction);

      // If the neighbor doesn't share a player
      // And the neighbor's touching attack value would lose...
      if (attacker.getPlayer() != neighbor.getPlayer() && comparisonStrategy.compare(
              attacker.getAttackValue(direction),
              neighbor.getAttackValue(direction.getOpposite())
      )) {

        // Flip the neighbor and combo off of it.
        neighbors.get(direction).changePlayer();
        flippedThisTurn.add(neighbors.get(direction));
        // Update the number of cards that have been flipped.
        numFlippedThisTurn += 1;
      }
    }
  }
}
