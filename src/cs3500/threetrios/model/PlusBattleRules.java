package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@link ThreeTriosBattleRules} that behaves like {@link SimpleRules} except that,
 * If at least two cards adjacent to the originally placed card have the same sum with said card in
 * their respective opposing directions, then any of those neighboring cards that belong to the
 * opponent are flipped.
 *
 * <p>This change only applies on the initial card played.
 * When newly flipped cards do battle, this rule does not apply.
 */
public class PlusBattleRules implements ThreeTriosBattleRules {
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
   *  <p>On the initial placement of a card, the rules are slightly different: If at least two cards
   *  adjacent to the originally placed card have the same sum with said card in their respective
   *  opposing directions, then any of those neighboring cards that belong to the opponent are
   *  flipped. This does not apply to the combo step.</p>
   *
   * @param comparisonStrategy The strategy to compare two attack values.
   */
  public PlusBattleRules(ThreeTriosBattleComparison comparisonStrategy) {
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

    // Handel the initial variant rules
    handelFirstStep(cardPlayed);


    // Handle the combo step
    flippedThisTurn.add(cardPlayed);

    while (!flippedThisTurn.isEmpty()) {
      ThreeTriosCard attacker = flippedThisTurn.remove(0);
      battleWithNeighbors(attacker);
    }

    return numFlippedThisTurn;
  }

  /**
   * A helper method for battle that handles the battle from the initial placement of the card.
   * This is not called on combo steps.
   *
   * <p>The rules are specifically: If at least two cards adjacent to the originally placed card
   * have the same sum with said card in their respective opposing directions, then any of those
   * neighboring cards that belong to the opponent are flipped.
   *
   * @param cardPlayed The card played this turn.
   */
  private void handelFirstStep(ThreeTriosCard cardPlayed) {
    Map<Integer, List<ThreeTriosCard>> cardsOfSameValue = new HashMap<>();

    // For every neighbor of the cardPlayed...
    Map<ThreeTriosDirection, ThreeTriosCard> neighbors = grid.getNeighbors(cardPlayed);
    for (ThreeTriosDirection direction : neighbors.keySet()) {
      ThreeTriosCard neighbor = neighbors.get(direction);
      int cardNeighborSum = cardPlayed.getAttackValue(direction).getValue()
              + neighbor.getAttackValue(direction.getOpposite()).getValue();

      // If the sum is not already in the map of sum values, create a list for it.
      if (!cardsOfSameValue.containsKey(cardNeighborSum)) {
        cardsOfSameValue.put(cardNeighborSum, new ArrayList<>());
      }

      // Place the card in its corresponding list.
      cardsOfSameValue.get(cardNeighborSum).add(neighbor);
    }

    // For every sum found:
    for (List<ThreeTriosCard> cardsOfCommonSum : cardsOfSameValue.values()) {

      // If at least two cards have the same sum...
      if (cardsOfCommonSum.size() >= 2) {
        // Flip all same-valued cards (that don't belong to the current player).
        for (ThreeTriosCard card : cardsOfCommonSum) {
          if (card.getPlayer() != cardPlayed.getPlayer()) {
            card.changePlayer();
            flippedThisTurn.add(card);
            ++numFlippedThisTurn;
          }
        }
      }
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
      // And the neighbor's touching attack value would lose...
      if (attacker.getPlayer() != neighbor.getPlayer() && comparisonStrategy.compare(
              attacker.getAttackValue(direction),
              neighbor.getAttackValue(direction.getOpposite())
      )) {

        // Flip the neighbor and combo off of it.
        neighbors.get(direction).changePlayer();
        flippedThisTurn.add(neighbors.get(direction));
        // Update the number of cards that have been flipped.
        ++numFlippedThisTurn;
      }
    }
  }
}
