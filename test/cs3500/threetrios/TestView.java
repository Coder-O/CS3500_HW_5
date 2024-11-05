package cs3500.threetrios;

import org.junit.Test;

import cs3500.threetrios.model.ConfigurationReader;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.view.ThreeTriosView;

import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * Tests the textual view of the game.
 */
public class TestView {

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

    model = new ThreeTriosGameModel(grid, deck, battleRules, false);
  }

  @Test
  public void testStartingView() {

    this.setUp();

    ThreeTriosView view = new ThreeTriosView(model);
    
    String expectedView = "Player: RED\n"
            + "_ _ _\n"
            + "_ _ _\n"
            + "_ _ _\n"
            + "Hand:\n"
            + "Card1 1 1 1 1\n"
            + "Card2 2 2 2 2\n"
            + "Card3 3 3 3 3\n"
            + "Card4 4 4 4 4\n"
            + "Card5 5 5 5 5\n";

    assertEquals(expectedView, view.toString());
  }

  @Test
  public void testView1Play() {

    this.setUp();

    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

    ThreeTriosView view = new ThreeTriosView(model);

    String expectedView = "Player: BLUE\n"
            + "R _ _\n"
            + "_ _ _\n"
            + "_ _ _\n"
            + "Hand:\n"
            + "Card6 6 6 6 6\n"
            + "Card7 7 7 7 7\n"
            + "Card8 8 8 8 8\n"
            + "Card9 9 9 9 9\n"
            + "CardA A A A A\n";

    assertEquals(expectedView, view.toString());
  }

  //todo
//  @Test
//  public void testView2Play() {
//
//    this.setUp();
//
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 2);
//
//    ThreeTriosView view = new ThreeTriosView(model);
//
//    String expectedView = "Player: RED\n"
//            + "R _ B\n"
//            + "_ _ _\n"
//            + "_ _ _\n"
//            + "Hand:\n"
//            + "Card2 2 2 2 2\n"
//            + "Card3 3 3 3 3\n"
//            + "Card4 4 4 4 4\n"
//            + "Card5 5 5 5 5\n";
//
//    assertEquals(expectedView, view.toString());
//  }

}
