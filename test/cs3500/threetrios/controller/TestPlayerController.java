package cs3500.threetrios.controller;

import org.junit.Test;

import java.util.List;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.SimpleBattleComparison;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.view.ThreeTriosGUIView;
import cs3500.threetrios.view.ThreeTriosGameGUIView;

/**
 * Test the full controller of the game. (HW3).
 */
public class TestPlayerController {

  public ReadOnlyThreeTriosGameModel readOnlyModel;

  public ThreeTriosGameModel model;
  public ViewFeatures features;
  public ThreeTriosGUIView view;
  public Player player;

  public PlayerController controller;

  /**
   * Set up an example game.
   */
  protected void setUp() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3.txt"
    );
    ThreeTriosBattleRules battleRules = new SimpleRules(new SimpleBattleComparison());
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
    );

    readOnlyModel = new ReadOnlyThreeTriosGameModel(grid, deck, battleRules, false);
    model = new ThreeTriosGameModel(grid, deck, battleRules, false);
    view = new ThreeTriosGameGUIView(readOnlyModel, ThreeTriosPlayer.RED);
    features = new ViewFeaturesImpl(model, view);
  }

  /**
   * Set up an example game.
   */
  protected void setUpWithHoles() {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3H.txt"
    );
    ThreeTriosBattleRules battleRules = new SimpleRules(new SimpleBattleComparison());
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt"
    );

    readOnlyModel = new ReadOnlyThreeTriosGameModel(grid, deck, battleRules, false);
    model = new ThreeTriosGameModel(grid, deck, battleRules, false);
    view = new ThreeTriosGameGUIView(readOnlyModel, ThreeTriosPlayer.RED);
    features = new ViewFeaturesImpl(model, view);
  }

  //test null model
  @Test(expected = NullPointerException.class)
  public void testNullModel() {
    this.setUp();
    controller = new PlayerControllerImpl(null, features, player, ThreeTriosPlayer.RED);
  }

  //test null player color
  @Test(expected = NullPointerException.class)
  public void testNullPlayerColor() {
    this.setUp();
    controller =  new PlayerControllerImpl(model, features, player, null);
  }

  //test null features
  @Test(expected = NullPointerException.class)
  public void testNullView() {
    this.setUp();
    controller = new PlayerControllerImpl(model, null, player, ThreeTriosPlayer.RED);
  }

  //test handle card selection when it's not the players turn
  //Cannot test a view but correct message is being displayed

  //test handle card selection when the selected card does not belong to the player
  //Cannot test a view but correct message is being displayed

  //test handlegridselection when a cell is clicked before choosing a card
  //Cannot test a view but correct message is being displayed

  //test handlegridselection when a hole is clicked
  //Cannot test a view but correct message is being displayed

  //test handlegridselection when a cell with a card is clicked
  //Cannot test a view but correct message is being displayed

}
