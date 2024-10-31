package cs3500.ThreeTrios.model;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Reads a grid configuration from a file.
 */
public class ConfigurationReader {

  /**
   * Reads a grid configuration from the provided file.
   * @param filePath The file to read.
   * @return The grid read from the file.
   * @throws FileNotFoundException If the file isn't found
   * @throws IllegalStateException if the file is not structured correctly
   */
  public static Grid readGrid(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(filePath);
    int rows;
    int columns;

    if (scanner.hasNextInt()) {
      rows = scanner.nextInt();
    } else {
      throw new IllegalStateException("The provided file could not be read!!!");
    }
    if (scanner.hasNextInt()) {
      columns = scanner.nextInt();
    } else {
      throw new IllegalStateException("The provided file could not be read!!!");
    }

    GridBuilder builder = new GridBuilder(rows, columns);

    String line;
    for (int row = 0; row < rows; row++) {
      if (!scanner.hasNextLine()) {
        throw new IllegalStateException(
                "The file doe snot have enough rows, it only has " + row + "!"
        );
      }
      line = scanner.nextLine();

      if (line.length() != columns) {
        throw new IllegalStateException(
                "Line " + (row + 2) + " does not have " + columns + " characters, it has "
                        + line.length()
        );
      }

      for (int column = 0; column < columns; column++) {
        switch (line.charAt(column)) {
          case 'C':
            builder.setCell(row, column, new Cell(false));
            break;
          case 'X':
            builder.setCell(row, column, new Cell(true));
            break;
          default:
            throw new IllegalStateException("The file has an illegal character!");
        }
      }
    }

    return builder.buildGrid();
  }

  /**
   * Reads a card configuration from the provided file.
   * @param filePath The file to read.
   * @return The card read from the file.
   * @throws FileNotFoundException If the file isn't found
   * @throws IllegalStateException if the file is not structured correctly
   */
  public static List<ThreeTriosCard> readDeck(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(filePath);

    String name;
    String northValue;
    String southValue;
    while(scanner.hasNextLine()) {
      name = scanner.next();

    }

    return null;
  }

}
