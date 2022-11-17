package discoGolf.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interface for a scorecard object with getters for the necessary fields
 * that are saved and stored in the database
 * @author Jakob Opland
 * @version 1.2
 * @since 2022-10-29
 */

@JsonDeserialize(as = ScorecardDAO.class)
public interface ScorecardInterface {
    
    /**
     * @return the player name for the scorecard
     */
    public String getPlayerName();

    /**
     * @return the Course object for the scorecard
     */
    public Course getCourse();

    /**
     * @return the course name for the Course in the scorecard
     */
    public String getCourseName();
    
    /**
     * @return the total score for the scorecard object
     */
    public int getScore(); 

    /**
     * @return the best individual hole score for the scorecard
     */
    public int getBestHole();

}
