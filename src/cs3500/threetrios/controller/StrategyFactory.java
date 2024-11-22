package cs3500.threetrios.controller;

/**
 * A class that creates complete strategies based on an input.
 */
public class StrategyFactory {

  /**
   * Creates a complete strategy based on an input.
   */
  public static FullyCompleteStrategy makeStrategy(String args) {
    return new TieBreakingCompleteAdapter(
            new UpperLeftmostStrategy()
    );
  }
}
