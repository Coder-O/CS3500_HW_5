package cs3500.ThreeTrios;

import org.junit.Test;

import cs3500.ThreeTrios.model.ConfigurationReader;
import cs3500.ThreeTrios.model.SimpleRules;
import cs3500.ThreeTrios.model.ThreeTriosBattleRules;
import cs3500.ThreeTrios.model.ThreeTriosCard;
import cs3500.ThreeTrios.model.ThreeTriosGameModel;
import cs3500.ThreeTrios.model.ThreeTriosGrid;
import cs3500.ThreeTrios.model.ThreeTriosModel;
import cs3500.ThreeTrios.model.ThreeTriosPlayer;
import cs3500.ThreeTrios.view.ThreeTriosView;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class TestView {

    private ThreeTriosGameModel model;

    /**
     * Set up an example game.
     */
    protected void setUp() {
        ThreeTriosGrid grid = ConfigurationReader.readGrid("Grid.Tall");
        ThreeTriosBattleRules battleRules = new SimpleRules();
        List<ThreeTriosCard> deck = ConfigurationReader.readDeck("Card.10Cards");

        ThreeTriosModel model = new ThreeTriosGameModel(grid, deck, battleRules);
    }

    //todo test starting game
    @Test
    public void testStartingView() {

        ThreeTriosView view = new ThreeTriosView(model);

        String expectedView = "Player: RED\n" 
        + "_ _ _"
        + "_ _ _" 
        + "_ _ _"
        + "Hand:\n" 
        + "Player: RED\n" 
        + "Player: RED\n";

        assertEquals(expectedView, view.toString());
    }

    //todo test 1 play
    @Test
    public void testView() {

        ThreeTriosView view = new ThreeTriosView(model);
        model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);

        String expectedView = "Player: BLUE\n" 
        + "_ _ _"
        + "_ _ _" 
        + "_ _ _"
        + "Hand:\n" 
        + "Player: RED\n" 
        + "Player: RED\n";



        assertEquals(expectedView, view.toString());
    }


}
