package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link MaximizeScoreStrategy}.
 */
public class TestMaximizeScoreStrategy extends AbstractStrategyTest {
  // todo: Test:
  //  no legal moves throws an exception,
  //  returned moves are legal,
  //  Cases: empty 3x3,
  //  full 3x3,
  //  partial 3x3
  //  no flip possible 3x3
  //  partial Grid.Split
  //  Test all positions checked.
  //  Test outputs legal.
  //  Test best move made (make a mock that lies about the value of moves)

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

}
