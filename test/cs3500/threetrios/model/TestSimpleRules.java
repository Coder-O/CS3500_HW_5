package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SimpleRules.
 */
public class TestSimpleRules {
  private ThreeTriosBattleRules battleRules;

  //|--A--|
  //|A R A|
  //|__A__|
  private ThreeTriosCard aceCard;

  //|--1--|
  //|1 1 1|
  //|__1__|
  private ThreeTriosCard worstCard;
  private ThreeTriosPlayer attackingPlayer;

  // Defaults to 3 x 3 of card cells.
  //   |--8--| |--7--| |--8--|
  //   |8 B 8| |7 B A| |3 B 8|
  //   |__8__| |__9__| |__A__|
  //
  //   |--9--| |--A--| |--9--|
  //   |9 B 9| |A R A| |9 B 9|
  //   |__9__| |__A__| |__9__|
  //
  //   |--4--| |--9--| |--5--|
  //   |9 B 3| |A B A| |1 B 7|
  //   |__A__| |__A__| |__8__|
  private ThreeTriosGrid allLooseGrid;

  // Defaults to a 1 x 3 grid
  // |--1--| |--A--| |--_--|
  // |1 R 1| |A B A| |_ _ _|
  // |__1__| |__A__| |__-__|
  private ThreeTriosGrid oneLoosesGrid;

  //   |--A--| |--A--| |--A--|
  //   |A B A| |A B A| |3 B A|
  //   |__2__| |__A__| |__A__|
  //
  //   |--7--| |--_--| |--9--|
  //   |A B A| |_ H _| |9 B 9|
  //   |__4__| |__-__| |__9__|
  //
  //   |--A--| |--_--| |--5--|
  //   |A R A| |_ H _| |1 B 1|
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
    );

    // Specific grids:
    allLooseGrid = new GridBuilder(3, 3, new CellBuilder())
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


    oneLoosesGrid =  new GridBuilder(1, 3, new CellBuilder())
            .setCell(0, 0, new Cell(worstCard))
            .setCell(0, 1, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "Ace"
            )))
            .setCell(0, 2, new Cell())
            .buildGrid();
    holeGrid = new GridBuilder(3, 3, new CellBuilder())
            .setCell(0, 0, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.TWO,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 0, 0"
            )))
            .setCell(0, 1, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 0, 1"
            )))
            .setCell(0, 2, new Cell(new Card(
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.THREE,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 0, 2"
            )))
            .setCell(1, 0, new Cell(new Card(
                    ThreeTriosAttackValue.SEVEN,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.A,
                    ThreeTriosAttackValue.FOUR,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 1, 0"
            )))
            .setCell(1, 1, new Cell(true))
            .setCell(1, 2, new Cell(new Card(
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosAttackValue.NINE,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 1, 2"
            )))
            .setCell(2, 0, new Cell(aceCard))
            .setCell(2, 1, new Cell(true))
            .setCell(2, 2, new Cell(new Card(
                    ThreeTriosAttackValue.FIVE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosAttackValue.ONE,
                    ThreeTriosPlayer.BLUE,
                    "holeGrid 2, 2"
            )))
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
            attackingPlayer,
            holeGrid.getCell(1,0).getCard().getPlayer()
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
    battleRules.battle(aceCard, allLooseGrid);
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Assert.assertEquals(
                "All cards should be flipped to the attacking player, but "
                        + row + ", " + col + " didn't",
                allLooseGrid.getCell(row, col).getCard().getPlayer(),
                attackingPlayer
        );
      }
    }
  }

  @Test
  public void testBattleComboPartial() {
    battleRules.battle(aceCard, holeGrid);
    Assert.assertEquals(
            "Some cards should be flipped!",
            holeGrid.getCell(0,0).getCard().getPlayer(),
            attackingPlayer
    );
    Assert.assertEquals(
            "Some cards should not be flipped!",
            holeGrid.getCell(0,1).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
    Assert.assertEquals(
            "Some cards should not be flipped!",
            holeGrid.getCell(2,2).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }

  @Test
  public void testBlueCanFlip() {
    aceCard.changePlayer();

    battleRules.battle(aceCard, oneLoosesGrid);

    Assert.assertEquals(
            "Player two should have flipped the weak card!",
            oneLoosesGrid.getCell(0, 0).getCard().getPlayer(),
            ThreeTriosPlayer.BLUE
    );
  }

  @Test
  public void testCorrectScoreBattleComboPartial() {
    Assert.assertEquals(
            "The score should be as expected.",
            3,
            battleRules.battle(aceCard, holeGrid)
    );
  }

  @Test
  public void testCorrectScoreBattleComboAll() {
    Assert.assertEquals(
            "The score should be as expected.",
            9,
            battleRules.battle(aceCard, allLooseGrid)
    );
  }

  @Test
  public void testCorrectScoreAttackFails() {
    Assert.assertEquals(
            "The score should be as expected.",
            1,
            battleRules.battle(worstCard, oneLoosesGrid)
    );
  }

  @Test
  public void testCorrectScoreBlue() {
    aceCard.changePlayer();

    Assert.assertEquals(
            "The score should be as expected.",
            2,
            battleRules.battle(aceCard, oneLoosesGrid)
    );
  }
}
