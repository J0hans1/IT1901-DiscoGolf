package discoGolf.core;

/**
 * Hole class storing data information for a hole
 * @author @Jakob Opland
 * @version 1.0
 * @since 2022-10-25
 */
public class Hole {
  private int par;
  private int holeThrows;
  
  public Hole(int par) {
    this.par = par;
    this.holeThrows = par;
  }

  public Hole(int par, int holeThrows) {
    this.par = par;
    this.holeThrows = holeThrows;
  }

  public int getPar() {
    return par;
  }
    
  public int getHoleThrows() {
    return holeThrows;
  }

  /**
     * adds one to the current amount of throws the player has made on the current
     * hole
  */
  public void addThrow() {
    this.holeThrows += 1;
  }

  /**
  * removes one from the current amount of throws the player has made on the
  * current hole
  */
  public void removeThrow() {
    if (getHoleThrows() == 1) {
      throw new IllegalStateException("Cannot have 0 throws");
    }
    this.holeThrows -= 1;
  }

  public int getHoleScore() {
    return getHoleThrows() - getPar();
  }

}
