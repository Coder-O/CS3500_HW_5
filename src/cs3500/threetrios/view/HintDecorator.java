package cs3500.threetrios.view;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;

import cs3500.threetrios.controller.PlayerActionEvents;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCell;
import cs3500.threetrios.model.ThreeTriosGrid;

/**
 * HintDecorator overlays hint values onto grid cells to display
 * how many cards would be flipped if a selected card is played at a specific cell.
 */
public class HintDecorator extends GridDecorator {

  private final GridPanel gridPanel;
  private final ReadOnlyThreeTriosModel model;
  private boolean hintsEnabled;
  private int selectedCardIdx;
  private PlayerActionEvents features;
  private final JComponent gridContainer;

  /**
   * Constructor for the HintDecorator,
   * @param gridPanel The game's original GridPanel
   * @param model  The read-only model to calculate hints.
   */
  public HintDecorator(GridPanel gridPanel, ReadOnlyThreeTriosModel model) {
    super(gridPanel);
    this.gridPanel = gridPanel;
    this.model = model;
    this.hintsEnabled = false;
    this.gridContainer = gridPanel.getGridContainer();

    // KeyListener that enables hints when a player presses "H" or "h"
    gridPanel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'h' || e.getKeyChar() == 'H') {
          hintsEnabled = !hintsEnabled;
          gridPanel.update();
          System.out.println("Hint mode: " + hintsEnabled);
        }
      }
    });

    // Set focusable to ensure the panel can receive key events
    gridPanel.setFocusable(true);
    gridPanel.requestFocusInWindow();

  }

  /**
   * Updates the grid.
   * If a card is selected and hint mode is enabled, it will display the score of playing that
   * card to each cell.
   */
  @Override
  public void update() {
    // Update the original grid first
    super.update();
    System.out.println("Updated. Hints enabled: " + hintsEnabled);

    System.out.println("no card selected yet");

    if (hintsEnabled) {
      System.out.println(gridPanel.getFeatures().getSelectedCardIdx());
    }

    // Overlay hints only if enabled and a card is selected
    if (hintsEnabled && gridPanel.getFeatures().getSelectedCardIdx() >= 0) {
      System.out.println(gridPanel.getFeatures().getSelectedCardIdx());

      ThreeTriosGrid grid = model.getGrid();

      for (int row = 0; row < grid.getNumRows(); row++) {
        for (int col = 0; col < grid.getNumColumns(); col++) {
          ThreeTriosCell cell = grid.getCell(row, col);
          JComponent cellPanel = gridPanel.getCellPanel(row, col);
          if (!cell.isHole() && cell.getCard() == null) {
            int moveScore = -1;
            try {
              moveScore = model.getMoveScore
                      (model.getCurrentPlayer(),
                              selectedCardIdx,
                              row,
                              col
                      ) - 1;
            }
            catch (Exception e){
              //no hint
            }
            cellPanel.removeAll();
            JLabel hintLabel = new JLabel(String.valueOf(moveScore));
            cellPanel.add(hintLabel, BorderLayout.CENTER);
            System.out.println("finish");
          }
        }
      }
    }

    // Refresh the UI to show the hints
    gridContainer.repaint();
    gridContainer.revalidate();
    System.out.println("finish 2");
  }

  /**
   * Returns the current grid.
   * @return the current grid.
   */
  @Override
  public JComponent getComponent() {
    return gridPanel.getComponent();
  }

}
