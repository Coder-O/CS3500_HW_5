package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Before;

import java.util.List;
import java.util.Random;

import cs3500.threetrios.model.SimpleBattleComparison;
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
abstract class AbstractStrategyTest {
  protected TranscriptMockModelAdapter model;
  public ThreeTriosModel mutableModel;
  protected Appendable appendable;

  private final String PATH_GRID_3X3 = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3.txt";
  private final String PATH_GRID_SPLIT = "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.Split.txt";

  // This path results in the hands:
  //      Red: 1, 9, 3, 5, A
  //     Blue: 7, 6, 2, 8, 4
  private final String PATH_DECK_10 = "src/cs3500/ThreeTrios/ConfigurationFiles/Card.10Cards.txt";
  // This path results in the hands:
  //      Red:   Card7 (7 7 7 7),  C_Card6 (6 6 6 6),  Shield (7 7 1 7),   Card3 (3 3 3 3),
  //           C_Card2 (2 2 2 2),    Card4 (4 4 4 4), C_Card9 (9 9 9 9),   Card9 (9 9 9 9),
  //              Ace2 (A A A A), C_Shield (7 7 1 7),   Card2 (2 2 2 2),     Run (7 9 A 8),
  //        GlassCanon (1 A A 1),  C_Card7 (7 7 7 7),   Spike (A 1 1 1), C_Card1 (1 1 1 1),
  //       StrongSouth (1 1 1 A),    Card1 (1 1 1 1)
  //      Blue:    Card6 (6 6 6 6),          Link (7 7 7 2),  C_Ace2 (A A A A),    Weak (1 3 4 2),
  //                 Ace (A A A A),   StrongNorth (A 1 1 1),    3335 (3 3 5 3),   C_Ace (A A A A),
  //             C_Card4 (4 4 4 4), C_StrongSouth (1 1 1 A), C_Card3 (3 3 3 3),   Mario (3 4 4 A),
  //         GlassGannon (1 1 1 A),       C_Card8 (8 8 8 8),     Med (5 6 4 4), C_Card5 (5 5 5 5),
  //       C_StrongNorth (A 1 1 1),        C_3335 (3 3 5 3),
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
    reset();
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            gridPath
    );
    ThreeTriosBattleRules battleRules = new SimpleRules(new SimpleBattleComparison());
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            deckPath
    );

    Random random = new Random(0);

    mutableModel = new ThreeTriosGameModel(grid, deck, battleRules, true, random);
    appendable = new StringBuilder();
    model = new TranscriptMockModelAdapter(mutableModel, appendable);
  }

  /**
   * Fills up the grid of the model.
   */
  private void fillGrid() {
    ThreeTriosGrid grid = mutableModel.getGrid();
    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int column = 0; column < grid.getNumColumns(); column++) {
        if (
                mutableModel.canPlayToGrid(
                        model.getCurrentPlayer(),
                        0,
                        row,
                        column
                ).isEmpty()
        ) {
          mutableModel.playToGrid(
                  mutableModel.getCurrentPlayer(),
                  0,
                  row,
                  column
          );
        }
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
   * Sets up a 3x3 game that is mid-way through.
   */
  protected final void setUpPartial3x3() {
    setUpEmpty3x3();
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 0, 0);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 2);
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
    setUpEmpty3x3();
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 4, 0, 1);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 3, 0, 2);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 1, 2, 1);
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
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 17, 4, 4);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 17, 0, 0);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 0, 2);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 6);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 0, 7);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 0, 9);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 1, 2);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 1, 9);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 2, 4);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 2, 7);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 2, 8);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 3, 0);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 3, 1);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 3, 2);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 3, 6);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 3, 8);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 4, 1);
    mutableModel.playToGrid(ThreeTriosPlayer.BLUE, 0, 4, 2);
    mutableModel.playToGrid(ThreeTriosPlayer.RED, 0, 4, 6);
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
  protected boolean testMoveLegal(ThreeTriosMove move) {
    Assert.assertTrue(
            "The provided move should be legal.",
            model.canPlayToGrid(
                    move.getPlayer(),
                    move.getCardIdxInHand(),
                    move.getRowIdx(),
                    move.getCollumnIdx()
            ).isEmpty()
    );
    return true;
  }

  /**
   * Tests if each move in a given list is legal.
   * @param moves The moves to test.
   */
  protected boolean testMovesLegal(List<ThreeTriosMove> moves) {
    boolean allTrue = true;
    for (ThreeTriosMove move : moves) {
      allTrue &= testMoveLegal(move);
    }
    return allTrue;
  }
}
