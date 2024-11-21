package cs3500.threetrios.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.threetrios.controller.ViewFeatures;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosDirection;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Contains one card which can be clicked to print its player
 * and its index in the player's hand. Clicked card will also be highlighted.
 * The card's index is 0-indexed.
 */
public class CardPanel extends JPanel implements ThreeTriosPanel {

  private ViewFeatures features;
  private ThreeTriosPlayer player;
  private final ThreeTriosCard card;
  private final int index;
  private boolean isSelected = false;
  private static CardPanel selectedCardPanel = null;

  /**
   * Card Panel constructor.
   * @param card A card in the hand.
   * @param index The index of the Card in the Hand.
   */
  public CardPanel(ThreeTriosCard card, int index) {
    this.card = card;
    this.index = index;

    //draw card
    this.drawCardsHelper();

    update();

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        handleCardClick();
      }
    });
  }

  /**
   * Draws the Card.
   * The background color will be the player's color.
   * The values are arranged in a diamond shape.
   */
  private void drawCardsHelper() {
    setBackground(getPlayerColor(card.getPlayer()));
    setPreferredSize(new Dimension(50, 50));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setOpaque(true);

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.CENTER;

    // North value
    gbc.gridx = 1;
    gbc.gridy = 0;
    JLabel northValue = new JLabel(card.getAttackValue(ThreeTriosDirection.NORTH).getSymbol(),
            SwingConstants.CENTER);
    add(northValue, gbc);

    // East value
    gbc.gridx = 2;
    gbc.gridy = 1;
    JLabel eastValue = new JLabel(card.getAttackValue(ThreeTriosDirection.EAST).getSymbol(),
            SwingConstants.CENTER);
    add(eastValue, gbc);

    // South value
    gbc.gridx = 1;
    gbc.gridy = 2;
    JLabel southValue = new JLabel(card.getAttackValue(ThreeTriosDirection.SOUTH).getSymbol(),
            SwingConstants.CENTER);
    add(southValue, gbc);

    // West value
    gbc.gridx = 0;
    gbc.gridy = 1;
    JLabel westValue = new JLabel(card.getAttackValue(ThreeTriosDirection.WEST).getSymbol(),
            SwingConstants.CENTER);
    add(westValue, gbc);
  }

  /**
   * Prints the index and player of the clicked card.
   * The card's index is 0-indexed.
   */
  public void handleCardClick() {
    System.out.println("Clicked card index: " + index + ", Player: " + card.getPlayer().getName());
    toggleSelection();

    if (features != null) {
      features.handleCardSelection(index, player);
    }
  }

  /**
   * Highlights or removes the highlight of the selected card.
   */
  private void toggleSelection() {
    // Deselect the currently selected card if it exists and is not the current card
    if (selectedCardPanel != null && selectedCardPanel != this) {
      selectedCardPanel.deselect();
    }

    // Select or deselect the current card
    if (isSelected) {
      deselect();
    } else {
      select();
    }
  }

  /**
   * Highlights the selected card.
   */
  private void select() {
    setBorder(BorderFactory.createLineBorder(Color.RED, 5));
    isSelected = true;
    selectedCardPanel = this;
  }

  /**
   * Removes the highlight if the card was deselected.
   * A card can be deselected by clicking it again or clicking another card.
   */
  private void deselect() {
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    isSelected = false;
    selectedCardPanel = null;
  }

  /**
   * Returns the card's color/player.
   * @param player the card's player
   * @return the card's color/player
   */
  private Color getPlayerColor(ThreeTriosPlayer player) {
    if (player == ThreeTriosPlayer.RED) {
      return Color.PINK;
    }
    return Color.CYAN;
  }

  /**
   * Updates the card.
   * The only thing that can change is its color (aka player).
   */
  @Override
  public void update() {
    setBackground(getPlayerColor(card.getPlayer()));
  }

  /**
   * Return the card.
   * @return the card.
   */
  @Override
  public JComponent getComponent() {
    return this;
  }

  /**
   * Adds the features to enable communication with the controller.
   * @param features the controller's features interface.
   * @param player the current player.
   */
  public void addFeatures(ViewFeatures features, ThreeTriosPlayer player) {
    this.features = features;
    this.player = player;
  }
}
