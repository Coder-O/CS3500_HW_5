# CS3500_HW_5

This codebase is meant to represent a game of ThreeTrios. Players take turns playing cards from their hands onto a grid

The following assumptions are made:
- There are only 2 players.
- Players will specify locations in hand/grid using integer coordinates.
- Game will track whose turn it is.

Quick Start: 
    
    // Get a grid and deck to start the game with, from configuration files 'exampleGrid.txt' and 'exampleDeck.txt' in the root directory
    ThreeTriosGrid grid = ConfigurationReader.readGrid("exampleGrid.txt");
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck("exampleGrid.txt");
    
    // Get an instance of the rules to start a game with, such as SimpleRules
    ThreeTriosBattleRules battleRules = new SimpleRules();
    
    // Starts a game!!!
    ThreeTriosModel model = new ThreeTriosGameModel(grid, deck, battleRules);
    
    // Sets up a view
    ThreeTriosView view = new ThreeTriosView(ThreeTriosModel model, System.out);
    
    // From here, take player actions
    ThreeTriosGameModel.playToGrid(ThreeTriosPlayer.Red, 0, 0, 0);
    ThreeTriosGameModel.playToGrid(ThreeTriosPlayer.Blue, 0, 1, 1);
    
    // And view the game
    view.render()

Key Components and Subcomponents:
- Controller: drives the control-flow of the game. To be developed.
    - ConfigurationReader: Creates a ThreeTriosGrid or a list of ThreeTriosCards from a file.
- ThreeTriosModel: is driven by the controller. Manages the rules and objects of the game.
    - ThreeTriosGrid: the playing grid, a 2 by 2 grid that holds cells as described below. A grid must have an odd number of card cells.
    - ThreeTriosCell: a cell in the grid. It can be a hole (a non-existant position) or a card cell (can hold a card).
    - ThreeTriosCard: the cards of the game. Every card in the game must have a unique name.
    - ThreeTriosBattleRules: the rules that govern the ThreeTrios game.
    - ThreeTriosDirection: An enum describing the directions a ThreeTriosCard can have neighbors in.
    - ThreeTriosPlayer: An enum describing the 2 players: Red and Blue.
    - ThreeTriosAttackValue: An enum describing attack values for the cards (1, 2, 3, 4, 5, 6, 7, 8, 9, A) (A is numerically equivalent to 10.)
    - ThreeTriosGridBuilder: help initializes an intance of a grid. Generally better to use ConfigurationReader from a user standpoint.
    
- ThreeTriosView: renders the game into a textual view. Graphic view to be developed.

Source Organization:
- Src
    - Controller
        - (Controller related code)
    - Model
        - (ThreeTriosModel and related code)
    - View
        - ThreeTriosView
    - Configurations
        - (Configuration files)
- Test
    - (Tests mirroring the organization above)

Changes for Part 2:
- New features for HW6
    - Created a ReadOnlyThreeTriosModel interface for the observation methods of the model. The ThreeTriosModel now extends this class.
    - Created a canPlayToGrid method in the model interface and implemntation, which determines the legality of a move.
        - This is useful for strategies to know what moves are legal.
    - Created a getMoveScore method in the model interface and implemntation, which returns the score of a move as determined by the BattleRules implmentation.
        - This is useful for strategies to determine what moves to make.
        - To accomplish this, the ThreeTriosBattleRules interface and implemntations were edited so that they return a score.
    - Added a getOpposingPlayer method to the ThreeTriosPlayer enum, as it is useful for strategies to be able to easily find the opposing player.

- Fixing bugs/issues
    - Revised methods to disallow illegal mutation
        - Added copy methods in ThreeTriosCard, ThreeTriosCell, and ThreeTriosGrid to achive this.
    -  Added a setPlayer(Player player) method to ThreeTriosCard and implementations to help fix an error in setting up the player hands
        - Specifically, this allows the cards to know what player they have been assigned to when they are assigned to that player, rather than relying on fliping the player, which cannot easily initialize.
    - Added a constructor to the model that takes in a random object and a boolean shuffle to create more varied tests
    - Based on feedback from class, moved the ConfigurationReader to the controller, as it deals with input/output and thus belongs there
        - To help support this, created a CellBuilder interface and implmentation (so that the Cell implmentation remains package-private)
    - Removed redundant methods
        - In card, removed getNorth(), getSouth(), getEast() and getWest(), as their functionality is more elegantly handled by getAttackValue
        - Removed isGameWon, as its functionality exists as part of getWinner()
    -  Added a getWinner() method to ThreeTriosModel and its implemntation.
        - This is very important for determining the outcome of a game. It may have been lost to troubles with Git previously.
    - Added small improvemnts to interfaces and implementations
        - Changed ArrayList return types to Lists
        - Changed some exceptions to be more specific (such as throwing an IndexOutOfBounds exception instead of an IllegalArgument exception for certain errors.)
    - Added more thorough tests to all parts of the program, and made many small updates to implementations to fix previouly uncaught errors

