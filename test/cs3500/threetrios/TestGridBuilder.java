package cs3500.threetrios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.CellBuilder;
import cs3500.threetrios.model.GridBuilder;
import cs3500.threetrios.model.ThreeTriosCellBuilder;
import cs3500.threetrios.model.ThreeTriosGridBuilder;

/**
 * A testing class for a ThreeTriosGridBuilder.
 */
public class TestGridBuilder {
  private ThreeTriosGridBuilder builder;
  private ThreeTriosCellBuilder cellBuilder;

  @Before
  public void setUp() {
    setUp(3, 3);
  }

  private void setUp(int rows, int columns) {
    cellBuilder = new CellBuilder();
    builder = new GridBuilder(rows, columns, cellBuilder);
  }

  @Test
  public void testConstructorInvalidArguments() {
    Assert.assertThrows(
            "< 1 rows should throw an exception.",
            IllegalArgumentException.class,
        () -> setUp(0, 3)
    );

    Assert.assertThrows(
            "< 1 columns should throw an exception.",
            IllegalArgumentException.class,
        () -> setUp(11, 0)
    );
  }

  @Test
  public void testSetCell() {
    builder.setCell(0, 0, cellBuilder.makeCell(true));
    builder.setCell(0, 1, cellBuilder.makeCell(false));
    builder.setCell(0, 2, cellBuilder.makeCell(true));

    Assert.assertTrue(
            "Set cell should work.",
            builder.buildGrid().getCell(0,0).isHole()
    );

    Assert.assertFalse(
            "Set cell should work.",
            builder.buildGrid().getCell(0,1).isHole()
    );

    Assert.assertNull(
            "Set cell should work.",
            builder.buildGrid().getCell(0, 1).getCard()
    );
  }

  @Test
  public void testSetCellExceptions() {
    Assert.assertThrows(
            "Should not be able to set a cell outside of the valid range!",
            IllegalArgumentException.class,
        () -> builder.setCell(-1, 0, cellBuilder.makeCell(true))
    );

    Assert.assertThrows(
            "Should not be able to set a cell outside of the valid range!",
            IllegalArgumentException.class,
        () -> builder.setCell(0, -1, cellBuilder.makeCell(true))
    );
  }

  @Test
  public void testBuildGridExceptions() {
    builder.setCell(0, 0, cellBuilder.makeCell(true));

    Assert.assertThrows(
            "Should not make a grid with an illegal number of card cells!",
            IllegalStateException.class,
        () -> builder.buildGrid()
    );
  }
}
