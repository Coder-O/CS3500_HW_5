package cs3500.threetrios.controller;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;

/**
 * A general purpose "view" that can be told a model state.
 */
public interface ViewFeatures {
  /**
   * Updates the view with the new model
   * @param model
   */
  void updateModel(ReadOnlyThreeTriosModel model);
}
