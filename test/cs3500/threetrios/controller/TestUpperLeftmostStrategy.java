package cs3500.threetrios.controller;

/**
 * A testing class for {@link UpperLeftmostStrategy}.
 */
public class TestUpperLeftmostStrategy extends AbstractStrategyTest {
  // todo: Think of what to test

  private UpperLeftmostStrategy strategy;

  @Override
  public void reset() {
    super.reset();
    strategy = new UpperLeftmostStrategy();
  }
}
