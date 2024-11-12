package cs3500.threetrios.controller;

import org.junit.Before;

/**
 * A testing class for {@link GoForCornerStrategy}.
 */
public class TestGoForCornerStrategy extends AbstractStrategyTest {
  // todo: Think of what to test

  private GoForCornerStrategy strategy;

  // Is this @Before needed?
  @Before
  @Override
  public void reset() {
    super.reset();
    strategy = new GoForCornerStrategy();
  }
}
