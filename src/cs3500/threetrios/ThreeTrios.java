package cs3500.threetrios;

import java.util.List;

import cs3500.threetrios.controller.ConfigurationReader;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.view.ThreeTriosGUIView;
import cs3500.threetrios.view.ThreeTriosGameGUIView;

/**
 * Instantiates a model, instantiates a view
 * using that model, and tells the view to get started.
 */
public final class ThreeTrios {

  /**
   * Instantiates a model, instantiates a view
   * using that model, and tells the view to get started.
   */
  public static void main(String[] args) {

    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3H.txt");
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt");

    ThreeTriosGameModel model = new ThreeTriosGameModel(grid, deck, battleRules, false);
    ThreeTriosGUIView view = new ThreeTriosGameGUIView(model);
    view.makeVisible();
  }

  //todo
  /**
   * Instantiates a model, view, player models, and player controllers.
   * Using these, tells the game to get started.
   */
//  public static void main(String[] args) {
//    YourModel model = ...create an example model...
//    YourView viewPlayer1 = new YourView(model);
//    YourView viewPlayer2 = new YourView(model);
//    YourPlayer player1 = new YourHumanPlayer(model);
//    YourPlayer player2 = new YourHumanPlayer(model);
//    YourController controller1 = new YourController(model, player1, viewPlayer1);
//    YourController controller2 = new YourController(model, player2, viewPlayer2);
//    model.startGame();
//  }
}