package cs3500.ThreeTrios.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A set of rules for when cards do battle in a game of Three Trios.
 * By these rules, an attacker wins a battle if it's attack value in the direction of its opponent is greater than
 *  that of its opponent's in its direction. Otherwise, the defender wins.
 *
 *  <p>If the attacker wins, then the defender is flipped.</p>
 */
public class SimpleRules implements ThreeTriosBattleRules{

  private List<ThreeTriosCard> flippedThisTurn;
  private ThreeTriosGrid grid;

  SimpleRules() {
    flippedThisTurn = new ArrayList<>();
  }

  /**
   * Handles the battling and flipping of cards after a card has been played.
   * Details of interactions vary depending upon the implementation.
   *
   * @param cardPlayed The card that was played this turn.
   * @param grid       The grid the card is in.
   */
  @Override
  public void battle(ThreeTriosCard cardPlayed, ThreeTriosGrid grid) {
    flippedThisTurn = new ArrayList<>();
    this.grid = grid;

    flippedThisTurn.add(cardPlayed);

    ThreeTriosCard attacker;

    while (!flippedThisTurn.isEmpty()) {
      attacker = flippedThisTurn.remove(0);
      battleWithNeighbors(attacker);
    }
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
      // And the neighbor's touching attack value is less...
      if (attacker.getPlayer() != neighbor.getPlayer()
              && (attacker.getAttackValue(direction).getValue()
              > neighbor.getAttackValue(direction.getOpposite()).getValue())
      ) {

        // Flip the neighbor and combo off of it.
        neighbors.get(direction).changePlayer();
        flippedThisTurn.add(neighbors.get(direction));
      }
    }
  }
}
