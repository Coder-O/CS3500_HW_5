package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link CompleteStrategyAdapter}.
 */
public class TestCompleteStrategies extends AbstractStrategyTest {
  private FullyCompleteStrategy maxScoreUpperLeftStrategy;
  private FullyCompleteStrategy cornerUpperLeftStrategy;
  private FullyCompleteStrategy upperLeftCompleteStrategy;
  private List<FullyCompleteStrategy> strategies;

  @Override
  public void reset() {
    super.reset();

    upperLeftCompleteStrategy = new TieBreakingCompleteAdapter(
            new UpperLeftmostStrategy()
    );

    maxScoreUpperLeftStrategy = new CompleteStrategyAdapter(
            new MaximizeScoreStrategy(),
            new TieBreakingCompleteAdapter(
                    new UpperLeftmostStrategy()
            ),
            new UpperLeftmostStrategy()
    );

    cornerUpperLeftStrategy = new CompleteStrategyAdapter(
            new GoForCornerStrategy(),
            new TieBreakingCompleteAdapter(
                    new UpperLeftmostStrategy()
            ),
            new UpperLeftmostStrategy()
    );

    strategies = new ArrayList<>();
    strategies.add(maxScoreUpperLeftStrategy);
    strategies.add(cornerUpperLeftStrategy);
    strategies.add(upperLeftCompleteStrategy);
  }

  @Test
  public void testNoLegalMovesWrongPlayer() {
    for (CompleteStrategy strategy : strategies) {
      setUpEmpty3x3();
      // Blue can't play first, so there should be no legal moves.
      Assert.assertThrows(
              "There should be no legal moves!!!",
              IllegalStateException.class,
        () -> strategy.findBestMove(model, ThreeTriosPlayer.BLUE)
      );

      setUpPartial3x3();
      // It is blue's turn, not red, so there should be no legal moves.
      Assert.assertThrows(
              "There should be no legal moves!!!",
              IllegalStateException.class,
        () -> strategy.findBestMove(model, ThreeTriosPlayer.RED)
      );
    }

  }

  @Test
  public void testNoLegalMovesFullGame() {
    for (CompleteStrategy strategy : strategies) {
      setUpFull3x3();
      // There are no spaces to play, so there should be no legal moves.
      Assert.assertThrows(
              "There should be no legal moves!!!",
              IllegalStateException.class,
        () -> strategy.findBestMove(model, ThreeTriosPlayer.BLUE)
      );

      setUpFullSplit();
      // There are no spaces to play, so there should be no legal moves.
      Assert.assertThrows(
              "There should be no legal moves!!!",
              IllegalStateException.class,
              () -> strategy.findBestMove(model, ThreeTriosPlayer.BLUE)
      );
    }
  }

  @Test
  public void testEmpty3x3Max() {
    setUpEmpty3x3();
    ThreeTriosMove bestMove = maxScoreUpperLeftStrategy.findBestMove(model, ThreeTriosPlayer.RED);
    ThreeTriosMove expected = new Move(ThreeTriosPlayer.RED, 0,0,0);

    Assert.assertEquals(
            "The move should be as expected!",
            expected,
            bestMove
    );
  }

  @Test
  public void testEmpty3x3Corner() {
    setUpEmpty3x3();
    ThreeTriosMove bestMove = cornerUpperLeftStrategy.findBestMove(model, ThreeTriosPlayer.RED);
    // Play card 9 to top left corner.
    ThreeTriosMove expected = new Move(ThreeTriosPlayer.RED, 1,0,0);

    Assert.assertEquals(
            "The move should be as expected!",
            expected,
            bestMove
    );
  }

  @Test
  public void testPartial3x3Max() {
    setUpPartial3x3();
    ThreeTriosMove bestMove = maxScoreUpperLeftStrategy.findBestMove(model, ThreeTriosPlayer.BLUE);
    // Play first card in hand to top middle to flip top left
    ThreeTriosMove expected = new Move(ThreeTriosPlayer.BLUE, 0,0,1);

    Assert.assertEquals(
            "The move should be as expected!",
            expected,
            bestMove
    );
  }

  @Test
  public void testPartial3x3Corner() {
    setUpPartial3x3();
    ThreeTriosMove bestMove = cornerUpperLeftStrategy.findBestMove(model, ThreeTriosPlayer.BLUE);
    ThreeTriosMove expected = new Move(ThreeTriosPlayer.BLUE, 0, 2, 0);

    Assert.assertEquals(
            "The move should be as expected!",
            expected,
            bestMove
    );
  }

  @Test
  public void testAllMovesLegal() {
    for (CompleteStrategy strategy : strategies) {
      setUpEmpty3x3();
      ThreeTriosMove bestMove = strategy.findBestMove(model, model.getCurrentPlayer());
      testMoveLegal(bestMove);

      setUpPartial3x3();
      bestMove = strategy.findBestMove(model, model.getCurrentPlayer());
      testMoveLegal(bestMove);

      setUpNoFlipsPossible3x3();
      bestMove = strategy.findBestMove(model, model.getCurrentPlayer());
      testMoveLegal(bestMove);

      setUpEmptySplit();
      bestMove = strategy.findBestMove(model, model.getCurrentPlayer());
      testMoveLegal(bestMove);

      setUpPartialSplit();
      bestMove = strategy.findBestMove(model, model.getCurrentPlayer());
      testMoveLegal(bestMove);
    }
  }

  @Test
  public void testFullGames() {
    for (CompleteStrategy redStrategy : strategies) {
      for (CompleteStrategy blueStrategy : strategies) {
        setUpEmpty3x3();
        testFullGame(redStrategy, blueStrategy);

        setUpEmptySplit();
        testFullGame(redStrategy, blueStrategy);
      }
    }
  }

  /**
   * Helper method for testing a full game for a given setup.
   */
  private void testFullGame(CompleteStrategy redStrategy, CompleteStrategy blueStrategy) {
    Map<ThreeTriosPlayer, CompleteStrategy> strategyMap = new HashMap<>();
    strategyMap.put(ThreeTriosPlayer.RED, redStrategy);
    strategyMap.put(ThreeTriosPlayer.BLUE, blueStrategy);

    while (!model.isGameOver()) {
      ThreeTriosPlayer currentPlayer = model.getCurrentPlayer();
      ThreeTriosMove move = strategyMap.get(currentPlayer).findBestMove(model, currentPlayer);
      mutableModel.playToGrid(
              move.getPlayer(),
              move.getCardIdxInHand(),
              move.getRowIdx(),
              move.getCollumnIdx()
      );
    }

    Assert.assertTrue(model.isGameOver());
    Assert.assertEquals(model.getCurrentPlayer(), ThreeTriosPlayer.BLUE);

    // For fun:
    // System.out.println(model.getGrid());
  }

  @Test
  public void testFullyCompleteness() {
    for (FullyCompleteStrategy strategy : strategies) {
      Assert.assertTrue(
              "Should be fully complete!",
              strategy.findsAtLeastOneMove()
      );
    }
  }

  @Test
  public void testSimpleStrategy() {
    setUpEmpty3x3();
    upperLeftCompleteStrategy.findBestMove(model, ThreeTriosPlayer.RED);
    System.out.println(appendable.toString());
  }

}
