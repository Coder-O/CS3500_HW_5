package cs3500.threetrios.model;

import cs3500.threetrios.provider.model.ICard;
import cs3500.threetrios.provider.model.ICardCell;

/**
 * Adapts a {@link ThreeTriosCell} to a {@link ICardCell}, if possible
 */
public class CellToICardCellAdapter extends CellToCellAdapter implements ICardCell {

  private ICard card;

  /**
   * Adapts a {@link ThreeTriosCell} to a {@link ICardCell}, if possible
   * @param cell The cell to adapt from
   * @param row The cell's row
   * @param col The cell's column.
   * @throws IllegalArgumentException If the cell is a hole and thus not an ICardCell
   */
  public CellToICardCellAdapter(
          ThreeTriosCell cell,
          int row,
          int col
  )throws IllegalArgumentException {
    super(cell, row, col);
    if (cell.isHole()) {
      throw new IllegalArgumentException(
              "Only card cells may be adapted into ICardCells! Not holes!"
      );
    }

    card = new CardToCardAdapter(cell.getCard()).toCard();
  }

  /**
   * Returns a copy of the card in this cell.
   * Will only ever be called internally by the view, so alias is passed
   * instead of a copy.
   *
   * @return Card this Cell's card.
   */
  @Override
  public ICard getCard() {
    return card;
  }

  /**
   * Sets the card field of this cell to the given Card.
   *
   * @param card Card to be put in this cell.
   */
  @Override
  public void setCard(ICard card) {
    this.card = card;
  }
}
