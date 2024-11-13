package cs3500.threetrios.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * A testing class for {@link UpperLeftmostStrategy}, a Tie-Breaking Strategy.
 */
public class TestUpperLeftmostStrategy extends AbstractStrategyTest {

  private UpperLeftmostStrategy strategy;

  @Override
  public void reset() {
    super.reset();
    strategy = new UpperLeftmostStrategy();
  }

  @Test
  public void testNoMovesThrowsException() {
    setUpEmpty3x3();
    Assert.assertThrows(
            "Giving the strategy no moves should throw an exception!",
            IllegalStateException.class,
        () -> strategy.findBestMove(model, ThreeTriosPlayer.RED, new ArrayList<>())
    );
  }

  @Test
  public void testReturnsSingleMove() {
    setUpEmpty3x3();
    ThreeTriosMove expected = new Move(ThreeTriosPlayer.RED, 0, 0, 0);
    ThreeTriosMove actual = strategy.findBestMove(model, ThreeTriosPlayer.RED, List.of(expected));

    Assert.assertEquals(
            "The strategy should return the only move!",
            expected,
            actual
    );
  }

  @Test
  public void testNoMutation() {
    List<ThreeTriosMove> original = new ArrayList<>();
    original.add(new Move(ThreeTriosPlayer.RED, 0, 20, 365));
    original.add(new Move(ThreeTriosPlayer.RED, 46, -12, 4444));
    original.add(new Move(ThreeTriosPlayer.RED, 2, 2, 1));

    List<ThreeTriosMove> used = List.copyOf(original);

    strategy.findBestMove(model, ThreeTriosPlayer.RED, used);

    Assert.assertEquals(
            "The provided list should not be mutated!",
            original,
            used
    );
  }

  @Test
  public void testReturnsExpected() {
    List<ThreeTriosMove> moves = new ArrayList<>();
    moves.add(new Move(ThreeTriosPlayer.RED, 0, 20, 365));
    moves.add(new Move(ThreeTriosPlayer.RED, 46, 4, 4444));
    moves.add(new Move(ThreeTriosPlayer.RED, 2, 2, 1));
    moves.add(new Move(ThreeTriosPlayer.RED, 2, 2, 0));

    ThreeTriosMove bestMove = strategy.findBestMove(model, ThreeTriosPlayer.RED, moves);

    Assert.assertEquals(
            "The chosen move should be as expected!",
            new Move(ThreeTriosPlayer.RED, 2, 2, 0),
            bestMove
    );
  }


}
