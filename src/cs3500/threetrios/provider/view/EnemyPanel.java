package cs3500.threetrios.provider.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.threetrios.provider.controller.Features;
import cs3500.threetrios.provider.controller.Player;
import cs3500.threetrios.provider.model.ICard;
import cs3500.threetrios.provider.model.IReadOnlyThreeTriosModel;

/**
 * This class represents a panel that does not register mouse input/clicks.
 */
public class EnemyPanel extends JPanel implements IHandPanel {

  private final IReadOnlyThreeTriosModel model;
  private final Player player;
  private List<Features> features;

  /**
   * Constructor for a HandPanel,
   * sets all the info from the model needed and the dimensions of the hand.
   *
   * @param model the model it is representing.
   */
  public EnemyPanel(IReadOnlyThreeTriosModel model, Player player) {
    this.model = model;
    this.player = player;
    this.features = new ArrayList<>();
    this.setPreferredSize(new Dimension(120, 600));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    int cardWidth = getWidth();
    int cardHeight = 0;
    if (!model.getPlayerHand(player.getColor().toString()).isEmpty()) {
      cardHeight = getHeight() / model.getPlayerHand(player.getColor().toString()).size();
    }

    for (int card = 0; card < model.getPlayerHand(player.getColor().toString()).size(); card++) {
      int x = 0;
      int y = card * cardHeight;

      drawCard(g2, card, x, y);

      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(1));
      g2.drawRect(x, y, cardWidth, cardHeight);
    }
  }

  private void drawCard(Graphics2D g2, int index, int x, int y) {
    int cardWidth = getWidth();
    int cardHeight = getHeight() / model.getPlayerHand(player.getColor().toString()).size();

    ICard card = model.getPlayerHand(player.getColor().toString()).get(index);
    if (player.getColor().toString().equals("R")) {
      g2.setColor(Color.RED);
    } else {
      g2.setColor(Color.BLUE);
    }
    g2.setStroke(new BasicStroke(4));
    g2.fillRect(x, y, cardWidth, cardHeight);

    int northX = x + cardWidth / 2 - 10;
    int northY = y + 20;
    int southX = x + cardWidth / 2 - 10;
    int southY = y + cardHeight - 5;
    int westX = x + 5;
    int westY = y + cardHeight / 2 + 5;
    int eastX = x + cardWidth - 25;
    int eastY = y + cardHeight / 2 + 5;

    g2.setColor(Color.BLACK);
    g2.drawString(String.valueOf(card.getNorth()), northX, northY);
    g2.drawString(String.valueOf(card.getSouth()), southX, southY);
    g2.drawString(String.valueOf(card.getWest()), westX, westY);
    g2.drawString(String.valueOf(card.getEast()), eastX, eastY);
  }


  @Override
  public void addFeatureListener(Features feature) {
    this.features.add(feature);
  }

}
