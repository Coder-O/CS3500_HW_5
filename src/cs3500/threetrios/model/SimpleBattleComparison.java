package cs3500.threetrios.model;

/**
 * A simple {@link ThreeTriosBattleComparison}.
 * The attacker wins if the attacker's attack value is greater than the defender's.
 */
public class SimpleBattleComparison implements ThreeTriosBattleComparison {
  /**
   * Compares two attack values.
   *
   * @param attacker The attack value of the attacker.
   * @param defender The attack value of the defender.
   * @return True if the attacker wins, false otherwise.
   */
  @Override
  public boolean compare(ThreeTriosAttackValue attacker, ThreeTriosAttackValue defender) {
    return attacker.getValue() > defender.getValue();
  }
}
