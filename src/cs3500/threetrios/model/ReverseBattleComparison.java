package cs3500.threetrios.model;

/**
 * A decorator for a {@link ThreeTriosBattleComparison}
 * that swaps the attacking and defending attack values.
 * So, the decorated relationship is reversed (but not inverted).
 */
public class ReverseBattleComparison implements ThreeTriosBattleComparison {
  private final ThreeTriosBattleComparison comparison;

  /**
   * Constructs decorator for a {@link ThreeTriosBattleComparison}
   * that swaps the attacking and defending attack values.
   * So, the decorated relationship is reversed (but not inverted).
   * @param comparison The {@link ThreeTriosBattleComparison} to decorate.
   */
  public ReverseBattleComparison(ThreeTriosBattleComparison comparison) {
    this.comparison = comparison;
  }

  /**
   * Compares two attack values.
   *
   * @param attacker The attack value of the attacker.
   * @param defender The attack value of the defender.
   * @return True if the attacker wins, false otherwise.
   */
  @Override
  public boolean compare(ThreeTriosAttackValue attacker, ThreeTriosAttackValue defender) {
    return comparison.compare(defender, attacker);
  }
}
