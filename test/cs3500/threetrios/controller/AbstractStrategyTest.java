package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Before;

import java.util.List;
import java.util.Random;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * An abstract testing class for strategies.
 * Helps set up specific configurations for testing purposes.
 * Holds many methods that each set up a specific scenario for easy testing.
 */
public abstract class AbstractStrategyTest {
  protected ReadOnlyThreeTriosModel model;
  private ThreeTriosModel mutableModel;
  protected Appendable appendable;

  private final String PATH_GRID_3X3 = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3.txt";
  private final String PATH_GRID_SPLIT = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Split.txt";

  // todo: document the player hands that result from the given deck paths.
  private final String PATH_DECK_10 = "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt";
  private final String PATH_DECK_38 = "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt";

  // Setup methods:

  /**
   * Resets the state before every test.
   */
  @Before
  public void reset() {
    model = null;
    mutableModel = null;
    appendable = null;
  }

  /**
   * Sets up the model and the appendable. Used in almost every test.
   * Uses predictable random shuffling for more interesting board states.
   * @param gridPath The file path to the grid for the model.
   * @param deckPath The file path to the deck for the model.
   */
  private void initialSetUp(String gridPath, String deckPath) {
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            gridPath
    );
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            deckPath
    );

    Random random = new Random(0);

    mutableModel = new ThreeTriosGameModel(grid, deck, battleRules, true, random);
    model = mutableModel;
    appendable = new StringBuilder();
  }

  /**
   * Sets up a game with a full grid. Should only be called after setting up an empty grid.
   */
  private void fillGrid() {
    ThreeTriosGrid grid = mutableModel.getGrid();
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int column = 0; column < grid.getNumColumns(); column++) {
        mutableModel.playToGrid(
                mutableModel.getCurrentPlayer(),
                0,
                row,
                column
        );
      }
    }
  }

  /**
   * Sets up an empty 3x3 game.
   */
  protected final void setUpEmpty3x3() {
    initialSetUp(PATH_GRID_3X3, PATH_DECK_10);
  }

  /**
   * Sets up a 3x3 game that is mid-way through
   */
  protected final void setUpPartial3x3() {
    setUpEmpty3x3();
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 1);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 2, 2);
  }

  /**
   * Completely fills the board, preventing any further moves.
   */
  protected final void setUpFull3x3() {
    setUpEmpty3x3();
    fillGrid();
  }

  /**
   * Sets up a board state where no flips are possible.
   */
  protected final void setUpNoFlipsPossible3x3() {
    // todo: write this method!
  }

  /**
   * Sets up an empty split board.
   */
  protected final void setUpEmptySplit() {
    initialSetUp(PATH_GRID_SPLIT, PATH_DECK_38);
  }

  /**
   * Sets up a partially filled game with the split board.
   */
  protected final void setUpPartialSplit() {
    setUpEmptySplit();
    // todo: finish this method.
  }

  /**
   * Sets up a filled game with the split board.
   */
  protected void setUpFullSplit() {
    setUpEmptySplit();
    fillGrid();
  }


  // General tests:

  /**
   * Tests if a given move is legal.
   * @param move The move to test.
   */
  protected void testMoveLegal(Move move) {
    Assert.assertNull(
            "The provided move should be legal.",
            model.canPlayToGrid(
                    move.getPlayer(),
                    move.getCardIdxInHand(),
                    move.getRowIdx(),
                    move.getCollumnIdx()
            )
    );
  }

  /**
   * Tests if a given move has the correct score.
   * @param move The move to test.
   */
  protected void testMoveScoreIsExpected(Move move) {
    Assert.assertEquals(
            "The projected score in the move should be the standard expected!",
            model.getMoveScore(
                    move.getPlayer(),
                    move.getCardIdxInHand(),
                    move.getRowIdx(),
                    move.getCollumnIdx()
            ),
            move.getProjectedScore()
    );
  }
}
