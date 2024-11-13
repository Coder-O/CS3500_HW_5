package cs3500.threetrios.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.threetrios.model.ThreeTriosCell;
import cs3500.threetrios.model.ThreeTriosGrid;

/**
 * Describes what the Grid Panels are capable of.
 */
public class GridPanel extends JPanel implements ThreeTriosPanel {

  private final ThreeTriosGrid grid;
  private final JPanel gridContainer;

  /**
   * Constructor for GridPanel.
   * @param grid the game's grid.
   */
  public GridPanel(ThreeTriosGrid grid) {
    this.grid = grid;
    this.gridContainer = new JPanel(new GridLayout(grid.getNumRows(), grid.getNumColumns()));
    setLayout(new BorderLayout());
    add(gridContainer, BorderLayout.CENTER);
    update();
  }

  /**
   * Updates the grid.
   */
  @Override
  public void update() {
    gridContainer.removeAll();

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumColumns(); col++) {
        ThreeTriosCell cell = grid.getCell(row, col);
        JPanel cellPanel = new JPanel();
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellPanel.setOpaque(true); // Ensure background color is visible

        if (cell.isHole()) {
          cellPanel.setBackground(Color.YELLOW);
        } else if (cell.getCard() == null) {
          cellPanel.setBackground(Color.LIGHT_GRAY);
        } else {
          CardPanel cardPanel = new CardPanel(cell.getCard());
          cardPanel.update(); // Set initial background color
          cellPanel.setLayout(new BorderLayout());
          cellPanel.add(cardPanel.getComponent(), BorderLayout.CENTER);
        }

        int finalRow = row;
        int finalCol = col;

        cellPanel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked cell at: (" + finalRow + ", " + finalCol + ")");
          }
        });

        gridContainer.add(cellPanel);
      }
    }

    gridContainer.revalidate();
    gridContainer.repaint();
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
