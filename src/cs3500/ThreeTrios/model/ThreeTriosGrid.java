package cs3500.ThreeTrios.model;

import java.util.Map;

/**
* Represents the Grid of the ThreeTrios Game.
* Invariant: there is always an odd number of card cells.
*/
public interface ThreeTriosGrid {
    
    /**
     * Gets a reference to the cell at the specified row and index.
     * @param row The row to get the cell from.
     * @param column The collumn to get the cell from.
     * @return The cell at the specified position. NOTE: Allows for mutation.
     * @throws IllegalArgumentException If the specified position is not in the grid.
     */
    public ThreeTriosCell getCell(int row, int column) throws IllegalArgumentException;

    /**
    * Returns the number of rows in the Grid.
    * @return the number of rows in the Grid.
    */
    public int getNumRows();
    
    /**
    * Returns the number of columns in the Grid.
    * @return the number of columns in the Grid.
    */
    public int getNumColumns();

    /**
     * Returns the number of card cells in the grid, which is always odd.
     * @return the number of card cells in the grid, which is always odd.
     */
    int getNumCardCells();

    /**
     * Returns a map containing all neighbors of this card.
     * @param card The card to find the neighbors for.
     * @return A map containing the neighbors of this card. If a neighbor is missing,
     *        this map does not include an entry or key for it.
     */
    public Map<ThreeTriosDirection, ThreeTriosCard> getNeighbors(ThreeTriosCard card);
}
