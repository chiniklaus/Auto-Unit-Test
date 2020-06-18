package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;

/**
 * an implementation of the interface MarbleSolitaireModel, represents the game model.
 * the class has two constant, first one is a double array of Spots, the second one is an int,
 * which represents dimension of the game board. There are Two constructors, one take
 * no param, and locate the empty spot in the middle of the game board. The other takes in
 * two ints, and locate the empty spot according to the params. The move method move the Spots
 * and change the game board. The isGameOver method checks if game is over. The get game state
 * method returns a String of the game state.
 */
public class MarbleSolitaireModelImpl extends AbstractMarbleSolitaireModel {

  //change: remove the fields in this model, because we are going to use the fields
  //from the abstract class.

  /**
   * constructor that creates the empty slot at the center.
   * change the constructor to call the abstract constructor.
   */
  public MarbleSolitaireModelImpl() {
    super(3,3,7);
  }

  /**
   * constructor that creates the empty slot at the required place.
   * change the constructor to call the abstract constructor.
   * @param sRow x position.
   * @param sCol y position.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
      super(sRow,sCol,7);
  }

  @Override
  //return true if the given position is in the border spot.
  protected boolean outOfBorderCheck(int i,int j) {
    return ((i <= 1 || i >= 5) && (j <= 1 || j >= 5));
  }

  @Override
  public String getGameState() {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < 7; i++) {
      StringBuilder row = new StringBuilder();
      if (i == 0 || i == 1 || i == 5 || i == 6) {
        printRow(row,i,4,gameBoard);
      }
      else {
        printRow(row,i,6,gameBoard);
      }

      if (i == 6) {
        result.append(row);
      }
      else {
        result.append(row);
        result.append("\n");
      }
    }
    return result.toString();
  }

  //for the move, getScore, isGameOver method, I put them all in the abstract class.
}