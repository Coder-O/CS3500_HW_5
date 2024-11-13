package cs3500.threetrios.view;

import java.awt.*;
import javax.swing.*;

import java.util.List;

import javax.swing.JFrame;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosPlayer;

/**
 * Represents the GUI view for the ThreeTrios Game.
 * The view consists of both player's hands and the playing grid.
 * The view will also display which player's turn it is.
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

    //Display which player's turn it is
    setLayout(new BorderLayout());
    setTitle("Current player: " + model.getCurrentPlayer().getName());

    //Build grid
    gridPanel = new GridPanel(model.getGrid());
    add(gridPanel, BorderLayout.CENTER);

    //Build hand panels
    redHandPanel = new JPanel();
    blueHandPanel = new JPanel();
    redHandPanel.setLayout(new BoxLayout(redHandPanel, BoxLayout.Y_AXIS));
    blueHandPanel.setLayout(new BoxLayout(blueHandPanel, BoxLayout.Y_AXIS));

    List<ThreeTriosCard> redHand = model.getHand(ThreeTriosPlayer.RED);
    for (int i = 0; i < redHand.size(); i++) {
      ThreeTriosCard card = redHand.get(i);
      CardPanel cardPanel = new CardPanel(card, i);
      redHandPanel.add(cardPanel);
    }

    List<ThreeTriosCard> blueHand = model.getHand(ThreeTriosPlayer.BLUE);
    for (int i = 0; i < blueHand.size(); i++) {
      ThreeTriosCard card = blueHand.get(i);
      CardPanel cardPanel = new CardPanel(card, i);
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
    List<ThreeTriosCard> hand = model.getHand(player);
    for (int i = 0; i < hand.size(); i++) {
      ThreeTriosCard card = hand.get(i);
      CardPanel cardPanel = new CardPanel(card, i);
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
