package cs3500.threetrios.model;

/**
 * An interface for a comparison strategy for comparing two {@link ThreeTriosAttackValue}.
 */
public interface ThreeTriosBattleComparison {
  /**
   * Compares two attack values.
   * @param attacker The attack value of the attacker.
   * @param defender The attack value of the defender.
   * @return True if the attacker wins, false otherwise.
   */
  public boolean compare(ThreeTriosAttackValue attacker, ThreeTriosAttackValue defender);
}
