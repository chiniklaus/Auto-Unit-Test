package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * An implementation of the interface MarbleSolitaireController.
 * There are three fields:
 * A Readable, which serves as the input of the controller;
 * A Appendable, which serves as the output of the controller;
 * A List of integers. While the there are input, the Class will
 * exam them and add them to the list of integers.
 * Every time the size of the list hits 4, the playGame(model) method
 * makes a move, and update the model accordingly.
 * There are one constructor, which takes in a Readable and a Appendable and
 * set them to the fields.
 * There are three private helper methods, the purpose of them are appending
 * the outputs more efficiently. Detailed documentation available in the specific methods.
 * The playgame method takes in a MarbleSolitaireModel, and update it according to the logic
 * in the method.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  //readable object that the controller reads from
  private Readable rd;
  //appendable object that the controller transmit information to
  private Appendable ap;
  //a list of input that read from the readable
  private List<Integer> input = new ArrayList<>();

  /**
   * constructor.
   * @param rd the readable object.
   * @param ap the appendable object.
   * @throws IllegalArgumentException if rd or ap is empty, throw this exception.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null) {
      throw new IllegalArgumentException("Readable is null.");
    }

    if (ap == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }

    this.rd = rd;
    this.ap = ap;
  }

  //a private method to transmit the game state and the current score to the appendable.
  //each on a different line. Ends with a new line.
  private void appendGameStateNScore(MarbleSolitaireModel model) {
    try {
      ap.append(model.getGameState());
      ap.append("\n");
      ap.append("Score: ");
      ap.append(Integer.toString(model.getScore()));
      ap.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("appendable can not append.");
    }
  }

  //a private method to transmit error message to the player so the player could reenter input
  //base on the message. Ends with a new line.
  private void appendMessageForUnexpectedInput(String s1, String s2) {
    try {
      ap.append("Unexpected input, please try again, starting from ");
      ap.append("the ");
      ap.append(s1);
      ap.append(" number of the position ");
      ap.append(s2);
      ap.append(" where a marble is to be moved.");
      ap.append("\n");
    } catch (IOException u) {
      throw new IllegalStateException("appendable can not append.");
    }
  }

  //a private method to append the prompts for the player. According to the param int,
  //(which is the size of the input list), cue the player of what input is needed.
  private void appendPrompts(int i) {
    try {
      switch (i) {
        case 0:
          ap.append("Enter from row: \n");
          break;
        case 1:
          ap.append("Enter from column: \n");
          break;
        case 2:
          ap.append("Enter to row: \n");
          break;
        default:
          ap.append("Enter to column: \n");
          break;
      }
    } catch (IOException u) {
      throw new IllegalStateException("appendable can not append.");
    }

  }

  @Override
  public void playGame(MarbleSolitaireModel model)  throws IllegalArgumentException,
          IllegalStateException {

    //throw illegalArgumentException if the model passed in is null.
    if (model == null) {
      throw new IllegalArgumentException("model is null.");
    }

    //use a scanner to scan the readable.
    Scanner scan = new Scanner(rd);

    //Transmit game state to the Appendable object exactly as the model provides it.
    //Score too but on another line.
    appendGameStateNScore(model);

    //the first prompt for the player. tell him/her to enter the from row.
    try {
      ap.append("Enter from row: \n");
    } catch (IOException e) {
      throw new IllegalStateException("appendable can not append.");
    }

    //while the scanner has inputs, deal with them according to the logic in the
    //while loop.
    while (scan.hasNext()) {

      String in = scan.next();

      //print game state and exit method if player enter q or Q.
      if (in.equals("q") || in.equals("Q")) {
        try {
          ap.append("Game quit!\n");
          ap.append("State of game when quit:\n");
          appendGameStateNScore(model);
          return;
        } catch (IOException e) {
          throw new IllegalStateException("appendable can not append.");
        }
      }
      //depend on the size of input array and the player's input, determine if add the
      //input to the array, or throw exception and ask the player to input again base on
      //the error message.
      else {
        try {
          int num = Integer.parseInt(in);
          input.add(num);
        } catch (NumberFormatException e) {
          switch (input.size()) {
            case 0:
              appendMessageForUnexpectedInput("row", "from");
              break;
            case 1:
              appendMessageForUnexpectedInput("column", "from");
              break;
            case 2:
              appendMessageForUnexpectedInput( "row", "to");
              break;
            default:
              appendMessageForUnexpectedInput("column", "to");
              break;
          }
        }
      }

      //if the input array has a size of 4, means there are enough to make a move. send the
      //information to the model and make the move. catch possible exceptions from the model.
      //if the move is valid, model updates, input array refresh to a new empty array to receive
      //inputs.
      if (input.size() == 4) {
        try {
          model.move(input.get(0) - 1,input.get(1) - 1,
                  input.get(2) - 1,input.get(3) - 1);
          appendGameStateNScore(model);
        } catch (IllegalArgumentException e) {
          try {
            ap.append("Invalid move. Play again. ");
            ap.append(e.getMessage());
            ap.append("\n");
          } catch (IOException u) {
            throw new IllegalStateException("appendable can not append.");
          }
        }
        input = new ArrayList<>();
      }

      //the player won, print the final state, exit the method.
      if (model.isGameOver()) {
        try {
          ap.append("Game over!\n");
          appendGameStateNScore(model);
          return;
        } catch (IOException e) {
          throw new IllegalStateException("appendable can not append.");
        }
      }

      //cue the player what to input next. If needed.
      appendPrompts(input.size());
    }

    //if ran out of inputs, throw illegalStateException
    if (! scan.hasNext()) {
      throw new IllegalStateException("ran out of inputs.");
    }
  }
}