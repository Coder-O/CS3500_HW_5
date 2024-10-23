/**
* Represents the Grid of the ThreeTrios Game.
* Invariant: there is always an odd number of cells.
*/
public interface ThreeTriosGrid {
    
    /**
     * Gets a reference to the cell at the specified row and index.
     * @param row The row to get the cell from.
     * @param column The collumn to get the cell from.
     * @return The cell at the specified position. NOTE: Allows for mutation.
     */
    public ThreeTriosCell getCell(int row, int column);

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
    * Returns the North neighbor of a specified Card.
    * @param card whose North neighbor we want.
    * @return a Card.
    */
    public ThreeTriosCard getNorthNeighbor(Card card);

    /**
    * Returns the South neighbor of a specified Card.
    * @param card whose South neighbor we want.
    * @return a Card.
    */
    public ThreeTriosCard getSouthNeighbor(Card card);

    /**
    * Returns the East neighbor of a specified Card.
    * @param card whose East neighbor we want.
    * @return a Card.
    */
    public ThreeTriosCard getEastNeighbor(Card card);

    /**
    * Returns the West neighbor of a specified Card.
    * @param card whose West neighbor we want.
    * @return a Card.
    */
    public ThreeTriosCard getWestNeighbor(Card card);
}
