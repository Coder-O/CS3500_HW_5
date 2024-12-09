package cs3500.threetrios.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import cs3500.threetrios.controller.PlayerActionEvents;
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
  private CardPanel redCardPanel;
  private CardPanel blueCardPanel;
  CardPanel selectedCardPanel = null;
  private final ThreeTriosPlayer playerColor;
  private final GridDecorator hintGrid; //new

  /**
   * Constructor for the ThreeTriosGameGUIView.
   * @param model ReadOnly model (ensures view is incapable of modifying  model)
   */
  public ThreeTriosGameGUIView(ReadOnlyThreeTriosModel model, ThreeTriosPlayer playerColor) {
    this.model = model;
    this.playerColor = playerColor;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 600));
    this.pack();

    //Display which player's turn it is
    setLayout(new BorderLayout());
    setTitle("Current player: " + model.getCurrentPlayer().getName());

    //Build grid
    gridPanel = new GridPanel(model, this);
    hintGrid = new HintDecorator(gridPanel, model); //new: wrap grid with HintDecorator
    add(hintGrid.getComponent(), BorderLayout.CENTER);

    //Build hand panels
    redHandPanel = new JPanel();
    blueHandPanel = new JPanel();
    redHandPanel.setLayout(new BoxLayout(redHandPanel, BoxLayout.Y_AXIS));
    blueHandPanel.setLayout(new BoxLayout(blueHandPanel, BoxLayout.Y_AXIS));

    List<ThreeTriosCard> redHand = model.getHand(ThreeTriosPlayer.RED);
    for (int i = 0; i < redHand.size(); i++) {
      ThreeTriosCard card = redHand.get(i);
      redCardPanel = new CardPanel(card, i, this, ThreeTriosPlayer.RED == playerColor);
      redHandPanel.add(redCardPanel);
    }

    List<ThreeTriosCard> blueHand = model.getHand(ThreeTriosPlayer.BLUE);
    for (int i = 0; i < blueHand.size(); i++) {
      ThreeTriosCard card = blueHand.get(i);
      blueCardPanel = new CardPanel(card, i, this, ThreeTriosPlayer.BLUE == playerColor);
      blueHandPanel.add(blueCardPanel);
    }

    add(redHandPanel, BorderLayout.WEST);
    add(blueHandPanel, BorderLayout.EAST);

    refresh();
  }

  /**
   * Updates the view based on the model's current state.
   */
  @Override
  public void refresh() {
    // Update the title to show current game state
    setTitle("You are: " + playerColor.getName()
            + ". Current player: " + model.getCurrentPlayer().getName());

    // Update the hand displays for both players
    updateHandPanel(ThreeTriosPlayer.RED);
    updateHandPanel(ThreeTriosPlayer.BLUE);
    hintGrid.update();

    //Display final message if game is over
    if (model.isGameOver()) {
      showResultsMessage();
    }

    // Repaint to apply updates
    repaint();
    revalidate();
    setVisible(true);
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
      CardPanel cardPanel = new CardPanel(card, i, this, playerColor == player);
      handPanel.add(cardPanel.getComponent());
    }

    handPanel.revalidate();
    handPanel.repaint();
  }

  /**
   * Displays the game window.
   */
  @Override
  public void makeVisible() {
    setVisible(true);
  }

  /**
   * Register the controller as a listener for player actions.
   * @param features player actions.
   */
  @Override
  public void addFeatures(PlayerActionEvents features) {
    // Pass the features to the grid panel and hand panels
    gridPanel.addFeatures(features);
    for (Component component : redHandPanel.getComponents()) {
      if (component instanceof CardPanel) {
        ((CardPanel) component).addFeatures(features, ThreeTriosPlayer.RED);
      }
    }
    for (Component component : blueHandPanel.getComponents()) {
      if (component instanceof CardPanel) {
        ((CardPanel) component).addFeatures(features, ThreeTriosPlayer.BLUE);
      }
    }
  }

  /**
   * Display an error message.
   * @param message the message we want to display.
   */
  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(
            this,                // Parent component (the JFrame itself)
            message,             // Message to display
            "Error",             // Title of the dialog box
            JOptionPane.ERROR_MESSAGE // Icon type (Error icon)
    );
  }

  /**
   * Display Results Message.
   * If the game was won, will display who won and score.
   * If the game was a tie, will display that it was a tie.
   */
  @Override
  public void showResultsMessage() {
    if (model.getWinner() == null) {
      JOptionPane.showMessageDialog(
              this,                // Parent component (the JFrame itself)
              "The Game was tied.",             // Message to display
              "Game over!",             // Title of the dialog box
              JOptionPane.PLAIN_MESSAGE // Icon type
      );
      return;
    }
    JOptionPane.showMessageDialog(
            this,
            model.getWinner().getName() + "won! Score: " + model.getScore(model.getWinner()),
            "Game over!",
            JOptionPane.PLAIN_MESSAGE
    );
  }

}
