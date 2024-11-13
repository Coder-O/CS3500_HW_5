package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link MaximizeScoreStrategy}.
 */
public class TestMaximizeScoreStrategy extends AbstractStrategyTest {

  private MaximizeScoreStrategy strategy;

  @Override
  public void reset() {
    super.reset();
    strategy = new MaximizeScoreStrategy();
  }

  @Test
  public void testNoLegalMovesWrongPlayer() {
    setUpEmpty3x3();
    // Blue can't play first, so there should be no legal moves.
    Assert.assertTrue(
            "There should be no legal moves!!!",
            strategy.findBestMoves(model, ThreeTriosPlayer.BLUE).isEmpty()
    );

    setUpPartial3x3();
    // It is blue's turn, not red, so there should be no legal moves.
    Assert.assertTrue(
            "There should be no legal moves!!!",
            strategy.findBestMoves(model, ThreeTriosPlayer.RED).isEmpty()
    );
  }

  @Test
  public void testNoLegalMovesFullGame() {
    setUpFull3x3();
    // There are no spaces to play, so there should be no legal moves.
    Assert.assertTrue(
            "There should be no legal moves!!!",
            strategy.findBestMoves(model, ThreeTriosPlayer.BLUE).isEmpty()
    );

    setUpFullSplit();
    // There are no spaces to play, so there should be no legal moves.
    Assert.assertTrue(
            "There should be no legal moves!!!",
            strategy.findBestMoves(model, ThreeTriosPlayer.BLUE).isEmpty()
    );
  }

  @Test
  public void testEmpty3x3() {
    setUpEmpty3x3();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, ThreeTriosPlayer.RED);

    Assert.assertEquals(
            "There should be no best move, so all possible moves should be present!",
            45, // 9 spots with 5 choices of card each
            bestMoves.size()
    );
  }

  @Test
  public void testPartial3x3() {
    setUpPartial3x3();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, ThreeTriosPlayer.BLUE);

    Assert.assertEquals(
            "There should be 8 best moves!",
            8, // 2 spots that could flip with 4 choices of card each
            bestMoves.size()
    );

    List<ThreeTriosMove> expectedMoves = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      expectedMoves.add(new Move(ThreeTriosPlayer.BLUE, i, 0, 1));
    }
    for (int i = 0; i < 4; i++) {
      expectedMoves.add(new Move(ThreeTriosPlayer.BLUE, i, 1, 0));
    }

    Assert.assertTrue(
            "The returned moves should be as expected",
            bestMoves.containsAll(expectedMoves)
    );
  }

  @Test
  public void testNoFlipsPossible() {
    setUpNoFlipsPossible3x3();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, ThreeTriosPlayer.BLUE);

    Assert.assertEquals(
            "There should be no best move, so all possible moves should be represented!",
            24, // 6 spots that could flip with 4 choices of card each
            bestMoves.size()
    );
  }

  @Test
  public void testAllPositionsChecked() {
    setUpPartialSplit();
    strategy.findBestMoves(model, ThreeTriosPlayer.BLUE);
    Set<TranscriptMockModelAdapter.Coordinate> expected = new HashSet<>();
    for (int row = 0; row < model.getGrid().getNumRows(); row++) {
      for (int column = 0; column < model.getGrid().getNumColumns(); column++) {
        expected.add(new TranscriptMockModelAdapter.Coordinate(row, column));
      }
    }

    Assert.assertEquals(
            "The strategy should have checked all coordinates!",
            expected,
            model.getCoordinatesChecked()
    );
  }

  @Test
  public void testAllMovesLegal() {
    setUpEmpty3x3();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpFull3x3();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpPartial3x3();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpNoFlipsPossible3x3();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpEmptySplit();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpPartialSplit();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);

    setUpFullSplit();
    bestMoves = strategy.findBestMoves(model, model.getCurrentPlayer());
    testMovesLegal(bestMoves);
  }

  @Test
  public void testBestMoveMade() {
    setUpEmptySplit();
    // A model that asserts the best move is to row 1 column 2 hand index 3.
    ReadOnlyThreeTriosModel fibModel = new FibingMockModel(model);
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(fibModel, ThreeTriosPlayer.RED);

    Assert.assertEquals(
            "The chosen move should be the determined best move!",
            List.of(new Move(ThreeTriosPlayer.RED, 3, 1, 2)),
            bestMoves
    );
  }
}
