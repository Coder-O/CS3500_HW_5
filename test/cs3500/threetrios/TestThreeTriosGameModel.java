package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.controller.ConfigurationReader;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosAttackValue;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for the Model.
 */
public class TestThreeTriosGameModel {
  private ThreeTriosGameModel model;
  private List<ThreeTriosCard> deck;

  private final String PATH_GRID_3X3 = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3.txt";
  private final String PATH_GRID_SPLIT = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Split.txt";
  private final String PATH_DECK_10 = "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt";
  private final String PATH_DECK_38 = "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt";

  /**
   * Set up an example game.
   */
  @Before
  public void setUp() {
    setUp(PATH_GRID_3X3, PATH_DECK_10);
  }

  private void setUp(String gridPath, String deckPath) {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            gridPath
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    deck = ConfigurationReader.readDeck(
            deckPath
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
    // Deck to small for grid
    setUp(PATH_GRID_SPLIT, PATH_DECK_10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExc2() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            PATH_GRID_3X3
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
        PATH_DECK_10
    );
    grid.playToCell(1, 1, deck.remove(0));
    model = new ThreeTriosGameModel(grid, deck, battleRules);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExc3() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(PATH_GRID_3X3);
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
  public void testShuffle() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            PATH_GRID_3X3
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    deck = ConfigurationReader.readDeck(
            PATH_DECK_10
    );

    model = new ThreeTriosGameModel(grid, deck, battleRules, true, new Random(22));

    Assert.assertNotEquals(
            "The deck should have been shuffled!",
            deck.get(0),
            model.getHand(ThreeTriosPlayer.RED).get(0)
    );
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
        PATH_GRID_3X3
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
            "getGrid() should not allow mutation!!!!)",
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
            "getGrid() should not allow mutation!!!!)",
            model.getGrid().getCell(0, 0),
            ace
    );
  }

  @Test
  public void testGridCardCannotBeMutated() {
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.getGrid().getCell(0, 0).getCard().changePlayer();

    Assert.assertEquals(
            "getGrid() should not allow mutation!!!!)",
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

  // Testing playToGrid()

  @Test
  public void testPlayToGridLegal() {
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
    model.playToGrid(ThreeTriosPlayer.RED, 3, 2, 2);
    model.playToGrid(ThreeTriosPlayer.BLUE, 2, 1, 1);

    Assert.assertEquals(
            deck.get(0),
            model.getGrid().getCell(0,0).getCard()
    );

    Assert.assertEquals(
            deck.get(0+5), // Index 0 in Blues' hand, which starts 5 cards from the start.
            model.getGrid().getCell(0, 1).getCard()
    );

    Assert.assertEquals(
            deck.get(4),
            model.getGrid().getCell(2,2).getCard()
    );

    Assert.assertEquals(
            deck.get(3+5), // Index 3 in Blues' hand, which starts 5 cards from the start.
            model.getGrid().getCell(1, 1).getCard()
    );
  }

  // NOTE: This test assumes that a card with large attack values will flip a card with low attack
  // values, which might not be true depending upon the BattleRules implementation.
  @Test
  public void testPlayToGridFlipsCards() {
    model.playToGrid(ThreeTriosPlayer.RED, 0,0, 0);
    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);

    Assert.assertEquals(
            "The red card should have flipped",
            model.getGrid().getCell(0,0).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }

  @Test
  public void testPlayToHoleException() {
    setUp(PATH_GRID_SPLIT, PATH_DECK_38);

    Assert.assertThrows(
            "Playing to a hole should not be allowed!!!",
            IllegalStateException.class,
        () -> model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 3)
    );
  }

  @Test
  public void testPlayToFilledSpaceException() {
    setUp(PATH_GRID_SPLIT, PATH_DECK_38);

    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    Assert.assertThrows(
            "Playing to a cell twice should not be allowed!!!",
            IllegalStateException.class,
            () -> model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 0)
    );
  }

  @Test
  public void testPlayTwiceException() {
    setUp(PATH_GRID_SPLIT, PATH_DECK_38);

    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    Assert.assertThrows(
            "Playing twice in a row should not be allowed!!!",
            IllegalStateException.class,
            () -> model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 1)
    );
  }
}
