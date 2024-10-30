# CS3500_HW_5

This codebase is meant to represent a game of ThreeTrios. 

The following assumptions are made:
-There are only 2 players.
-Players will specify locations in hand/grid using integer coordinates.
-Game will track whose turn it is.

Quick Start: 
//todo: give a short snippet of code (rather like a simple JUnit test) showing how a user might get started using this codebase.

Key Components and Subcomponents:
-Controller: drives the control-flow of the game. To be developed.
-Model: is driven by the controller. Holds the rules and objects of the game.
    -Card: the cards of the game.
    -Direction: the directions a ThreeTriosCard can have neighbors in.
    -Player: player 1 and player 2.
    -AttackValue: values for the cards (1, 2, 3, 4, 5, 6, 7, 8, 9, A)
    -GridBuilder: initializes a grid.
    -Grid: the playing grid, has empty and hole cells.
    -Cell: a cell in the grid. It can be a hole or hold a card.
    -BattleRules: the rules that govern the ThreeTrios game.
-View: renders the game into a textual view. Graphic view to be developed.

Source Organization:
-Src
    -Controller
    -Model
    -View
-Test