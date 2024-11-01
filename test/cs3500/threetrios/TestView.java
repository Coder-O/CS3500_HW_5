package cs3500.threetrios;

import org.junit.Test;

import cs3500.threetrios.model.ConfigurationReader;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosModel;
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

}
