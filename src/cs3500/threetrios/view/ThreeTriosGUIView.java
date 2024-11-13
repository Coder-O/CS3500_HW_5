package cs3500.threetrios.view;
import javax.swing.JComponent;

/**
 * Represents the GUI view for the ThreeTrios Game.
 */
public interface ThreeTriosGUIView {

  /**
   * Updates the view based on the model's current state.
   */
  void refresh();

  /**
   * Displays the game window.
   */
  void makeVisible();

}
