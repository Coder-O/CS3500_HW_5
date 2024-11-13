package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link GoForCornerStrategy}.
 */
public class TestGoForCornerStrategy extends AbstractStrategyTest {

  private GoForCornerStrategy strategy;

  @Override
  public void reset() {
    super.reset();
    strategy = new GoForCornerStrategy();
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

    List<ThreeTriosMove> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 1, 0, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 1, 0, 2));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 1, 2, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 1, 2, 2));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 4, 0, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 4, 0, 2));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 4, 2, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 4, 2, 2));

    Assert.assertTrue(
            "All moves playing 9 or A to a corner should be included!",
            bestMoves.containsAll(expectedMoves)
    );
  }

  @Test
  public void testPartial3x3() {
    setUpPartial3x3();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, ThreeTriosPlayer.BLUE);

    List<Move> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(ThreeTriosPlayer.BLUE, 0, 2, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.BLUE, 2, 2, 0));

    Assert.assertTrue(
            "The returned moves should be as expected!",
            bestMoves.containsAll(expectedMoves)
    );
  }

  @Test
  public void testEmptySplit() {
    setUpEmptySplit();
    List<ThreeTriosMove> bestMoves = strategy.findBestMoves(model, ThreeTriosPlayer.RED);

    List<ThreeTriosMove> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 8, 0, 0));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 8, 0, 9));
    expectedMoves.add(new Move(ThreeTriosPlayer.RED, 8, 4, 0));

    Assert.assertTrue(
            "The returned moves should be as expected!",
            bestMoves.containsAll(expectedMoves)
    );
  }

  @Test
  public void allCornersChecked() {
    setUpPartialSplit();
    strategy.findBestMoves(model, ThreeTriosPlayer.BLUE);

    Set<TranscriptMockModelAdapter.Coordinate> expected = new HashSet<>();
    expected.add(new TranscriptMockModelAdapter.Coordinate(0,0));
    expected.add(new TranscriptMockModelAdapter.Coordinate(0,9));
    expected.add(new TranscriptMockModelAdapter.Coordinate(4,0));
    expected.add(new TranscriptMockModelAdapter.Coordinate(4,9));


    Assert.assertEquals(
            "The strategy should have checked all corners!",
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
}
