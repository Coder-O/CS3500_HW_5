package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosPlayer;
import cs3500.threetrios.provider.controller.PlayerColor;

/**
 * Adapts a {@link cs3500.threetrios.model.ThreeTriosPlayer} into a
 * {@link cs3500.threetrios.provider.controller.Player}.
 */
public class PlayerColorAdapter implements cs3500.threetrios.provider.controller.Player {
  private final ThreeTriosPlayer player;
  // INVARIANT: player is not null.

  /**
   * Adapts a {@link cs3500.threetrios.model.ThreeTriosPlayer} into a
   * {@link cs3500.threetrios.provider.controller.Player}.
   * @param player The three trios player to adapt.
   * @throws IllegalArgumentException If player is null.
   */
  public PlayerColorAdapter(ThreeTriosPlayer player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player may not be null!");
    }
    this.player = player;
  }

  /**
   * This gets the color of this player.
   *
   * @return this players color.
   */
  @Override
  public PlayerColor getColor() {
    switch (player) {
      case RED:
        return PlayerColor.R;
      case BLUE:
        return PlayerColor.B;
    }
    throw new IllegalStateException(
            "A ThreeTriosPlayer enum object did not have an expected value... how?!"
    );
  }
}
