package cs3500.threetrios.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosCard;

/**
 * A textual view of the ThreeTrios Game.
 */
public class ThreeTriosView {
    private final ThreeTriosModel model;
    private final Appendable out;

    /**
     * Constructor for ThreeTriosView.
     * @param model the game model
     * @throws IllegalArgumentException if model is null
     */
    public ThreeTriosView(ThreeTriosModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null.");
        }
        this.model = model;
        this.out = null;
    }
    
    /**
     * Constructor for ThreeTriosView.
     * @param model the game model
     * @throws IllegalArgumentException if model is null
     * @throws IllegalArgumentException if Appendable is null
     */
    public ThreeTriosView(ThreeTriosModel model, Appendable out) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null.");
        }
        this.model = model;
        this.out = out;
        if (this.out == null) {
            throw new IllegalArgumentException("Appendable cannot be null.");
        }
    }

    /**
     * Displays the Game.
     * Example:
     * Player: BLUE
     * BB   _
     * _B   _
     * _ R  _
     * _  _ _
     * _   _R
     * Hand:
     * CorruptKing 7 3 9 A
     * AngryDragon 2 8 9 9
     * WindBird 7 2 5 3
     * HeroKnight A 2 4 4
     * WorldDragon 8 3 5 7
     * SkyWhale 4 5 9 9
     */
    @Override
    public String toString() {
        StringBuilder gameState = new StringBuilder();

        //display the Player
        String currentPlayer = model.getCurrentPlayer().getSymbol();
        gameState.append("Player: ").append(currentPlayer).append("\n");
        
        //display the grid
        String grid = model.getGrid().toString();
        gameState.append(grid).append("\n");

        //display the current player's hand
        ArrayList<ThreeTriosCard> hand = model.getHand(model.getCurrentPlayer());
        gameState.append("Hand:\n");
        for (ThreeTriosCard card : hand) {
            gameState.append(card.toString()).append("\n");
        }
        return gameState.toString();
    }

/**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IllegalArgumentException if Appendable is null
   * @throws IOException if the rendering fails for some reason
   */
  public void render() throws IOException {
    if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }
    out.append(this.toString());
  }
}
