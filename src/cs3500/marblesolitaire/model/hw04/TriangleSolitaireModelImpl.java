package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.SpotType;


/**
 * An implementation of the interface MarbleSolitaireModel, extension of the
 * AbstractMarbleSolitaireModel. Represents a triangle game model.
 * <p>There are four constructors:
 * 1.A default constructor that creates a 5-row game with the empty slot at (0,0).
 * 2.A constructor with a single parameter (dimensions) that creates a game with the specified
 * dimension and the empty slot at (0,0).
 * 3.A constructor with a two parameters (row,col) that creates a 5-row game with the empty slot at
 * the specified position.
 * 4.A constructor with three parameters (dimensions,row,col) that creates a game with the specified
 * dimension and an empty slot at the specified row and column.</p>
 * <p>The move method move the Spots and change the game board.</p>
 * <p>The isGameOver method checks if game is over.</p>
 * <p>The getGameState method returns a String of the game state.</p>
 * <p>The getScore method returns the number of marble left on the board at the score.</p>
 */
public class TriangleSolitaireModelImpl extends AbstractMarbleSolitaireModel {

  /**
   * A default constructor (no parameters) that creates a 5-row game with the empty slot at (0,0).
   */
  public TriangleSolitaireModelImpl() {
    super(0,0,5);
  }

  /**
   * A constructor with a single parameter (dimensions) that creates a game with the specified
   * dimension (number of slots in the bottom-most row) and the empty slot at (0,0).
   * This constructor throw IllegalArgumentException if the specified dimension is invalid.
   * @param dimension the number of spots on the bottom most row.
   */
  public TriangleSolitaireModelImpl(int dimension) {
    super(0,0,dimension);
  }

  /**
   * A constructor with a two parameters (row,col) that creates a 5-row game with the empty slot at
   * the specified position. This constructor throw IllegalArgumentException exception if the
   * specified position is invalid.
   * @param row row number of the empty spot.
   * @param col column number of the empty spot.
   */
  public TriangleSolitaireModelImpl(int row, int col) {
    super(row,col,5);
  }

  /**
   * A constructor with three parameters (dimensions,row,col) that creates a game with the specified
   * dimension and an empty slot at the specified row and column. This constructor should throw
   * IllegalArgumentException if the specified dimension is invalid (non-positive) or the specified
   * position is invalid.
   * @param dimension the number of spots on the bottom most row.
   * @param row row number of the empty spot.
   * @param col column number of the empty spot.
   */
  public TriangleSolitaireModelImpl(int dimension, int row, int col) {
    super(row,col,dimension);
  }


  @Override
  protected boolean outOfBorderCheck(int i,int j) {
    return j > i;
  }

  //check if move a marble from fromRow, fromCol to toRow, toCol is permitted by the game rule,
  //which is the marble can move towards up, down, left, right, left up, right down, six directions,
  //and the from and to position need to be two position away.
  @Override
  protected void moveLogicNotValid(int fromRow, int fromCol, int toRow, int toCol) {
    if (! ((((fromRow == toRow && Math.abs(fromCol - toCol) == 2))
            || (fromCol == toCol && Math.abs(fromRow - toRow) == 2))
            || (((fromRow - toRow) * (fromCol - toCol) > 0)
              && (Math.abs(fromRow - toRow) == 2) && (Math.abs(fromCol - toCol) == 2)))) {
      throw new IllegalArgumentException("not two positions away.");
    }
  }

  //check for every space left on the board, if there are two marbles on its left up and down right
  //directions. If there are not such space left on the board, and by orthogonally logic the game is
  //over too, then return true.
  @Override
  public boolean isGameOver() {
    boolean result = true;

    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (gameBoard[i][j].equalType(SpotType.space)) {
          if (i > 1 && j > 1 && gameBoard[i - 1][j - 1].equalType(SpotType.marble)
                  && gameBoard[i - 2][j - 2].equalType(SpotType.marble)) {
            result = false;
          }
          if (i < dimension - 2 && gameBoard[i + 1][j + 1].equalType(SpotType.marble)
                  && gameBoard[i + 2][j + 2].equalType(SpotType.marble)) {
            result = false;
          }
        }
      }
    }

    return super.isGameOver() && result;
  }

  //a private method for this model, that prints the spaces before the game board for each row.
  private String printStringUpFront(int i) {
    StringBuilder s = new StringBuilder();
    while (i > 0) {
      s.append(" ");
      i -= 1;
    }
    return s.toString();
  }

  @Override
  public String getGameState() {
    StringBuilder result = new StringBuilder();
    int spaceUpFront = dimension - 1;

    for (int i = 0;i < dimension; i++) {
      StringBuilder row = new StringBuilder();
      row.append(printStringUpFront(spaceUpFront));
      printRow(row,i,i,gameBoard);
      spaceUpFront -= 1;

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
}