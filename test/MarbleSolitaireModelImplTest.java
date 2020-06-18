import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

public class MarbleSolitaireModelImplTest {

  private MarbleSolitaireModel m1;

  @Before
  public void variablesForTests() {
    m1 = new MarbleSolitaireModelImpl();
  }

  @Test
  public void testConstructorThatTakesInNothing() {

    //test the default constructor
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",m1.getGameState());
  }

  @Test
  public void testConstructorThatTakesInTwoIntsWorks() {

    MarbleSolitaireModel m2 = new MarbleSolitaireModelImpl(4,5);
    //test the constructor that let the user choose the empty space
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O X O\n"
            + "    O O O\n"
            + "    O O O",m2.getGameState());
  }

  @Test
  public void testConstructorThatTakesInTwoIntsFails() throws IllegalArgumentException {
    //throw exception when user choose an invalid empty space
    try {
      MarbleSolitaireModel m3 = new MarbleSolitaireModelImpl(40,59);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (40,59)", e.getMessage());
    }
  }

  @Test
  public void testGetGameState() {
    m1.move(5,3,3,3);
    m1.move(4,5,4,3);
    m1.move(6,4,4,4);
    m1.move(6,2,6,4);

    MarbleSolitaireModel m2 = new MarbleSolitaireModelImpl(2,5);
    m2.move(2,3,2,5);
    m2.move(2,1,2,3);

    //game state for m1
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O X O\n"
            + "    O X X\n"
            + "    X X O",m1.getGameState());

    //game state for m2
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O X X O X O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",m2.getGameState());
  }

  @Test
  public void moveTestValidLeftToRight() {
    //before the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());

    m1.move(3, 1, 3, 3);

    //after the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O X X O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());
  }

  @Test
  public void moveTestValidRightToLeft() {
    //before the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());

    m1.move(3, 5, 3, 3);

    //after the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O X X O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());
  }

  @Test
  public void moveTestValidTopToBottom() {
    //before the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());

    m1.move(1, 3, 3, 3);

    //after the move
    assertEquals("    O O O\n"
            + "    O X O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());
  }

  @Test
  public void moveTestValidBottomToTop() {
    //before the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", m1.getGameState());

    m1.move(5, 3, 3, 3);

    //after the move
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O X O O O\n"
            + "    O X O\n"
            + "    O O O", m1.getGameState());
  }

  @Test
  public void moveTestInvalid() throws IllegalArgumentException {
    //move invalid(across rows and columns but two positions away)
    try {
      m1.move(3, 1, 3, 3);
      m1.move(1, 4, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("not two positions away.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidLeftTopBorder() throws IllegalArgumentException {

    //from position in the 'border' spot
    try {
      m1.move(0, 0, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidRightTopBorder() throws IllegalArgumentException {
    try {
      m1.move(0, 6, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidLeftBottomBorder() throws IllegalArgumentException {
    try {
      m1.move(6, 0, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidRightBottomBorder() throws IllegalArgumentException {
    try {
      m1.move(6, 6, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridLeftTop() throws IllegalArgumentException {
    try {
      m1.move(-1, -1, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridRightTop() throws IllegalArgumentException {
    try {
      m1.move(-1, 7, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridLeftBottom() throws IllegalArgumentException {
    try {
      m1.move(7, -1, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridRightBottom() throws IllegalArgumentException {
    try {
      m1.move(7, 7, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridTop() throws IllegalArgumentException {
    try {
      m1.move(-1, 3, 1, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridLeft() throws IllegalArgumentException {
    try {
      m1.move(3, -1, 3, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridRight() throws IllegalArgumentException {
    try {
      m1.move(3, 7, 3, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestFromPositionInvalidOutOfGridBottom() throws IllegalArgumentException {
    try {
      m1.move(7,3,3,1);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidLeftTopBorder() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidRightTopBorder() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 0, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidLeftBottomBorder() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 6, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidRightBottomBorder() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridLeftTop() throws IllegalArgumentException {
    try {
      m1.move(3, 3, -1, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridRightTop() throws IllegalArgumentException {
    try {
      m1.move(3, 3, -1, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridLeftBottom() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 7, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridRightBottom() throws IllegalArgumentException {
    try {
      m1.move(3, 3, 7, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridTop() throws IllegalArgumentException {
    try {
      m1.move(1, 3, -1, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridLeft() throws IllegalArgumentException {
    try {
      m1.move(3, 1, 3, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridRight() throws IllegalArgumentException {
    try {
      m1.move(3, 1, 3, 7);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionInvalidOutOfGridBottom() throws IllegalArgumentException {
    try {
      m1.move(3, 1, 7, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.", e.getMessage());
    }
  }

  @Test
  public void moveTestNoMarbleAtFromPosition() throws IllegalArgumentException {
    //there is not a marble at the from position test
    try {
      m1.move(3, 3, 3, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("from position doesn't have marble.", e.getMessage());
    }
  }

  @Test
  public void moveTestToPositionIsNotEmpty() throws IllegalArgumentException {
    //to position is not empty test
    try {
      m1.move(3, 4, 3, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("to position is not empty.", e.getMessage());
    }
  }

  @Test
  public void moveTestNotTwoPositionAway() throws IllegalArgumentException {
    //from position and to position are not two positions away
    try {
      m1.move(0, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("not two positions away.", e.getMessage());
    }
  }

  @Test
  public void moveTestNoMarbleInBetween() throws IllegalArgumentException {
    m1.move(3, 1, 3, 3);

    //no marble in between the move
    try {
      m1.move(3,3,3,1);
    } catch (IllegalArgumentException e) {
      assertEquals("there is not a marble in the slot between "
              + "the “to” and “from” positions.", e.getMessage());
    }
  }

  @Test
  public void testIsGameOverNotOver() {
    //game not over test
    assertFalse(m1.isGameOver());
  }

  @Test
  public void testIsGameOverIsOver() {
    //game over test
    m1.move(5,3,3,3);
    m1.move(4,5,4,3);
    m1.move(6,4,4,4);
    m1.move(6,2,6,4);
    m1.move(3,4,5,4);
    m1.move(6,4,4,4);
    m1.move(1,4,3,4);
    m1.move(2,6,2,4);
    m1.move(4,6,2,6);
    m1.move(2,3,2,5);
    m1.move(2,6,2,4);
    m1.move(2,1,2,3);
    m1.move(0,2,2,2);
    m1.move(3,2,1,2);
    m1.move(0,4,0,2);
    m1.move(0,2,2,2);
    m1.move(5,2,3,2);
    m1.move(4,0,4,2);
    m1.move(2,0,4,0);
    m1.move(4,3,4,1);
    m1.move(4,0,4,2);
    m1.move(2,3,2,5);
    m1.move(2,5,4,5);
    m1.move(4,5,4,3);
    m1.move(4,3,4,1);
    m1.move(4,1,2,1);
    m1.move(2,1,2,3);
    m1.move(3,3,3,5);
    m1.move(1,3,3,3);
    m1.move(3,2,3,4);
    m1.move(3,5,3,3);

    assertTrue(m1.isGameOver());
  }

  @Test
  public void testGameScore() {
    assertEquals(32,m1.getScore());

    m1.move(3,5,3,3);
    assertEquals(31,m1.getScore());

    MarbleSolitaireModel m3 = new MarbleSolitaireModelImpl();
    m3.move(5,3,3,3);
    m3.move(4,5,4,3);
    m3.move(6,4,4,4);
    m3.move(6,2,6,4);
    m3.move(3,4,5,4);
    m3.move(6,4,4,4);
    m3.move(1,4,3,4);
    m3.move(2,6,2,4);
    m3.move(4,6,2,6);
    m3.move(2,3,2,5);
    m3.move(2,6,2,4);
    m3.move(2,1,2,3);
    m3.move(0,2,2,2);
    m3.move(3,2,1,2);
    m3.move(0,4,0,2);
    m3.move(0,2,2,2);
    m3.move(5,2,3,2);
    m3.move(4,0,4,2);
    m3.move(2,0,4,0);
    m3.move(4,3,4,1);
    m3.move(4,0,4,2);
    m3.move(2,3,2,5);
    m3.move(2,5,4,5);
    m3.move(4,5,4,3);
    m3.move(4,3,4,1);
    m3.move(4,1,2,1);
    m3.move(2,1,2,3);
    m3.move(3,3,3,5);
    m3.move(1,3,3,3);
    m3.move(3,2,3,4);
    m3.move(3,5,3,3);
    assertEquals(1,m3.getScore());
  }
}