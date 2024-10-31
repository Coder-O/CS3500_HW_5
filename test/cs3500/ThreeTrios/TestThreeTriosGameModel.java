package cs3500.ThreeTrios;

import org.junit.Test;

import cs3500.ThreeTrios.model.Card;
import cs3500.ThreeTrios.model.ConfigurationReader;
import cs3500.ThreeTrios.model.SimpleRules;
import cs3500.ThreeTrios.model.ThreeTriosAttackValue;
import cs3500.ThreeTrios.model.ThreeTriosBattleRules;
import cs3500.ThreeTrios.model.ThreeTriosCard;
import cs3500.ThreeTrios.model.ThreeTriosGameModel;
import cs3500.ThreeTrios.model.ThreeTriosGrid;
import cs3500.ThreeTrios.model.ThreeTriosModel;
import cs3500.ThreeTrios.model.ThreeTriosPlayer;

import org.junit.Assert;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for the Model.
 */
public class TestThreeTriosGameModel {

    private ThreeTriosGameModel model;

    /**
     * Set up an example game.
     */
    protected void setUp() {
        ThreeTriosGrid grid = ConfigurationReader.readGrid(
                "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Tall.txt"
        );
        ThreeTriosBattleRules battleRules = new SimpleRules();
        List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
                "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
        );

        ThreeTriosModel model = new ThreeTriosGameModel(grid, deck, battleRules);
    }

    @Test
    public void testConfigurationError() {
        //todo
    }
    
    // @Test(expected = IllegalArgumentException.class)
    // public void testConstructorExc1() {
    //     //todo
    // }

    // @Test(expected = IllegalArgumentException.class)
    // public void testConstructorExc2() {
    //     //todo
    // }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExc3() {
        ThreeTriosGrid grid = ConfigurationReader.readGrid("Grid.Tall");
        ThreeTriosBattleRules battleRules = new SimpleRules();
        List<ThreeTriosCard> deck = List.of(new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.RED, 
        "card"), new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosPlayer.RED, 
        "card"));

        ThreeTriosModel model = new ThreeTriosGameModel(grid, deck, battleRules);
    }

    @Test
    public void testIsGameOverTrue() {
        //todo
    }

    @Test
    public void testIsGameOverFalse() {
        this.setUp();

        assertFalse(model.isGameOver());
    }

    @Test
    public void testIsGameWonTrue() {
        //todo
    }

    @Test
    public void testIsGameWonFalse() {
        //todo
    }

    @Test
    public void testIsGameWonOngoingGame() {
        this.setUp();
    
        assertFalse(model.isGameWon());
    }

    // @Test(expected = IllegalStateException.class)
    // public void testPlayToGridExc1() {
    //     //todo
    // }

    // @Test(expected = IllegalArgumentException.class)
    // public void testPlayToGridExc2() {
    //     //todo
    // }

    // @Test(expected = IllegalArgumentException.class)
    // public void testPlayToGridExc3() {
    //     //todo
    // }

    @Test
    public void testPlayToGrid() {
        //todo
    }

    @Test
    public void testGetDeck() {
        this.setUp();

        List<ThreeTriosCard> expectedDeck = List.of(new Card(ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.ONE, 
        ThreeTriosAttackValue.ONE, 
        null, 
        "Card1"), 
        new Card(ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.TWO, 
        ThreeTriosAttackValue.TWO, 
        null, 
        "Card2"), 
        new Card(ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.THREE, 
        ThreeTriosAttackValue.THREE, 
        null, 
        "Card3"), 
        new Card(ThreeTriosAttackValue.FOUR, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosAttackValue.FOUR, 
        ThreeTriosAttackValue.FOUR, 
        null, 
        "Card4"), 
        new Card(ThreeTriosAttackValue.FIVE, 
        ThreeTriosAttackValue.FIVE, 
        ThreeTriosAttackValue.FIVE, 
        ThreeTriosAttackValue.FIVE, 
        null, 
        "Card5"), 
        new Card(ThreeTriosAttackValue.SIX, 
        ThreeTriosAttackValue.SIX, 
        ThreeTriosAttackValue.SIX, 
        ThreeTriosAttackValue.SIX, 
        null, 
        "Card6"), 
        new Card(ThreeTriosAttackValue.SEVEN, 
        ThreeTriosAttackValue.SEVEN, 
        ThreeTriosAttackValue.SEVEN, 
        ThreeTriosAttackValue.SEVEN, 
        null, 
        "Card7"), 
        new Card(ThreeTriosAttackValue.EIGHT, 
        ThreeTriosAttackValue.EIGHT, 
        ThreeTriosAttackValue.EIGHT, 
        ThreeTriosAttackValue.EIGHT, 
        null, 
        "Card8"), 
        new Card(ThreeTriosAttackValue.NINE, 
        ThreeTriosAttackValue.NINE, 
        ThreeTriosAttackValue.NINE, 
        ThreeTriosAttackValue.NINE, 
        null, 
        "Card9"), 
        new Card(ThreeTriosAttackValue.A, 
        ThreeTriosAttackValue.A, 
        ThreeTriosAttackValue.A, 
        ThreeTriosAttackValue.A, 
        null, 
        "CardA"));

        assertEquals(expectedDeck, model.getDeck());
    }

    @Test
    public void testGetGrid() {
        //todo
    }

    @Test
    public void testGetCurrentPlayerInitial() {
        //todo
    }

    @Test
    public void testGetCurrentPlayerAfterOneMove() {
        //todo
    }

    // @Test(expected = IllegalArgumentException.class)
    // public void testGetHandExc() {
    //     //todo
    // }

    @Test
    public void testGetHand() {
        //todo
    }

    @Test
    public void testEndTurn() {
        //todo
    }

    
}
