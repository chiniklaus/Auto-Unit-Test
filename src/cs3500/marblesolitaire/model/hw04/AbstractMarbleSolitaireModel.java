package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Spot;
import cs3500.marblesolitaire.model.hw02.SpotType;

/**
 * An abstract class that represent a Marble Solitaire game model.
 *
 * <p>There are two fields, a two dimensional array of Spots that represent the game grid, and a int
 * that represents the dimension of the game board</p>
 * <p>There is one constructor in the class that takes in an int of row number, an int of column
 * number, and an int of dimension.</p>
 * <p>the move method update the game board according to the game rule and the input instruction</p>
 * <p>the isGameOver method returns a boolean of if the game is over according to the game rule</p>
 * <p>the getGameState method print the current game state as a String</p>
 * <p>the getScore returns an int of number of marbles left on the game board, as the score</p>
 */
public class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {
  protected Spot[][] gameBoard;
  protected int dimension;

  /**
   * A constructor of the abstract class. takes in two ints as the position of the empty spot, and
   * one int as the dimension of the game. The constructor checks if the dimension is valid, if the
   * position is out of the grid or on the border. Throws exception accordingly. If all the inputs
   * are valid, create the two dimensional game board.
   * @param sRow the row number of the position of the empty spot.
   * @param sCol the column number of the position of the empty spot.
   * @param dimension the dimension of the game board.
   */
  public AbstractMarbleSolitaireModel(int sRow, int sCol, int dimension) {
    //check if the dimension is valid.(not less than 1.)
    if (dimension < 1) {
      throw new IllegalArgumentException("dimension is invalid (non-positive).");
    }

    //check if the given position is valid for the game.(in the gird)
    if (outOfGridCheck(sRow,sCol,dimension)) {
      throw new IllegalArgumentException("position is out of the grid.");
    }

    //check if the position is on the border spot
    if (outOfBorderCheck(sRow,sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    Spot[][] gameBoard = new Spot[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (outOfBorderCheck(i,j)) {
          gameBoard[i][j] = new Spot(SpotType.border);
        }
        else if (i == sRow && j == sCol) {
          gameBoard[i][j] = new Spot(SpotType.space);
        }
        else {
          gameBoard[i][j] = new Spot(SpotType.marble);
        }
      }
    }
    this.gameBoard = gameBoard;
    this.dimension = dimension;
  }

  //a method that checks if the given position is on a border spot
  protected boolean outOfBorderCheck(int i,int j) {
    return outOfGridCheck(i,j,this.dimension);
  }

  //a method that checks if the given position is out of the grid
  protected boolean outOfGridCheck(int i, int j, int dimension) {
    return i < 0 || i > dimension - 1 || j < 0 || j > dimension - 1;
  }

  //this method set the moving rule that the from and to position have to be two position away.
  //check if move a marble from fromRow, fromCol to toRow, toCol is permitted by the game rule.
  //if not, throw illegalArgumentException.
  protected void moveLogicNotValid(int fromRow, int fromCol, int toRow, int toCol) {
    if ( ! (((fromRow == toRow && Math.abs(fromCol - toCol) == 2))
            || (fromCol == toCol && Math.abs(fromRow - toRow) == 2))) {
      throw new IllegalArgumentException("not two positions away.");
    }
  }

  //a method that checks some of the universal game rule that apply for all game:
  //from position need to be on the board and there is a marble on it.
  //to position need to be on the board and empty.
  //there need to be a marble in between the from and to position.
  protected void moveThrowExceptions(int fromRow, int fromCol, int toRow, int toCol) {
    if (outOfBorderCheck(fromRow,fromCol) || outOfGridCheck(fromRow,fromCol,dimension)) {
      throw new IllegalArgumentException("from position invalid.");
    }

    else if (outOfBorderCheck(toRow,toCol) || outOfGridCheck(toRow,toCol,dimension)) {
      throw new IllegalArgumentException("to position invalid.");
    }

    else if (! gameBoard[fromRow][fromCol].equalType(SpotType.marble)) {
      throw new IllegalArgumentException("from position doesn't have marble.");
    }

    else if (! gameBoard[toRow][toCol].equalType(SpotType.space)) {
      throw new IllegalArgumentException("to position is not empty.");
    }

    else if (! gameBoard[(fromRow + toRow) / 2][(fromCol + toCol) / 2].equalType(SpotType.marble)) {
      throw new IllegalArgumentException("there is not a marble in the slot between "
              + "the “to” and “from” positions.");
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    moveThrowExceptions(fromRow,fromCol,toRow,toCol);
    moveLogicNotValid(fromRow,fromCol,toRow,toCol);

    gameBoard[fromRow][fromCol].changeType(SpotType.space);
    gameBoard[toRow][toCol].changeType(SpotType.marble);
    gameBoard[(fromRow + toRow) / 2][(fromCol + toCol) / 2].changeType(SpotType.space);
  }

  //when the rules are the marble can only move orthogonally, check if game is over.
  @Override
  public boolean isGameOver() {
    boolean result = true;

    //for each space on the gameBoard, if there are two marbles right next to it on the same side,
    //means game is not over.
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (gameBoard[i][j].equalType(SpotType.space)) {
          //check if there are two marbles on the left side of the space
          if (i > 1 && ((gameBoard[i - 1][j].equalType(SpotType.marble))
                  && (gameBoard[i - 2][j].equalType(SpotType.marble)))) {
            result = false;
          }
          //check if there are two marbles on the right side of the space
          else if (i < (dimension - 2) && ((gameBoard[i + 1][j].equalType(SpotType.marble))
                  && (gameBoard[i + 2][j].equalType(SpotType.marble)))) {
            result = false;
          }
          //check if there are two marbles on the down side of the space
          else if (j > 1 && ((gameBoard[i][j - 1].equalType(SpotType.marble))
                  && (gameBoard[i][j - 2].equalType(SpotType.marble)))) {
            result = false;
          }
          //check if there are two marbles on the up side of the space
          else if (j < (dimension - 2) && ((gameBoard[i][j + 1].equalType(SpotType.marble))
                  && (gameBoard[i][j + 2].equalType(SpotType.marble)))) {
            result = false;
          }
        }
      }
    }
    return result;
  }

  //a helper method to print a row of the gameBoard. Variable b is the StringBuilder,
  //i is the row number to print, c is the column number.
  protected void printRow(StringBuilder b, int i, int c,Spot[][] board) {
    for (int k = 0; k < (c + 1); k++) {
      if (k == c) {
        b.append(board[i][k].print());
      }
      else {
        b.append(board[i][k].print());
        b.append(" ");
      }
    }
  }

  @Override
  public String getGameState() {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < dimension; i++) {
      StringBuilder row = new StringBuilder();
      printRow(row,i,dimension - 1,gameBoard);

      if (i == dimension - 1) {
        result.append(row);
      }
      else {
        result.append(row);
        result.append("\n");
      }
    }
    return result.toString();
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (gameBoard[i][j].equalType(SpotType.marble)) {
          score = score + 1;
        }
      }
    }
    return score;
  }
}