package cs3500.threetrios.controller;

import org.junit.Test;

import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosAttackValue;
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
    ThreeTriosBattleRules battleRules = new SimpleRules();
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
    ThreeTriosBattleRules battleRules = new SimpleRules();
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
  @Test
  public void testNotYourTurn() {
    this.setUp();
    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
    controller.handleCardSelection(0, ThreeTriosPlayer.BLUE);
  }

  //test handle card selection when the selected card does not belong to the player
  @Test
  public void testNotYourCard() {
    this.setUp();

    ThreeTriosCard cardInBlueHand = new Card(ThreeTriosAttackValue.SIX,
            ThreeTriosAttackValue.SIX,
            ThreeTriosAttackValue.SIX,
            ThreeTriosAttackValue.SIX,
            ThreeTriosPlayer.BLUE,
            "Card6");

    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
    controller.handleCardSelection(model.getHand(ThreeTriosPlayer.BLUE).indexOf(cardInBlueHand),
            ThreeTriosPlayer.BLUE);
  }

  //test handlegridselection when a cell is clicked before choosing a card
  @Test(expected = NullPointerException.class)
  public void testCellClickedBeforeCard() {
    this.setUp();
    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
    controller.handleGridCellSelection(0, 0);
  }

  //todo test handlegridselection when a hole is clicked
//  @Test(expected = IllegalStateException.class)
//  public void testHoleClicked() {
//    this.setUpWithHoles();
//    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
//    controller.handleCardSelection(0, ThreeTriosPlayer.RED);
//    controller.handleGridCellSelection(1, 1);
//  }

  //test handlegridselection when a cell with a card is clicked
  @Test(expected = NullPointerException.class)
  public void testCellWithCardClicked() {
    this.setUp();
    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    controller.handleCardSelection(0, ThreeTriosPlayer.BLUE);
    controller.handleGridCellSelection(0, 0);
  }

  //todo test that the players turn changes after a play has been made
//  @Test
//  public void testTurnChanges() {
//    this.setUp();
//    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
//    assertEquals(ThreeTriosPlayer.BLUE, model.getCurrentPlayer());
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 2);
//    assertEquals(ThreeTriosPlayer.RED, model.getCurrentPlayer());
//  }

  //todo test a game is won
//  @Test
//  public void testIsGameWon() {
//    this.setUp();
//
//    controller = new PlayerControllerImpl(model, features, player, ThreeTriosPlayer.RED);
//
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 0, 2);
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 0);
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 1, 1);
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 2);
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 0);
//    model.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 1);
//    model.playToGrid(ThreeTriosPlayer.RED, 0, 2, 2);
//
//    assertEquals(ThreeTriosPlayer.BLUE, model.getWinner());
//  }

  //todo test a game is tied

}
