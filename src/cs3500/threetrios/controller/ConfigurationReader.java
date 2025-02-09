package cs3500.threetrios.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CellBuilder;
import cs3500.threetrios.model.GridBuilder;
import cs3500.threetrios.model.ThreeTriosAttackValue;
import cs3500.threetrios.model.ThreeTriosCard;
import cs3500.threetrios.model.ThreeTriosCellBuilder;
import cs3500.threetrios.model.ThreeTriosGrid;

/**
 * Reads a configuration from a file.
 */
public class ConfigurationReader {

  /**
   * Reads a grid configuration from the provided file.
   * @param filePath The file to read.
   * @return The grid read from the file.
   * @throws IllegalArgumentException if the file could not be opened.
   * @throws IllegalStateException if the file is not structured correctly
   */

  public static ThreeTriosGrid readGrid(String filePath) {
    File file = new File(filePath);
    ThreeTriosCellBuilder cellBuilder = new CellBuilder();
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException(
              "The file '" + filePath + "' could not be found!!!",
              exception
      );
    }
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

    GridBuilder builder = new GridBuilder(rows, columns, cellBuilder);

    // Leaves the current line, moving on to line 2
    String line = scanner.nextLine();
    for (int row = 0; row < rows; row++) {
      if (!scanner.hasNextLine()) {
        throw new IllegalStateException(
                "The file does not have enough rows, it only has " + row + "!"
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
            builder.setCell(row, column, cellBuilder.makeCell(false));
            break;
          case 'X':
            builder.setCell(row, column, cellBuilder.makeCell(true));
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
   * @throws IllegalArgumentException if the file could not be opened.
   * @throws IllegalStateException if the file is not structured correctly
   */
  public static List<ThreeTriosCard> readDeck(String filePath) {
    File file = new File(filePath);
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException(
              "The file '" + filePath + "' could not be found!!!",
              exception
      );
    }

    List<ThreeTriosCard> deck = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      // CARD_NAME NORTH SOUTH EAST WEST
      // 0          1     2     3   4
      String[] elements = line.split(" ");
      if (elements.length != 5) {
        throw new IllegalStateException("The line '" + line + "' does not have 5 elements!!!");
      }

      deck.add(new Card(
              ThreeTriosAttackValue.attackValueFactory(elements[1]), // North
              ThreeTriosAttackValue.attackValueFactory(elements[3]), // East
              ThreeTriosAttackValue.attackValueFactory(elements[4]), // West
              ThreeTriosAttackValue.attackValueFactory(elements[2]), // South
              null,
              elements[0]                                            // Name
      ));

    }

    return deck;
  }

}
