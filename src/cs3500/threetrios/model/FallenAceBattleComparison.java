package cs3500.threetrios.model;

/**
 * A decorator for a {@link ThreeTriosBattleComparison}
 * that allows ones to beat Aces.
 * This is achieved by switching the values of Aces and Ones.
 */
public class FallenAceBattleComparison implements ThreeTriosBattleComparison {
  private final ThreeTriosBattleComparison comparison;

  /**
   * Creates decorator for a {@link ThreeTriosBattleComparison}
   * that allows ones to beat Aces.
   * This is achieved by switching the values of Aces and Ones.
   * @param comparison The comparison to decorate.
   */
  public FallenAceBattleComparison(ThreeTriosBattleComparison comparison) {
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

    // If the provided values are 1 and A or A and 1
    if (
            (attacker == ThreeTriosAttackValue.A && defender == ThreeTriosAttackValue.ONE)
                    ||
                    (attacker == ThreeTriosAttackValue.ONE && defender == ThreeTriosAttackValue.A)
    ) {
      return  comparison.compare(defender, attacker);
    }
    return comparison.compare(attacker,defender);
  }
}
