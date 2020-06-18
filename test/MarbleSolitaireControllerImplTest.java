import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

public class MarbleSolitaireControllerImplTest {

  @Test
  public void testConstructorNullReadable() throws IllegalArgumentException {
    StringBuffer out = new StringBuffer();
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null,out);
    } catch (IllegalArgumentException e) {
      assertEquals("Readable is null.",e.getMessage());
    }
  }

  @Test
  public void testConstructorNullAppendable() throws IllegalArgumentException {
    Reader in = new StringReader("");
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,null);
    } catch (IllegalArgumentException e) {
      assertEquals("Appendable is null.",e.getMessage());
    }
  }

  @Test
  public void testPlayGameNullModel() throws IllegalArgumentException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);

    try {
      controller.playGame(null);
    } catch (IllegalArgumentException e) {
      assertEquals("model is null.",e.getMessage());
    }
  }

  @Test
  public void testAppendGameStateNScore() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    controller.playGame(model);


    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testGameQuitAfterValidMove1() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("6 4 4 4 5 6 5 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    controller.playGame(model);

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O X X O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O X X O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 30\n",out.toString());
  }

  @Test
  public void testGameQuitAfterValidMove2() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
    controller.playGame(model);

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "    O O O\n" +
            "    O X O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O X O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n",out.toString());
  }

  @Test
  public void testInputZeroValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input, while storing
    //the previous 0 valid inputs

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 0
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Unexpected input, please try again, starting from " +
            "the row number of the position from where a marble is to be moved.\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testInputTwoInValidNumber() {
    //this test made sure the program inform the player to input correct input, while storing
    //the previous 0 valid inputs

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("w w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 0
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Unexpected input, please try again, starting from " +
            "the row number of the position from where a marble is to be moved.\n" +
            "Enter from row: \n" +
            "Unexpected input, please try again, starting from " +
            "the row number of the position from where a marble is to be moved.\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testInputAValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input, while storing
    //the previous 1 valid inputs

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 1
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Unexpected input, please try again, starting from " +
            "the column number of the position from where a marble is to be moved.\n" +
            "Enter from column: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testInputTwoValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input, while storing
    //the previous 2 valid inputs

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 2 w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 2
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Unexpected input, please try again, starting from" +
            " the row number of the position to where a marble is to be moved.\n" +
            "Enter to row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testInputThreeValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input, while storing
    //the previous 3 valid inputs

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 2 3 w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 3
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "Unexpected input, please try again, starting from " +
            "the column number of the position to where a marble is to be moved.\n" +
            "Enter to column: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testInputFourValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input after
    //making a valid move

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("6 4 4 4 w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 0
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Enter from row: \n" +
            "Unexpected input, please try again, starting from " +
            "the row number of the position from where a marble is to be moved.\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 31\n",out.toString());
  }

  @Test
  public void testInputFiveValidNumberAndAInvalidNumber() {
    //this test made sure the program inform the player to input correct input after
    //making a valid move

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("6 4 4 4 3 w q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in,out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);

    //the message only appears when the input array has a size of 0
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Unexpected input, please try again, starting from " +
            "the column number of the position from where a marble is to be moved.\n" +
            "Enter from column: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "    O X O\n" +
            "    O O O\n" +
            "Score: 31\n",out.toString());
  }

  @Test
  public void testInvalidMove() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 2 2 2 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Enter from row: \n" +
            "Enter from column: \n" +
            "Enter to row: \n" +
            "Enter to column: \n" +
            "Invalid move. Play again. from position invalid.\n" +
            "Enter from row: \n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O X O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n",out.toString());
  }

  @Test
  public void testGameOver() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("6 4 4 4 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 " +
            "5 5 5 2 5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 3 2 3 4 1 3 3 3 4 3 " +
            "2 3 1 5 1 3 1 3 3 3 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 3 4 3 " +
            "6 3 6 5 6 5 6 5 4 5 4 5 2 5 2 3 2 3 2 3 4 4 4 4 6 2 4 4 4 4 3 4 5 " +
            "4 6 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    controller.playGame(model);
    assertEquals("Game over!\n" +
            "    X X X\n" +
            "    X X X\n" +
            "X X X X X X X\n" +
            "X X X O X X X\n" +
            "X X X X X X X\n" +
            "    X X X\n" +
            "    X X X\n" +
            "Score: 1\n",out.toString().substring(out.toString().length() - 102));
  }

  @Test
  public void testReadableThrowException() throws IllegalStateException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("2 2 2 2");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, out);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    try {
      controller.playGame(model);
    } catch (IllegalStateException e) {
      assertEquals("ran out of inputs.",e.getMessage());
    }
  }

  @Test
  public void testAppendableThrowException() throws IllegalStateException {
    StringWriter out = new StringWriter();
    BufferedWriter bufferedWriter = new BufferedWriter(out);
    Reader in = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(in, bufferedWriter);
    MarbleSolitaireModel model = new MarbleSolitaireModelImpl();

    try {
      try {
        bufferedWriter.close();
        controller.playGame(model);
      } catch (IOException e) {
        //do nothing
      }
    } catch (IllegalStateException m) {
      assertEquals("appendable can not append.",m.getMessage());
    }
  }
}