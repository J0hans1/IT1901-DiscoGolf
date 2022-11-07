package discoGolf.core;

/**
 * Interface for a scorecard object with getters for the necessary fields
 * that are saved and stored in the database
 * @author @Jakob Opland
 * @version 1.2
 * @since 2022-10-29
 */
public interface ScorecardInterface {
    
    public String getPlayerName();

    public Course getCourse();

    public String getCourseName();
    
    public int getScore(); 

    public int getBestHole();

}
