package cs3500.threetrios.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for testng player implementations.
 */
public class AbstractPlayerTest {
  protected Appendable appendable;
  protected PlayerActions player;
  @Test
  public void testUpdatesAllControllers() {

    // Set up
    List<PlayerController> controllers = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      controllers.add(new MockPlayerController(appendable));
    }

    for (PlayerController controller : controllers) {
      player.addControllerListener(controller);
    }


  }
}
