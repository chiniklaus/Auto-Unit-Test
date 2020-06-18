import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriangleSolitaireModelImplTest {

  @Test
  public void testDefaultConstructor() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    assertEquals("    X\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInDimensionWorksOne() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2);
    assertEquals(" X\n" +
            "O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInDimensionWorksTow() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(1);
    assertEquals("X",m.getGameState());
  }

  @Test
  public void testConstructorTakeInDimensionWorksThree() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(4);
    assertEquals("   X\n" +
            "  O O\n" +
            " O O O\n" +
            "O O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInDimensionFailsOne() {
    //the dimension is less than 1 but not negative.
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(0);
    } catch (IllegalArgumentException e) {
      assertEquals("dimension is invalid (non-positive).",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInDimensionFailsTwo() {
    //the dimension is negative.
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(-2);
    } catch (IllegalArgumentException e) {
      assertEquals("dimension is invalid (non-positive).",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionWorksOne() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(1,1);
    assertEquals("    O\n" +
            "   O X\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInPositionWorksTwo() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3,2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O X O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInPositionFailsOne() {
    //the given position is in the border.
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3,4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,4)",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionFailsTwo() {
    //the given position is out of the grid. row more than 4
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,4);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionFailsThree() {
    //the given position is out of the grid. row less than 0
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(-1,4);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionFailsFour() {
    //the given position is out of the grid. column less than 0
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(4,-1);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionFailsFive() {
    //the given position is out of the grid. column more than 4
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3,5);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionWorksOne() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3,1,1);
    assertEquals("  O\n" +
            " O X\n" +
            "O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionWorksTwo() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(6,4,3);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O X O\n" +
            "O O O O O O",m.getGameState());
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsOne() {
    //dimension less than 1 but not negative
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(0,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("dimension is invalid (non-positive).",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsTwo() {
    //dimension less than 1 and negative
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(-2,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("dimension is invalid (non-positive).",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsThree() {
    //dimension is fine, position on border
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,3,4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,4)",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsFour() {
    //dimension is fine, row less than 0
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,-2,4);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsFive() {
    //dimension is fine, row more than dimension - 1
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,5,4);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsSix() {
    //dimension is fine, column more than dimension - 1
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,4,5);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testConstructorTakeInPositionAndDimensionFailsSeven() {
    //dimension is fine, column less than 0
    try {
      MarbleSolitaireModel m = new TriangleSolitaireModelImpl(5,4,-1);
    } catch (IllegalArgumentException e) {
      assertEquals("position is out of the grid.",e.getMessage());
    }
  }

  @Test
  public void testMoveUpLeftDown() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2,0);
    m.move(0,0,2,0);
    assertEquals("    X\n" +
            "   X O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveRightUp() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    m.move(2,0,0,0);
    assertEquals("    O\n" +
            "   X O\n" +
            "  X O O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveLeftToRight() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2,2);
    m.move(2,0,2,2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  X X O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveRightToLeft() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2,0);
    m.move(2,2,2,0);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O X X\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveLeftUp() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    m.move(2,2,0,0);
    assertEquals("    O\n" +
            "   O X\n" +
            "  O O X\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveRightDown() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2,2);
    m.move(0,0,2,2);
    assertEquals("    X\n" +
            "   O X\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O",m.getGameState());
  }

  @Test
  public void testMoveFromPositionOnBorder() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    try {
      m.move(2,3,0,1);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.",e.getMessage());
    }
  }

  @Test
  public void testMoveFromPositionOutOfGrid() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    try {
      m.move(-1,3,0,1);
    } catch (IllegalArgumentException e) {
      assertEquals("from position invalid.",e.getMessage());
    }
  }

  @Test
  public void testMoveToPositionOnBorder() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    try {
      m.move(1,0,1,2);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.",e.getMessage());
    }
  }

  @Test
  public void testMoveToPositionOutOfGrid() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    try {
      m.move(1,0,-2,0);
    } catch (IllegalArgumentException e) {
      assertEquals("to position invalid.",e.getMessage());
    }
  }

  @Test
  public void testMoveTwoPositionAwayOnTheGirdButNotOnTheBoardRightUpDirection() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(7,3,3);
    try {
      m.move(5,1,3,3);
    } catch (IllegalArgumentException e) {
      assertEquals("not two positions away.",e.getMessage());
    }
  }

  @Test
  public void testMoveTwoPositionAwayOnTheGirdButNotOnTheBoardLeftDownDirection() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(7,5,1);
    try {
      m.move(3,3,5,1);
    } catch (IllegalArgumentException e) {
      assertEquals("not two positions away.",e.getMessage());
    }
  }

  @Test
  public void testMoveFromPositionNoMarble() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(2,0);
    try {
      m.move(2,0,0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("from position doesn't have marble.",e.getMessage());
    }
  }

  @Test
  public void testMoveToPositionNotEmpty() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3,3);
    try {
      m.move(2,0,0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("to position is not empty.",e.getMessage());
    }
  }

  @Test
  public void testNoMarbleBetweenFromAndToPosition() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    m.move(2,0,0,0);
    try {
      m.move(0,0,2,0);
    } catch (IllegalArgumentException e) {
      assertEquals("there is not a marble in the slot between " +
              "the “to” and “from” positions.",e.getMessage());
    }
  }

  @Test
  public void testJustNotTwoPositionsAway() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    try {
      m.move(3,0,0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("not two positions away.",e.getMessage());
    }
  }

  @Test
  public void testIsGameOverFalse() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    assertFalse(m.isGameOver());
  }

  @Test
  public void testIsGameOverTrue() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3);
    m.move(2,0,0,0);
    m.move(2,2,2,0);
    m.move(0,0,2,2);
    assertTrue(m.isGameOver());
  }

  @Test
  public void testGetGameState() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(6,5,2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O X O O O",m.getGameState());
  }

  @Test
  public void testGetScoreOne() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl();
    assertEquals(14,m.getScore());
  }

  @Test
  public void testGetScoreTwo() {
    MarbleSolitaireModel m = new TriangleSolitaireModelImpl(3);
    m.move(2,0,0,0);
    m.move(2,2,2,0);
    m.move(0,0,2,2);
    assertEquals(2,m.getScore());
  }
}