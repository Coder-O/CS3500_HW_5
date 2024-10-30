package cs3500.ThreeTrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SimpleRules.
 */
public class TestSimpleRules {
  private ThreeTriosBattleRules battleRules;

  //|--A--|
  //|A 1 A|
  //|__A__|
  private ThreeTriosCard aceCard;

  //|--1--|
  //|1 1 1|
  //|__1__|
  private ThreeTriosCard worstCard;
  private ThreeTriosPlayer attackingPlayer;

  // Defaults to 3 x 3 of card cells.
//   |--8--| |--7--| |--8--|
//   |8 2 8| |7 2 A| |3 2 8|
//   |__8__| |__9__| |__A__|
//
//   |--9--| |--A--| |--9--|
//   |9 2 9| |A 1 A| |9 2 9|
//   |__9__| |__A__| |__9__|
//
//   |--4--| |--9--| |--5--|
//   |9 2 3| |A 2 A| |1 2 7|
//   |__A__| |__A__| |__8__|
  private ThreeTriosGrid allLooseGrid;

  // Defaults to a 1 x 2 grid
  // |--1--| |--A--|
  // |1 1 1| |A 2 A|
  // |__1__| |__A__|
  private ThreeTriosGrid oneLoosesGrid;

//   |--5--| |--7--| |--8--|
//   |8 2 1| |A 2 A| |3 2 8|
//   |__2__| |__9__| |__A__|
//
//   |--7--| |--_--| |--9--|
//   |9 2 9| |_ H _| |9 2 9|
//   |__4__| |__-__| |__9__|
//
//   |--5--| |--_--| |--5--|
//   |A 2 A| |_ H _| |1 2 7|
//   |__A__| |__-__| |__8__|
  private ThreeTriosGrid holeGrid


  @Before
  public void initCommonFields() {
    battleRules = new SimpleRules();
    attackingPlayer = ThreeTriosPlayer.ONE;

    // Specific cards:
    aceCard = new ;
    worstCard = new ;

    // Specific grids:
    allLooseGrid = new SimpleGridBuilder().buildGrid();
    oneLoosesGrid =  new SimpleGridBuilder().buildGrid();
  }

  @Test
  public void testAttackerNotFlipped() {
    battleRules.battle(worstCard, oneLoosesGrid);
    Assert.assertEquals(
            "The attacker should not have flipped",
            oneLoosesGrid.getCell(0,0).getCard().getPlayer(),
            attackingPlayer
    );
  }

  @Test
  public void testBattleNorthWin() {
    battleRules.battle(aceCard, allLooseGrid);
    Assert.assertEquals(
            "The attacker should have won",
            allLooseGrid.getCell(0,1).getCard().getPlayer(),
            attackingPlayer
    );
  }

  @Test
  public void testBattleEastWin() {
    battleRules.battle(aceCard, allLooseGrid);
    Assert.assertEquals(
            "The attacker should have won",
            allLooseGrid.getCell(1,2).getCard().getPlayer(),
            attackingPlayer
    );
  }

  @Test
  public void testBattleSouthWin() {
    battleRules.battle(aceCard, allLooseGrid);
    Assert.assertEquals(
            "The attacker should have won",
            allLooseGrid.getCell(2,1).getCard().getPlayer(),
            attackingPlayer
    );
  }

  @Test
  public void testBattleWestWin() {
    battleRules.battle(aceCard, allLooseGrid);
    Assert.assertEquals(
            "The attacker should have won",
            allLooseGrid.getCell(1,0).getCard().getPlayer(),
            attackingPlayer
    );
  }

  // Gained by AttackerNotFlipped
  @Test
  public void testBattleOnEdge() {

  }

  @Test
  public void testBattleNextToHole() {

  }

  @Test
  public void testBattleLoss() {

  }

  @Test
  public void testBattleComboAll() {

  }

  @Test
  public void testBattleComboPartial() {

  }

  @Test
  public void testPlayer2CanFlip() {
    attackingPlayer = ThreeTriosPlayer.TWO;
  }
}
