package cs3500.threetrios.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosDirection;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Describes what the Card Panels are capable of.
 */
public class CardPanel extends JPanel implements ThreeTriosPanel {

  private final ThreeTriosCard card;
  private JComponent selectedCard = null;

  /**
   * Card Panel constructor.
   * @param card that we wish to display.
   */
  public CardPanel(ThreeTriosCard card) {
    this.card = card;
    setBackground(getPlayerColor(card.getPlayer()));
    setPreferredSize(new Dimension(50, 50));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setOpaque(true);

// Use GridBagLayout to position the labels in a diamond shape
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

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        handleCardClick();
      }
    });

    update();
  }

  /**
   * Handles clicks on a card, toggling selection and highlighting.
   */
  private void handleCardClick() {
    // Print which card was clicked and who owns it
    int cardIndex = getComponentZOrder(this);
    System.out.println("Clicked card index: " + cardIndex + ", Card: " + card.getName());

    // Toggle selection
    if (selectedCard == this) {
      selectedCard.setBorder(null);  // Deselect if already selected
      selectedCard = null;
    } else {
      if (selectedCard != null) {
        selectedCard.setBorder(null);  // Remove highlight from previously selected card
      }
      selectedCard = this;
      selectedCard.setBorder(BorderFactory.createLineBorder(Color.RED, 5));  // Highlight selected card
      selectedCard.revalidate();
      selectedCard.repaint();
    }
  }

  /**
   * Returns the player's color.
   * @param player the card's player
   * @return the player's color
   */
  private Color getPlayerColor(ThreeTriosPlayer player) {
    if (player == ThreeTriosPlayer.RED) {
      return Color.PINK;
    }
      return Color.CYAN;
  }

  /**
   * Updates the card.
   * The only thing that can change is its color.
   */
  @Override
  public void update() {
    setBackground(getPlayerColor(card.getPlayer()));
  }

  /**
   * The game component.
   * @return game component.
   */
  @Override
  public JComponent getComponent() {
    return this;
  }
}
