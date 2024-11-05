package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.ConfigurationReader;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosAttackValue;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

import java.util.List;

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
  @Before
  public void setUp() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Tall.txt"
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
    );

    model = new ThreeTriosGameModel(grid, deck, battleRules, false);
  }

  @Test
  public void testConfigurationError() {
    Assert.assertThrows(
        "Should throw an error for an invalid file",
        IllegalStateException.class, () -> ConfigurationReader.readGrid(
          "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Fail.txt"
      )
    );
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExc1() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Split.txt"
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
    );

    model = new ThreeTriosGameModel(grid, deck, battleRules);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExc2() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.PlayedToGrid.txt"
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
    );
    grid.playToCell(1, 1, deck.remove(0));
    model = new ThreeTriosGameModel(grid, deck, battleRules);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExc3() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid("Grid.Tall");
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = List.of(new Card(ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.TWO,
            ThreeTriosAttackValue.THREE,
            ThreeTriosAttackValue.FOUR,
            ThreeTriosPlayer.RED,
            "card"),
            new Card(ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.TWO,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosPlayer.RED,
                    "card"));

    model = new ThreeTriosGameModel(grid, deck, battleRules);
  }

  @Test
  public void testIsGameOverTrue() {
    this.setUp();
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 2);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 0);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 1, 1);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 2);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 2);

    Assert.assertTrue("Game should be over", model.isGameOver());
  }

  @Test
  public void testIsGameOverFalse() {
    this.setUp();

    assertFalse(model.isGameOver());
  }

  @Test
  public void testIsGameWonTrue() {
    this.setUp();
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 2);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 0);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 1, 1);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 2);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 2);

    Assert.assertTrue(model.isGameWon());
  }

  @Test
  public void testIsGameWonFalse() {
    this.setUp();
  
    assertFalse(model.isGameWon());
  }

  //todo test isGameWonTiedGame

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerExc() {
    this.setUp();

    model.getWinner();
  }

  @Test
  public void testGetWinner() {
    this.setUp();

    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 2);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 0);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 1, 1);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 2);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 2);

    assertEquals(ThreeTriosPlayer.BLUE, model.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToGridExc1() {
    this.setUp();

    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testPlayToGridExc2() {
    this.setUp();

    model.playToGrid(ThreeTriosPlayer.RED, 50, 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToGridExc3() {
    this.setUp();
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 0);
  }

  @Test
  public void testPlayToGrid() {
    this.setUp();
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    assertEquals(4, model.getHand(ThreeTriosPlayer.RED).size()); //hand size should go down
    assertEquals(1, model.getGrid().getNumCards()); 
  }

  @Test
  public void testGetGrid() {
    this.setUp();

    ThreeTriosGrid expectedGrid = ConfigurationReader.readGrid(
        "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Tall.txt"
    );

    assertEquals(expectedGrid.toString(), model.getGrid().toString());
  }

  @Test
  public void testGetCurrentPlayerInitial() {
    this.setUp();

    assertEquals(ThreeTriosPlayer.RED, model.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayerAfterOneMove() {
    this.setUp();

    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    assertEquals(ThreeTriosPlayer.BLUE, model.getCurrentPlayer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetHandExc() {
    this.setUp();

    model.getHand(null);
  }

  @Test
  public void testGetHand() {
    this.setUp();

    List<ThreeTriosCard> expectedHand = List.of(
            new Card(
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosPlayer.RED,
                    "Card1"),
            new Card(ThreeTriosAttackValue.TWO,
                    ThreeTriosAttackValue.TWO,
                    ThreeTriosAttackValue.TWO,
                    ThreeTriosAttackValue.TWO,
                    ThreeTriosPlayer.RED,
                    "Card2"),
            new Card(ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosPlayer.RED,
                    "Card3"),
            new Card(ThreeTriosAttackValue.FOUR,
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosPlayer.RED,
                    "Card4"),
            new Card(ThreeTriosAttackValue.FIVE,
                    ThreeTriosAttackValue.FIVE,
                    ThreeTriosAttackValue.FIVE,
                    ThreeTriosAttackValue.FIVE,
                    ThreeTriosPlayer.RED,
                    "Card5"));

    assertEquals(expectedHand.size(), model.getHand(ThreeTriosPlayer.RED).size());
  }

  // Test illegal mutation
  @Test
  public void testGridCannotBeMutated() {
    ThreeTriosCard ace = new Card(
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosPlayer.RED,
            "Ace");
    model.getGrid().playToCell(0, 0, ace);

    Assert.assertNotEquals(
            "getgrid() should not allow mutation!!!!)",
            model.getGrid().getCell(0, 0),
            ace
    );
  }

  @Test
  public void testGridCellCannotBeMutated() {
    ThreeTriosCard ace = new Card(
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosPlayer.RED,
            "Ace");
    model.getGrid().getCell(0, 0).setCard(ace);

    Assert.assertNotEquals(
            "getgrid() should not allow mutation!!!!)",
            model.getGrid().getCell(0, 0),
            ace
    );
  }

  @Test
  public void testGridCardCannotBeMutated() {
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.getGrid().getCell(0, 0).getCard().changePlayer();

    Assert.assertEquals(
            "getgrid() should not allow mutation!!!!)",
            ThreeTriosPlayer.RED,
            model.getGrid().getCell(0, 0).getCard().getPlayer()
    );
  }

  @Test
  public void testHandCannotBeMutated() {
    model.getHand(ThreeTriosPlayer.RED).clear();

    Assert.assertFalse(model.getHand(ThreeTriosPlayer.RED).isEmpty());
  }

  @Test
  public void testHandCardCannotBeMutated() {
    model.getHand(ThreeTriosPlayer.RED).get(0).changePlayer();

    Assert.assertEquals(
            "The hand should not allow mutation!",
            ThreeTriosPlayer.RED,
            model.getHand(ThreeTriosPlayer.RED).get(0).getPlayer()
    );
  }
  
}
