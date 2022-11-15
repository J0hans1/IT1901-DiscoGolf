package discoGolf.core;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Keeps track of all scorecard objects in the database.
 * The scorecard objects are stored in the data ArrayList
 * @author Jakob Opland and Markus Johansen
 * @version 1.0
 * @since 2022-10-10
 */
public class Data {
    private ArrayList<ScorecardInterface> data;

    /**
     * Object for storing all the scorecards Its purpose is to be serialized and
     * deserialized to and from JSON
     */
    public Data() {
        this.data = new ArrayList<>();
    }

    /**
     * Get the list of scorecards
     * @return The list of scorecards
     */
    public ArrayList<ScorecardInterface> getData() {
        return new ArrayList<>(data);
    }

    /**
     * Set the list of scorecards
     * @param data The list of scorecards
     */
    public void setData(ArrayList<ScorecardInterface> data) {
        this.data = data;
    }

    /**
     * Add a scorecard to the list
     * 
     * @param scorecard The scorecard to add
     */
    public void add(ScorecardInterface scorecard) {
        data.add(scorecard);
    }

    /**
     * Remove a scorecard from the list TODO: Modify javadoc
     * 
     * @param scorecard The scorecard to remove
     */

    public void removeScorecardWithName(String name) {
        for (ScorecardInterface scorecard : data) {
            if (scorecard.getPlayerName().equals(name)) {
                data.remove(scorecard);
                break;
            }
        }
    }

    public Iterator<ScorecardInterface> iterator() {
        return data.iterator();
    }
}
