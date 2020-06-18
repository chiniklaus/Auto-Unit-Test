package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * an interface to represent the controller of the game. There is one
 * method in the interface, playGame, which takes in a MarbleSolitaireModel.
 * the implementation of this interface will override this method and update
 * the model accordingly.
 */
public interface MarbleSolitaireController {

  /**
   * play a new game of Marble Solitaire using the provided model.
   * update the model accordingly.
   * @param model the provided model.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}
