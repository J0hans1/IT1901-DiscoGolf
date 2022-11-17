package discoGolf.core;

/**
 * Hole class storing data information for a hole.
 *
 * @author Jakob Opland and Markus Johansen.
 * @version 1.0
 * @since 2022-10-25
 */
public class Hole {
  private int par;
  private int holeThrows;

  /**
   * Constructor validating par input and setting both field to this value.
   *
   * @param par the par value for the hole.
   */
  public Hole(int par) {
    validatePar(par);
    this.par = par;
    this.holeThrows = par;
  }

  /**
   * the par value.
   *
   * @return the par value for the hole.
   */
  public int getPar() {
    return par;
  }

  /**
   * the toalt throws.
   *
   * @return total throws for this hole.
   */
  public int getHoleThrows() {
    return holeThrows;
  }

  /**
   * the score for this hole which is total throws - par.
   *
   * @return the hole score.
   */
  public int getHoleScore() {
    return getHoleThrows() - getPar();
  }

  /**
   * adds one to the current amount of throws the player has made on the hole.
   */
  public void addThrow() {
    this.holeThrows += 1;
  }

  /**
   * removes one from the current amount of throws the player has made at the hole.
   *
   * @throws IllegalStateException if the amount of throws currently is 1.
   */
  public void removeThrow() throws IllegalStateException {
    if (getHoleThrows() == 1) {
      throw new IllegalStateException("Cannot have 0 throws");
    }
  }

  /**
   * validate the par value and checks if it illegal.
   *
   * @param int par value for the hole.
   * @throws IllegalArgumentException if par value is not in range 2-7.
   */
  private void validatePar(int par) throws IllegalArgumentException {
    if (par < 2 || par > 7) {
      throw new IllegalArgumentException("Not a valid par value");
    }
  }
}
