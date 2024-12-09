# CS3500_HW_5

This codebase is meant to represent a game of ThreeTrios. Players take turns playing cards from their hands onto a grid

The following assumptions are made:
- There are only 2 players.
- Game will track whose turn it is.

Quick Start: 

    // Use the provided .jar, or:
    
    

    // Creating model, which controls the game state.
    ThreeTriosGrid grid = ConfigurationReader.readGrid(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Grid.3x3H.txt");
    ThreeTriosBattleRules battleRules = new SimpleRules();
    List<ThreeTriosCard> deck = ConfigurationReader.readDeck(
            "src/cs3500/ThreeTrios/ConfigurationFiles/Card.38Cards.txt");

    ThreeTriosGameModel model = new ThreeTriosGameModel(grid, deck, battleRules, false);

    // -- Creating players, which handle player inputs (mainly for ai implementations) --

    Player redPlayer = new StrategyPlayer(
            model,
             StrategyFactory.makeStrategy(""),
             ThreeTriosPlayer.RED
    );
    Player bluePlayer = new StrategyPlayer(
            model,
            StrategyFactory.makeStrategy(""),
            ThreeTriosPlayer.BLUE
    );

    // -- Creating views, which display information
    // (and potentially handel inputs for human players)--

    ThreeTriosGUIView redGuiView = new ThreeTriosGameGUIView(model, ThreeTriosPlayer.RED);
    ViewFeatures redView = new ViewFeaturesImpl(model, redGuiView);

    ThreeTriosGUIView blueGuiView = new ThreeTriosGameGUIView(model, ThreeTriosPlayer.BLUE);
    ViewFeatures blueView = new ViewFeaturesImpl(model, blueGuiView);

    // -- Creating controllers, which connect each player's components and the model. --

    PlayerController redController = new PlayerControllerImpl(model,
            redView, ThreeTriosPlayer.RED);
    PlayerController blueController = new PlayerControllerImpl(model,
            blueView, ThreeTriosPlayer.BLUE);

    // -- Starting the game. --
    model.startGame();

Key Components and Subcomponents:
- Controller: drives the control-flow of the game.
    - ConfigurationReader: Creates a ThreeTriosGrid or a list of ThreeTriosCards from a file.
    - ModelFeatures: an interface for a model to implement that details its interactions with a controller.
    - Player: An interface for a Player in the game, which handels decition making and strategies for an in-game player.
        - Note: it is possible for a view to handel user input and call the controller directly, so Player implementations are not always requied.
    - PlayerActionEvents: The high-level actions a real-world player can take. This interface is implemented by any component that wishes to react directly to such events,             such as a controller or view.
    - PlayerController: The core of the overal controller section. Every in-game player has a coresponding controller, which manages interactions between components.
    - ViewFeatures: The features a View must implemnt to interact with a controller.
    - FullyCompleteStrategy: An interface for a strategy that is guaranteed to find a single move for a given board state, should a legal move be possible.
    - CompleteStrategy: An interface for a strategy that is guaranteed to find a single move for a given board state or throw an exception.
    - TieBreakingStrategy: An interface for a strategy that is guaranteed to find a single move for a given board state and list of moves or throw an exception.
    - TieNarrowingStrategy: An interface for a strategy that finds a list of desired moves for a given board state and list of moves. Intended to narrow down move options, and allow multiple strategies to be combined.
    - FallibleStrategy: An interface for a strategy that that finds a list of desired moves for a given board state.
    - Strategy: An interface for other strategies to build off of. Includes a findsAtLeastOneMove method to allow users to know if a given strategy allways returns at least one move.
    - Several adapters exist to convert between and combine different types of strategies.
    - ThreeTriosMove: Represents a move in a game of ThreeTrios. Also stores a score that can be used by strtegies to assign a value to a move.
- ThreeTriosModel: is driven by the controller. Manages the rules and objects of the game.
    - ThreeTriosGrid: the playing grid, a 2 by 2 grid that holds cells as described below. A grid must have an odd number of card cells.
    - ThreeTriosCell: a cell in the grid. It can be a hole (a non-existant position) or a card cell (can hold a card).
    - ThreeTriosCard: the cards of the game. Every card in the game must have a unique name.
    - ThreeTriosBattleRules: the rules that govern the ThreeTrios game.
    - ThreeTriosDirection: An enum describing the directions a ThreeTriosCard can have neighbors in.
    - ThreeTriosPlayer: An enum describing the 2 players: Red and Blue.
    - ThreeTriosAttackValue: An enum describing attack values for the cards (1, 2, 3, 4, 5, 6, 7, 8, 9, A) (A is numerically equivalent to 10.)
    - ThreeTriosGridBuilder: help initializes an intance of a grid. Generally better to use ConfigurationReader from a user standpoint.
- ThreeTriosView: renders the game into a textual view and graphical view. 
    -  ThreeTriosView: textual view of the game.
    -  ThreeTriosGUIView: interface for the graphical view of the game.
        - ThreeTriosGameGUIView: class for the graphical view methods.
    -  ThreeTriosPanel: panel interface.
        - CardPanel: Contains one card which can be clicked to print its player and its index in the player's hand. Clicked card will also be highlighted.
        - GridPanel: Contains cells which can be clicked. Prints the clicked cell's row and col.
- ThreeTrios: Main method to instantiate a model and view.

Source Organization:
- Src
    - Controller
        - (Controller related code, including the interfaces for other compenent's interactions with the controller)
    - Model
        - (ThreeTriosModel and related code)
    - View
        - (Both textual and graphical view and related code)
    - Configurations
        - (Configuration files)
    - ThreeTrios
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
    - Created a GUiView interface and class (extends JFrame).
    - Created a panel interface and two panel classes for the hands and the grid.
    - Created a ThreeTrios file with a main method.
    - Created a getMutableCopy method in the model for more advanced strategies to be able to simulate moves in advace.

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

Changes for Part 3:
- New Features for HW7:
    - Model:
        - A new ModelFeatures interface was created, to doument every feature a model may need to be used by a controller. Models interfaces now extend this interface.
            - Specifically, a Model should be able to add controllers as listeners and publish to them, listen for moves, and start a game by updating the initial controller.                 We added all of these features.
    - View:
        - We updated the view to better interact with view features, be able to handel multiple indepnedant instances at once, and to rely more on being controlled by a view             features and controller rather than handeling all processies itself.

Changes for Part 4:
- New features for HW8:
    - We created ModelToProviderAdapter to adapt our model to our proivder's interface so that their view could use it
        - To support this, we also created adapters for each of our internal representations that needed to be adapted – namely the grid, cards, cells (of various types),                 player colors
    - We created a ProivderController that acts both as a Features object for the provider's view and as a PlayerController for interfacing with our code.
    - We added getNumOwnedCards to our model Interface to implement a similar feature in the proivder's interface. Prevously, this was a private method, but upon further             reflection it makes sense for the user to have access to it.
    - We added a getPlayer observation method to our PlayerController interface, as it was useful and it makes sense for a user to have access to it.
- Changes for HW8:
    - Changes for customers:
        - We had an unused makeMove(Move) method in our model interface that exposed our Move class to clients when it wasn't nessicary. This caused our customers trouble, so             we removed it.
        - We unintentionally had our view implementation take in a ReadOnlyThreeTriosGameModel implementation class instead of a ReadOnlyThreeTriosModel interface class. We                 fixed this.
    - We updated our main to use the proivder's view instead of our old view.
        - NOTE: While all previous command line arguments should compile, Blue players do not actually use strategies properly, as it was not a requiremnt of the assignment             to get them to.

What we were able to get working:
We were able to get almost every feature of the assignment fully functional. However, our model adapter’s addFeatures method             does not actually work with every Features implementation. Instead, it only supports Features that are also                                 PlayerControllers. Alternative designs would require either changing several parts of our model implementation or making                    several assumptions on when and how a model would choose to call its controllers. As there was a drawback to every design, we               chose to only allow adaptation with compatible controller objects, which was the simplest solution to implement.                            Additionally, we were unable to get our strategies to work with the provider’s view. While this should be possible, it was                  explicitly not a requirement for this assignment, and thus we decided to focus more effort elsewhere.

Extra Credit HW 9:

Level 0:

A GridDecorator class that implements ThreeTriosPanel was made. A HintDecorator class that extends GridDecorator has also made. The hints are enabled if a player's screen is displayed and they click "h" or "H". This does not enable hints for the other player, and the one player cannot see the other player's hints. To disable hints, type "h" or "H" again. The rendering code was not changed, but the decorator was to the ThreeTriosGameGUIView by wrapping the gridPanel with the HintDecorator. 

Level 1:

All changes were made in the model folder and corresponding test folder, except for the command line arguments in main.
A new interface was made: ThreeTriosBattleComparison. This is a type of strategy for comparing different ThreeTriosAttackValues, with one method compare() that takes in two ThreeTriosAttackValues ad returns true if the first value wins. We refactored our SimpleRules implementation (our representation of the battle rules of the model) to take in an object of this type on construction and use that strategy to compare battling cards. We implemented three strategies:
- SimpleBattleComparison, which follows the same logic as the original SimpleRules: an attacker wins only if it's attack value is higher than the opponent's
- ReverseBattleComparision, which follows the Reverse logic from this assignment. This is a decorator, and relies on another ThreeTriosBattleComparison to delegate to.
- FallenAceBattleComparison, which follows the Fallen Ace logic from this assignment. This is a decorator, and relies on another ThreeTriosBattleComparison to delegate to.
All strategies were tested in TestBattleComparison in the model tests folder. Command line arguments were added to allow users to select which decorations they want to add to the base SimpleBattleComparison.

Level 2:

All changes were made in the model folder and corresponding test folder, except for the command line arguments in main.
Two new implementations of ThreeTriosBattleRules were made:

- SameBattleRules, whcih follows the Same logic from this assignment.
- PlusBattleRules, which follows the Plus logic from this assignment.

Both of these implmentations were made to use the comparision strategies from level 1 for normal comparisons outside of the special initial flip.
Each of these implmentations have their own test class in the model tests folder. Command line arguments were added to allow the user to selct which ruleset to use.

Level 3:

Levels 1 and 2 were designed to work together, so nothing was needed for this level.


Changes for HW 9:

The full list of changes for HW 9 were as follows:
- Added ThreeTriosComparisionStrategy and all implmentations (SimpleBattleComparison, ReverseBattleComparision, FallenAceBattleComparison)
- Changed SimpleRules to take in a ThreeTriosComparisionStrategy
- Added PlusBattleRules and SameBattleRules
- Changed code in main to allow command line arguments for choosing rulesets.
- Added tests for all new rules implmentations in model tests
- Created a GridDecorator abstract class that decorates our view's grid representation (which displays the grid)
- Created a HintDecorator class that extends GridDecorator and covers all functionality from Level 0.
- Altered ThreeTriosGameGUIView to use HintDecorator.
- Added functionality to PlayerActionEvents interface and all implmentations.
- Added functionality (handelCardSelection specific updating method) to ThreeTriosGUIView interface and implmentation.
- Altered ViewFeatures to use the new handelCardSelection method in ThreeTriosGUIView.
