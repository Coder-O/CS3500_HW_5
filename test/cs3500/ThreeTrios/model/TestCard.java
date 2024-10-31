package cs3500.ThreeTrios.model;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

/**
 * Test Card behaviors.
 */
public class TestCard {
    
    @Test
    public void testGetNorth() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        assertEquals(ThreeTriosAttackValue.ONE, card.getNorth());
    }

    @Test
    public void testGetEast() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        assertEquals(ThreeTriosAttackValue.TWO, card.getEast());
    }

    @Test
    public void testGetWest() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        assertEquals(ThreeTriosAttackValue.THREE, card.getWest());
    }

    @Test
    public void testGetSouth() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        assertEquals(ThreeTriosAttackValue.FOUR, card.getSouth());
    }

    @Test
    public void testGetPlayer() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");
        
        assertEquals(ThreeTriosPlayer.ONE, card.getPlayer());
    }

    @Test
    public void testGetName() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");
        
        assertEquals("card", card.getName());
    }

    @Test
    public void testChangePlayer() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        card.changePlayer();

        assertEquals(ThreeTriosPlayer.TWO, card.getPlayer());
    }

    @Test
    public void testGetAttackValue() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
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
        ThreeTriosPlayer.ONE, 
        "card");

        assertEquals("card 1 2 3 4", card.toString());
    }

    @Test
    public void testEquals() {
        ThreeTriosCard card = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        ThreeTriosCard card1 = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card");

        ThreeTriosCard card2 = new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.ONE, 
        "card2");
        
        Assert.assertTrue(card.equals(card1));
        Assert.assertFalse(card.equals(card2));
    }
}
