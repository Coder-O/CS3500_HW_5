package cs3500.threetrios;

import java.util.Arrays;
import java.util.List;

import cs3500.threetrios.controller.ConfigurationReader;
import cs3500.threetrios.controller.Player;
import cs3500.threetrios.controller.PlayerColorAdapter;
import cs3500.threetrios.controller.PlayerController;
import cs3500.threetrios.controller.PlayerControllerImpl;
import cs3500.threetrios.controller.ProviderController;
import cs3500.threetrios.controller.StrategyFactory;
import cs3500.threetrios.controller.StrategyPlayer;
import cs3500.threetrios.controller.ViewFeatures;
import cs3500.threetrios.controller.ViewFeaturesImpl;
import cs3500.threetrios.model.FallenAceBattleComparison;
import cs3500.threetrios.model.ModelToProviderAdapter;
import cs3500.threetrios.model.PlusBattleRules;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ReverseBattleComparison;
import cs3500.threetrios.model.SameBattleRules;
import cs3500.threetrios.model.SimpleBattleComparison;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleComparison;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.provider.model.IThreeTriosModel;
import cs3500.threetrios.provider.view.IPlayerView;
import cs3500.threetrios.provider.view.PlayerView;
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
   * @param args The arguments for setup. It must be organized as follows:
   *             <ul>
   *                <li>args[0]: The type of the red player, 'machine' or 'human'.</li>
   *                <li>args[1]: The type of the blue player, 'machine' or 'human'.</li>
   *                <li>args[2]: The strategy of the red player. </li>
   *                <li>args[3]: The strategy of the blue player.</li>
   *                <li>args[4]: The base rule type</li>
   *                <li>args[5-6]: Any decorators to the base rules' comparisons.</li>
   *
   *             </ul>
   *
   *             When inputting arguments, it is important to follow these rules:
   *             <ul>
   *                <li>In order for any argument to be properly read, all proceeding arguments must
   *                be present. For instance, to change the base rule type, both strategy arguments
   *                must be provided.</li>
   *                <li>The player strategy arguments are optional if and only if both players are
   *                'human'. Otherwise, arguments must be inputted, but will be ignored if their
   *                corresponding player is 'human'</li>
   *                <li>Applying the same decorator multiple times is allowed,
   *                but unintended, and effects may vary based on what decorator is repeated.
   *                Generally, applying the same decoration twice would remove it.</li>
   *             </ul>
   *
   *             <p>Valid player strategies are: 'upperLeft', 'maxScore', 'cornerUpperLeft',
   *             'minCanFlip', and 'minOpponentMove'.</p>
   *
   *             <p>Valid base rule types are 'simple' (default), 'same', and 'plus'</p>
   *
   *             <p>Valid decorations to the rules are 'reverse' and 'fallenAce'</p>
   *
   */
  public static void main(String[] args) {

    // interpreting args
    if (args.length < 2 || args.length > 7 || args.length == 3) {
      throw new IllegalArgumentException("An incorrect number of parameters were given!");
    }

    String redPlayerType = args[0];
    String bluePlayerType = args[1];

    String redStrategyType = null;
    String blueStrategyType = null;

    if (!(redPlayerType.equals("human") && bluePlayerType.equals("human"))) {
      if (args.length < 4) {
        throw new IllegalArgumentException("An incorrect number of parameters were given!");
      }
      redStrategyType = args[2];
      blueStrategyType = args[3];
    }

    // Creating model, which controls the game state.
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3.txt");
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt");

    // Creating the battleRules
    ThreeTriosBattleRules battleRules;

    if (args.length < 5) {
      battleRules = new SimpleRules(new SimpleBattleComparison());
    } else {
      battleRules = makeBattleRules(
              args[4],
              Arrays.asList(args).subList(5, args.length)
      );
    }

    ThreeTriosGameModel model = new ThreeTriosGameModel(grid, deck, battleRules, true);
    IThreeTriosModel adaptedModel = new ModelToProviderAdapter(model);

    // -- Creating players, which handle player inputs (mainly for ai implementations) --

    Player redPlayer = choosePlayer(
            model,
            ThreeTriosPlayer.RED,
            redPlayerType,
            redStrategyType
    );

    // -- Creating views, which display information
    // (and potentially handel inputs for human players)--

    ThreeTriosGUIView redGuiView = new ThreeTriosGameGUIView(model, ThreeTriosPlayer.RED);
    ViewFeatures redView = new ViewFeaturesImpl(model, redGuiView);

    IPlayerView blueView = new PlayerView(
            adaptedModel,
            new PlayerColorAdapter(ThreeTriosPlayer.BLUE),
            new PlayerColorAdapter(ThreeTriosPlayer.BLUE.getOpposingPlayer())
    );

    // -- Creating controllers, which connect each player's components and the model. --

    PlayerController redController = new PlayerControllerImpl(model,
            redView, redPlayer, ThreeTriosPlayer.RED);
    ProviderController blueController = new ProviderController(adaptedModel,
            ThreeTriosPlayer.BLUE, blueView);

    // -- Starting the game. --
    model.startGame();
    // This has the model call update() on it's subscribers for the first time.
    // Assumes the views set themselves to be visible when updated.

  }

  /**
   * Creates a {@link ThreeTriosBattleRules} object based on input.
   * @param baseType The base type of the rules, 'simple', 'same', or 'plus'
   * @param decorators The decorators to apply to the rules, 'reverse' or 'fallenAce'
   * @return A {@link ThreeTriosBattleRules} object of the desired type.
   */
  private static ThreeTriosBattleRules makeBattleRules(String baseType, List<String> decorators) {

    // Creare the comparison strategy
    ThreeTriosBattleComparison comparisonStrategy = new SimpleBattleComparison();
    for (String decorator : decorators) {
      switch (decorator) {
        case "reverse":
          comparisonStrategy = new ReverseBattleComparison(comparisonStrategy);
          break;
        case "fallenAce":
          comparisonStrategy = new FallenAceBattleComparison(comparisonStrategy);
          break;
        default:
          throw new IllegalArgumentException(
                  "The comparison decorator '" + decorator + "' is invalid!"
          );
      }
    }

    // Create the full strategy
    ThreeTriosBattleRules toReturn;
    switch (baseType) {
      case "simple":
        toReturn = new SimpleRules(comparisonStrategy);
        break;
      case "same":
        toReturn = new SameBattleRules(comparisonStrategy);
        break;
      case "plus":
        toReturn = new PlusBattleRules(comparisonStrategy);
        break;
      default:
        throw new IllegalArgumentException(
                "The provided base rule type '" + baseType + "' is invalid!"
        );
    }

    return toReturn;
  }

  /**
   * Creates a Player object based on input.
   * @param model The model for this player to observe.
   * @param playerColor The color this Player shall play for.
   * @param type The type of player, 'human' or 'machine'
   * @param strategy The strategy for that player to follow, if it is a machine player.
   * @return The corresponding player.
   */
  private static Player choosePlayer(
          ReadOnlyThreeTriosModel model,
          ThreeTriosPlayer playerColor,
          String type,
          String strategy
  ) {

    if (type.equals("human")) {
      return null;
    }
    else if (type.equals("machine")) {
      return new StrategyPlayer(model, StrategyFactory.makeStrategy(strategy), playerColor);
    }
    throw new IllegalStateException("The arguments of main were invalid!");
  }
}