package cs3500.ThreeTrios.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.ThreeTrios.model.ThreeTriosModel;
import cs3500.ThreeTrios.model.Card;

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
     */
    @Override
    public String toString() {
        StringBuilder gameState = new StringBuilder();

        //display the Player
        String currentPlayer = model.getCurrentPlayer().toString();
        gameState.append("Player: ").append(currentPlayer).append("\n");
        
        //display the grid
        String grid = model.getGrid().toString();
        gameState.append(grid).append("\n");

        //display the hand
        ArrayList<Card> hand = model.getHand(model.getCurrentPlayer());
        gameState.append("Hand:\n");
        for (Card card : hand) {
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
