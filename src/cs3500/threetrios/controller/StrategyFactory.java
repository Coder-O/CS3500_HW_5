package cs3500.threetrios.controller;

/**
 * A class that creates complete strategies based on an input.
 * Valid strategies are: upperLeft, maxScore, cornerUpperLeft, minCanFlip, and minOpponentMove.
 */
public class StrategyFactory {

  /**
   * Creates a complete strategy based on an input.
   */
  public static FullyCompleteStrategy makeStrategy(String type) {
    switch (type) {
      case "upperLeft" :
        return new TieBreakingCompleteAdapter(
                new UpperLeftmostStrategy()
        );
      case "maxScore" :
        return new CompleteStrategyAdapter(
                new MaximizeScoreStrategy(),
                new TieBreakingCompleteAdapter(
                        new UpperLeftmostStrategy()
                ),
                new UpperLeftmostStrategy()
        );
      case "cornerUpperLeft" :
        return new CompleteStrategyAdapter(
                new GoForCornerStrategy(),
                new TieBreakingCompleteAdapter(
                        new UpperLeftmostStrategy()
                ),
                new UpperLeftmostStrategy()
        );
      case "minCanFlip" :
        return new CompleteStrategyAdapter(
                new MinCanFlipStrategy(),
                new TieBreakingCompleteAdapter(
                        new UpperLeftmostStrategy()
                ),
                new UpperLeftmostStrategy()
        );
      case "minOpponentMove" :
        return new CompleteStrategyAdapter(
                new MinOpponentMoveStrategy(
                        new TieBreakingCompleteAdapter(
                                new UpperLeftmostStrategy()
                        )),
                new TieBreakingCompleteAdapter(
                        new UpperLeftmostStrategy()
                ),
                new UpperLeftmostStrategy()
        );
    }

    throw new IllegalArgumentException("The proved strategy type was invalid!");
  }
}
