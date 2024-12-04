package cs3500.threetrios.model;

import cs3500.threetrios.provider.model.ICard;

/**
 * An adapter that adapts the provider's Card to our Card implementation.
 */
public class CardToCardAdapter implements ICard {

  private final ThreeTriosCard card;

  public CardToCardAdapter(ThreeTriosCard card) {
    this.card = card;
  }

  /**
   * Retrieves this Card's numerical North value.
   */
  @Override
  public int getNorth() {
    return card.getAttackValue(ThreeTriosDirection.NORTH).getValue();
  }

  /**
   * Retrieves this Card's numerical West value.
   */
  @Override
  public int getWest() {
    return card.getAttackValue(ThreeTriosDirection.WEST).getValue();
  }

  /**
   * Retrieves this Card's numerical South value.
   */
  @Override
  public int getSouth() {
    return card.getAttackValue(ThreeTriosDirection.SOUTH).getValue();
  }

  /**
   * Retrieves this Card's numerical East value.
   */
  @Override
  public int getEast() {
    return card.getAttackValue(ThreeTriosDirection.EAST).getValue();
  }

  /**
   * Allows the caller to set the owner of this card.
   * @param owner true is RED, false is BLUE.
   */
  @Override
  public void setOwner(boolean owner) {
    if (owner && card.getPlayer() == ThreeTriosPlayer.BLUE) {
      //change player to red
      card.changePlayer();
    }
    else if (!owner && card.getPlayer() == ThreeTriosPlayer.RED) {
      //change player to blue
      card.changePlayer();
    }
  }

  /**
   * Returns the owner of this card in one character.
   * @return String, R for RED, B for BLUE. Used to help print out game state in view.
   */
  @Override
  public String getOwner() {
    if (card.getPlayer() == null) {
      return null;
    }
    return card.getPlayer().toString().substring(0,1);
  }

  /**
   * Returns the name of the card.
   * @return the cards name.
   */
  @Override
  public String getName() {
    return card.getName();
  }

  /**
   * Returns the adapter as a Card.
   * @return Card
   */
  public ICard toCard() {
    if (card == null) {
      return null;
    }
    return this;
  }
}
