package cs3500.threetrios.controller;

import org.junit.Before;

import java.util.List;

import cs3500.threetrios.model.SimpleRules;
import cs3500.threetrios.model.ThreeTriosBattleRules;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGrid;

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

}
