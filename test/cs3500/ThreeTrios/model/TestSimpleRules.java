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
//   |9 2 9| |A R A| |9 2 9|
//   |__9__| |__A__| |__9__|
//
//   |--4--| |--9--| |--5--|
//   |9 2 3| |A 2 A| |1 2 7|
//   |__A__| |__A__| |__8__|
  private ThreeTriosGrid allLooseGrid;

  // Defaults to a 1 x 2 grid
  // |--1--| |--A--|
  // |1 R 1| |A 2 A|
  // |__1__| |__A__|
  private ThreeTriosGrid oneLoosesGrid;

//   |--A--| |--A--| |--A--|
//   |A 2 A| |A 2 A| |3 2 A|
//   |__2__| |__A__| |__A__|
//
//   |--7--| |--_--| |--9--|
//   |A 2 A| |_ H _| |9 2 9|
//   |__4__| |__-__| |__9__|
//
//   |--A--| |--_--| |--5--|
//   |A R A| |_ H _| |1 2 1|
//   |__A__| |__-__| |__1__|
  private ThreeTriosGrid holeGrid;


  @Before
  public void initCommonFields() {
    battleRules = new SimpleRules();
    attackingPlayer = ThreeTriosPlayer.RED;

    // Specific cards:
    aceCard = new Card(
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            ThreeTriosAttackValue.A,
            attackingPlayer,
            "Ace"
    );
    worstCard = new Card(
            ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.ONE,
            ThreeTriosAttackValue.ONE,
            attackingPlayer,
            "Worst"
    );;

    // Specific grids:
    allLooseGrid = new GridBuilder(3, 3)
            .setCell(0, 0, new Cell(new Card(
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 0,0"
            )))
            .setCell(0, 1, new Cell(new Card(
                    ThreeTriosAttackValue.SEVEN,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.SEVEN,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 0,1"
            )))
            .setCell(0, 2, new Cell(new Card(
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 0,2"
            )))
            .setCell(1, 0, new Cell(new Card(
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 1,0"
            )))
            .setCell(1, 1, new Cell(aceCard))
            .setCell(1, 2, new Cell(new Card(
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 1,2"
            )))
            .setCell(2, 0, new Cell(new Card(
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 2,0"
            )))
            .setCell(2, 1, new Cell(new Card(
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 2,1"
            )))
            .setCell(2, 2, new Cell(new Card(
                    ThreeTriosAttackValue.FIVE,
                    ThreeTriosAttackValue.SEVEN,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.EIGHT,
                    ThreeTriosPlayer.BLUE,
                    "allLooseGrid 2,2"
            )))
            .buildGrid();


    oneLoosesGrid =  new GridBuilder(1, 2)
            .setCell(0, 0, new Cell(worstCard))
            .setCell(0, 1, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "Ace"
            )))
            .buildGrid();
    holeGrid = new GridBuilder(3, 3)
            .setCell(0, 0, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "Ace"
            )))
            .setCell(1, 1, new Cell(true))
            .setCell(2, 1, new Cell(true))
            .buildGrid();
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


  @Test
  public void testBattleNextToHole() {
    battleRules.battle(aceCard, holeGrid);
    Assert.assertEquals(
            "The attacker should have won",
            holeGrid.getCell(2,1).getCard(),
            null
    );
  }

  @Test
  public void testBattleLossDoesntFlip() {
    battleRules.battle(worstCard, oneLoosesGrid);
    Assert.assertEquals(
            "The attacker should not have flipped",
            oneLoosesGrid.getCell(0,1).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }

  @Test
  public void testBattleComboAll() {
    battleRules.battle(aceCard, holeGrid);
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Assert.assertEquals(
                "All cards should be flipped to the attacking player.",
                holeGrid.getCell(row, col).getCard().getPlayer(),
                attackingPlayer
        );
      }
    }
  }

  @Test
  public void testBattleComboPartial() {
    battleRules.battle(worstCard, oneLoosesGrid);
    Assert.assertEquals(
            "Some cards should be flipped!",
            oneLoosesGrid.getCell(0,0).getCard().getPlayer(),
            attackingPlayer
    );
    Assert.assertEquals(
            "Some cards should not be flipped!",
            oneLoosesGrid.getCell(0,1).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
    Assert.assertEquals(
            "Some cards should not be flipped!",
            oneLoosesGrid.getCell(2,2).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }

  @Test
  public void testPlayer2CanFlip() {
    aceCard.changePlayer();
    battleRules.battle(aceCard, oneLoosesGrid);

    Assert.assertEquals(
            "Player two should have flipped the weak card!",
            oneLoosesGrid.getCell(0, 0).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }
}
