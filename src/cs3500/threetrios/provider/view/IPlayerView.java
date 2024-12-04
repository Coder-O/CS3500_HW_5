package cs3500.threetrios.provider.view;


import cs3500.threetrios.provider.controller.Features;

public interface IPlayerView extends IThreeTriosViewGUI {

  void display(boolean show);


  void addFeaturesListener(Features feature);


  void error(String errorMessage);


  void refresh();


  void resetHighlight();


  void blurScreen(boolean blur);


  void displayEndGame(String winner);


  void setEnabled(boolean enabled);


}
