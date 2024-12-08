package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * A testing class for implementations of {@link ThreeTriosBattleComparison}.
 */
public class TestBattleComparison {
  private ThreeTriosBattleComparison simpleComparison;
  private ThreeTriosBattleComparison reverseComparison;
  private ThreeTriosBattleComparison fallenAceComparison;
  // reverseFallenComparison and fallenReverseComparison should behave the same.
  private ThreeTriosBattleComparison reverseFallenComparison;
  private ThreeTriosBattleComparison fallenReverseComparison;

  @Before
  public void initCommonFields() {
    simpleComparison = new SimpleBattleComparison();
    reverseComparison = new ReverseBattleComparison(new SimpleBattleComparison());
    fallenAceComparison = new FallenAceBattleComparison(new SimpleBattleComparison());
    reverseFallenComparison = new ReverseBattleComparison(
            new FallenAceBattleComparison(new SimpleBattleComparison())
    );
    fallenReverseComparison = new FallenAceBattleComparison(
            new ReverseBattleComparison(new SimpleBattleComparison())
    );
  }

  @Test
  public void testSimpleAttackerGreaterThanDefender() {
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.TWO)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.THREE)
    );


    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.NINE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.SEVEN)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.SIX, ThreeTriosAttackValue.FIVE)
    );

    Assert.assertTrue(
            "The attacker should win with a higher value!",
            simpleComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.ONE)
    );
  }

  @Test
  public void testReverseAttackerGreaterThanDefender() {
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.ONE)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.ONE)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.ONE)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.TWO)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.THREE)
    );


    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.NINE)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.SEVEN)
    );
    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.SIX, ThreeTriosAttackValue.FIVE)
    );

    Assert.assertFalse(
            "The attacker should loose with a higher value!",
            reverseComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.ONE)
    );
  }

  @Test
  public void testSimpleAttackerLessThanDefender() {
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.THREE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.THREE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.FOUR)
    );


    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.A)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.SEVEN, ThreeTriosAttackValue.NINE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.FIVE, ThreeTriosAttackValue.SIX)
    );

    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            simpleComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.A)
    );
  }

  @Test
  public void testReverseAttackerLessThanDefender() {
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.THREE)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.THREE)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.FOUR)
    );


    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.A)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.SEVEN, ThreeTriosAttackValue.NINE)
    );
    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.FIVE, ThreeTriosAttackValue.SIX)
    );

    Assert.assertTrue(
            "The attacker should win with a lower value!",
            reverseComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.A)
    );
  }

  @Test
  public void testSimpleValuesEqual() {
    for (ThreeTriosAttackValue value : ThreeTriosAttackValue.values()) {
      Assert.assertFalse(
              "The attacker should not win in a tie!",
              simpleComparison.compare(value, value)
      );
    }
  }

  @Test
  public void testReverseValuesEqual() {
    for (ThreeTriosAttackValue value : ThreeTriosAttackValue.values()) {
      Assert.assertFalse(
              "The attacker should not win in a tie!",
              reverseComparison.compare(value, value)
      );
    }
  }

  @Test
  public void testFallenValuesEqual() {
    for (ThreeTriosAttackValue value : ThreeTriosAttackValue.values()) {
      Assert.assertFalse(
              "The attacker should not win in a tie!",
              fallenAceComparison.compare(value, value)
      );
    }
  }

  @Test
  public void testRevFallValuesEqual() {
    for (ThreeTriosAttackValue value : ThreeTriosAttackValue.values()) {
      Assert.assertFalse(
              "The attacker should not win in a tie!",
              reverseFallenComparison.compare(value, value)
      );
    }
  }

  @Test
  public void testFallRevValuesEqual() {
    for (ThreeTriosAttackValue value : ThreeTriosAttackValue.values()) {
      Assert.assertFalse(
              "The attacker should not win in a tie!",
              fallenReverseComparison.compare(value, value)
      );
    }
  }

  @Test
  public void testFallenAceAndOne() {
    Assert.assertFalse(
            "FallenAce should let a one beat an ace!",
            fallenAceComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.ONE)
    );

    Assert.assertTrue(
            "FallenAce should let a one beat an ace!",
            fallenAceComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.A)
    );
  }

  @Test
  public void testFallenAceConsistent() {
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.ONE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.TWO)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.FOUR, ThreeTriosAttackValue.THREE)
    );


    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.NINE)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.SEVEN)
    );
    Assert.assertTrue(
            "The attacker should win with a higher value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.SIX, ThreeTriosAttackValue.FIVE)
    );

    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.TWO)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.THREE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.THREE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.FOUR)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.THREE, ThreeTriosAttackValue.FOUR)
    );


    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.NINE, ThreeTriosAttackValue.A)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.SEVEN, ThreeTriosAttackValue.NINE)
    );
    Assert.assertFalse(
            "The attacker should loose with a lower value!",
            fallenAceComparison.compare(ThreeTriosAttackValue.FIVE, ThreeTriosAttackValue.SIX)
    );
  }

  @Test
  public void testFullComposition() {
    for (ThreeTriosBattleComparison comparison
            : List.of(fallenReverseComparison, reverseFallenComparison)
    ) {
      Assert.assertTrue(comparison.compare(ThreeTriosAttackValue.A, ThreeTriosAttackValue.ONE));
      Assert.assertTrue(comparison.compare(ThreeTriosAttackValue.TWO, ThreeTriosAttackValue.THREE));

      Assert.assertFalse(comparison.compare(ThreeTriosAttackValue.ONE, ThreeTriosAttackValue.A));
      Assert.assertFalse(comparison.compare(ThreeTriosAttackValue.SIX, ThreeTriosAttackValue.FIVE));
    }
  }
}
