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
 * Main, instantiates a model, instantiates a view
 * using that model, and tells the view to get started.
 */
public final class ThreeTrios {

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
}