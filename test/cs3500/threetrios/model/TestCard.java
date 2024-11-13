package cs3500.threetrios.model;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

/**
 * Test Card behaviors.
 */
public class TestCard {

  @Test
  public void testGetPlayer() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");
    
    assertEquals(ThreeTriosPlayer.RED, card.getPlayer());
  }

  @Test
  public void testGetName() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");
    
    assertEquals("card", card.getName());
  }

  @Test
  public void testChangePlayer() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");

    card.changePlayer();

    assertEquals(ThreeTriosPlayer.BLUE, card.getPlayer());
  }

  @Test
  public void testGetAttackValue() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");

    assertEquals(ThreeTriosAttackValue.ONE, card.getAttackValue(ThreeTriosDirection.NORTH));
    assertEquals(ThreeTriosAttackValue.TWO, card.getAttackValue(ThreeTriosDirection.EAST));
    assertEquals(ThreeTriosAttackValue.THREE, card.getAttackValue(ThreeTriosDirection.WEST));
    assertEquals(ThreeTriosAttackValue.FOUR, card.getAttackValue(ThreeTriosDirection.SOUTH));
  }

  @Test
  public void testToString() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");

    assertEquals("card 1 2 3 4", card.toString());
  }

  @Test
  public void testEquals() {
    ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");

    ThreeTriosCard card1 = new Card(ThreeTriosAttackValue.ONE, 
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card");

    ThreeTriosCard card2 = new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card2");

    Assert.assertTrue(card.equals(card1));
    Assert.assertFalse(card.equals(card2));
  }
}
