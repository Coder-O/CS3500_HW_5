package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.CellBuilder;
import cs3500.threetrios.model.GridBuilder;
import cs3500.threetrios.model.ThreeTriosCellBuilder;
import cs3500.threetrios.model.ThreeTriosGridBuilder;

/**
 * A testing class for a card builder
 */
public class TestCardBuilder {
  private ThreeTriosCellBuilder builder;

  @Before
  public void setUp() {
    builder = new CellBuilder();
  }

  @Test
  public void testMakeHole() {
    Assert.assertTrue(
            "Should be a hole!",
            builder.makeCell(true).isHole()
    );
  }

  @Test
  public void testMakeCardCell() {
    Assert.assertFalse(
            "Should not be a hole!",
            builder.makeCell(false).isHole()
    );
  }

  @Test
  public void testNullCard() {
    Assert.assertNull(
            "Should not have a card!",
            builder.makeCell(false).getCard()
    );
  }

  @Test
  public void testNoCard() {
    Assert.assertThrows(
            "Should not have a card!",
            IllegalStateException.class,
        () -> builder.makeCell(true).getCard()
    );
  }
}
