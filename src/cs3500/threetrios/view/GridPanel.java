package cs3500.threetrios.view;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.threetrios.model.ThreeTriosCell;
import cs3500.threetrios.model.ThreeTriosGrid;

/**
 * Contains cells which can be clicked.
 * Prints the clicked cell's row and col.
 * Row is 0-indexed, with 0 being the topmost row.
 * Col is 0-indexed, with 0 being the furthest left col.
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
   * When a cell is clicked, it will print its row and col.
   * Card cells are drawn in grey, holes are drawn in yellow.
   */
  @Override
  public void update() {
    gridContainer.removeAll();

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumColumns(); col++) {
        ThreeTriosCell cell = grid.getCell(row, col);
        JPanel cellPanel = new JPanel();
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellPanel.setOpaque(true);

        if (cell.isHole()) {
          cellPanel.setBackground(Color.YELLOW);
        } else if (cell.getCard() == null) {
          cellPanel.setBackground(Color.LIGHT_GRAY);
        } else {
          // Generate a unique index based on the cell's position in the grid
          int cardIndex = row * grid.getNumColumns() + col;
          CardPanel cardPanel = new CardPanel(cell.getCard(), cardIndex);
          cardPanel.update();
          cellPanel.setLayout(new BorderLayout());
          cellPanel.add(cardPanel.getComponent(), BorderLayout.CENTER);
        }

        int clickedRow = row;
        int clickedCol = col;

        cellPanel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked cell at: (" + clickedRow + ", " + clickedCol + ")");
          }
        });

        gridContainer.add(cellPanel);
      }
    }

    gridContainer.revalidate();
    gridContainer.repaint();
  }

  /**
   * Returns the current grid.
   * @return the current grid.
   */
  @Override
  public JComponent getComponent() {
    return this;
  }
}
