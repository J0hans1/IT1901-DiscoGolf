package discoGolf.core;

import java.util.ArrayList;
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
     * @param scorecard The scorecard to add
     */
    public void add(ScorecardInterface scorecard) {
        data.add(scorecard);
    }
}
