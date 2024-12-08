package cs3500.threetrios.provider.view;

import java.awt.*;

import javax.swing.*;

/**
 * Creates an overlay that blurs out the JFrame of the player whose turn it's not.
 */
public class BlurOverlay extends JPanel {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(new Color(0, 0, 0, 150));
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g2d.dispose();
  }
}
