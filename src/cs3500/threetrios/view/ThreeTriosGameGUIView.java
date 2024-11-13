package cs3500.threetrios.view;

import java.awt.*;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.*;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents the GUI view for the ThreeTrios Game.
 */
public class ThreeTriosGameGUIView extends JFrame implements ThreeTriosGUIView {

  private final ReadOnlyThreeTriosModel model;
  private final GridPanel gridPanel;
  private final JPanel redHandPanel;
  private final JPanel blueHandPanel;

  /**
   * Constructor for the ThreeTriosGameGUIView.
   * @param model ReadOnly model (ensures view is incapable of modifying  model)
   */
  public ThreeTriosGameGUIView(ReadOnlyThreeTriosGameModel model) {
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 600));
    this.pack();

    setLayout(new BorderLayout());
    setTitle("Current player: " + model.getCurrentPlayer().getName());

    gridPanel = new GridPanel(model.getGrid());
    add(gridPanel, BorderLayout.CENTER);

    redHandPanel = new JPanel();
    blueHandPanel = new JPanel();
    redHandPanel.setLayout(new BoxLayout(redHandPanel, BoxLayout.Y_AXIS));
    blueHandPanel.setLayout(new BoxLayout(blueHandPanel, BoxLayout.Y_AXIS));

    List<ThreeTriosCard> redHand = model.getHand(ThreeTriosPlayer.RED);
    for (ThreeTriosCard card : redHand) {
      CardPanel cardPanel = new CardPanel(card);
      redHandPanel.add(cardPanel);
    }

    List<ThreeTriosCard> blueHand = model.getHand(ThreeTriosPlayer.BLUE);
    for (ThreeTriosCard card : blueHand) {
      CardPanel cardPanel = new CardPanel(card);
      blueHandPanel.add(cardPanel);
    }

    add(redHandPanel, BorderLayout.WEST);
    add(blueHandPanel, BorderLayout.EAST);

    refresh();
  }

  /**
   * Updates the hand panel for the specified player.
   * @param player the player (RED or BLUE)
   */
  private void updateHandPanel(ThreeTriosPlayer player) {
    // Clear current hand panel
    JPanel handPanel = (player == ThreeTriosPlayer.RED) ? redHandPanel : blueHandPanel;
    handPanel.removeAll();

    // Add each card from the player's hand to the panel
    for (ThreeTriosCard card : model.getHand(player)) {
      CardPanel cardPanel = new CardPanel(card);
      handPanel.add(cardPanel.getComponent());
    }

    handPanel.revalidate();
    handPanel.repaint();
  }

  /**
   * Updates the view based on the model's current state.
   */
  @Override
  public void refresh() {
    // Update the grid display
    ((GridPanel) gridPanel).update();

    // Update the title to show current game state
    setTitle("Current player: " + model.getCurrentPlayer().getName());

    // Update the hand displays for both players
    updateHandPanel(ThreeTriosPlayer.RED);
    updateHandPanel(ThreeTriosPlayer.BLUE);

    // Repaint to apply updates
    repaint();
    revalidate();
  }

  /**
   * Displays the game window.
   */
  @Override
  public void makeVisible() {
    setVisible(true);
  }

}
