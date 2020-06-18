package cs3500.marblesolitaire.model.hw02;

/**
 * a class to represent a spot in a game.
 */
public class Spot {

  private SpotType type;

  /**
   * constructor of a Spot.
   * @param spotType construct the class with the given spotType
   */
  public Spot(SpotType spotType) {
    this.type = spotType;
  }

  /**
   * change the type of the class. cannot change or change into a border type
   * @param type the type to change into
   */
  public void changeType(SpotType type) {
    if ((type != SpotType.border) && (this.type != SpotType.border)) {
      this.type = type;
    }
  }

  /**
   * check if the given Spot has the same type of this Spot.
   * @param type the given Spot
   * @return true if has the same type
   */
  public boolean equalType(SpotType type) {
    return this.type == type;
  }

  /**
   * print the class according to the getGameState() method.
   * @return the String printed
   */
  public String print() {
    if (type == SpotType.marble) {
      return "O";
    }
    else if (type == SpotType.space) {
      return "X";
    }
    else {
      return " ";
    }
  }
}