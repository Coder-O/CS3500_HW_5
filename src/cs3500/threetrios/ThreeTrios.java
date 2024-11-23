package cs3500.threetrios;

import java.util.List;

import cs3500.threetrios.controller.ConfigurationReader;
import cs3500.threetrios.controller.Player;
import cs3500.threetrios.controller.PlayerController;
import cs3500.threetrios.controller.PlayerControllerImpl;
import cs3500.threetrios.controller.StrategyFactory;
import cs3500.threetrios.controller.StrategyPlayer;
import cs3500.threetrios.controller.ViewFeatures;
import cs3500.threetrios.controller.ViewFeaturesImpl;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;
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

    // Creating model, which controls the game state.
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3H.txt");
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt");

    ThreeTriosGameModel model = new ThreeTriosGameModel(grid, deck, battleRules, false);

    // -- Creating players, which handle player inputs (from machines) --

    Player redPlayer = new StrategyPlayer(
            model,
             StrategyFactory.makeStrategy(""),
             ThreeTriosPlayer.RED
    ); // Either StrategyPlayer(model, Strategy) or HumanPlayer()
    Player bluePlayer = new StrategyPlayer(
            model,
            StrategyFactory.makeStrategy(""),
            ThreeTriosPlayer.BLUE
    ); // Either StrategyPlayer(model, Strategy) or HumanPlayer()

    // -- Creating views, which display information (and potentially handel inputs)--

    // View redView = new ViewImpl(model);
    // Should be the same implementation for both humans and machines
    // But we will need to add a Player as a listener in the human case.
    // View blueView = the same thing...

    ThreeTriosGUIView redGuiView = new ThreeTriosGameGUIView(model, ThreeTriosPlayer.RED);
    ViewFeatures redView = new ViewFeaturesImpl(model, redGuiView);

    ThreeTriosGUIView blueGuiView = new ThreeTriosGameGUIView(model, ThreeTriosPlayer.BLUE);
    ViewFeatures blueView = new ViewFeaturesImpl(model, blueGuiView);

    // -- Creating controllers, which connect each player's components and the model. --

    PlayerController redController = new PlayerControllerImpl(model,
            redView, ThreeTriosPlayer.RED);
    PlayerController blueController = new PlayerControllerImpl(model,
            blueView, ThreeTriosPlayer.BLUE);
    // Constructors will establish the controller as a listener/subscriber to the model and player.
    // If we didn't do it in the view section,
    // it might also set the Player as a listener to the view.


    // -- Starting the game. --
    model.startGame();
    // This has the model call update() on it's subscribers for the first time.
    // Assumes the views set themselves to be visible when updated.

    // todo:
    //  Update interfaces to match this design
    //  Update implementations to match those interfaces.
    //  Document to decrease confusion.
    //

    // todo:
    //  Make the controller assign players to everything else.;
  }
}