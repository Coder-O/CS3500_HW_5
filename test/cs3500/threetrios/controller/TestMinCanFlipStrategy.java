package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link MinCanFlipStrategy}.
 */
public class TestMinCanFlipStrategy extends AbstractStrategyTest {

  private MinCanFlipStrategy strategy;

  @Override
  public void reset() {
    super.reset();
    strategy = new MinCanFlipStrategy();
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
            "There should be no best position, but two best cards. So 18 possible moves.",
            18, // 9 spots with 2 choices of card each
            bestMoves.size()
    );
  }

}
