package discoGolf.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interface for a scorecard object with getters for the necessary fields
 * that are saved and stored in the database
 * @author @Jakob Opland
 * @version 1.2
 * @since 2022-10-29
 */

@JsonDeserialize(as = ScorecardDAO.class)
public interface ScorecardInterface {
    
    public String getPlayerName();

    public Course getCourse();

    public String getCourseName();
    
    public int getScore(); 

    public int getBestHole();

}
