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
    private ArrayList<Scorecard> data;

    /**
     * Object for storing all the scorecards
     * Its purpose is to be serialized and deserialized to and from JSON
     */
    public Data() {
        this.data = new ArrayList<>();
    }

    /**
     * Get the list of scorecards
     * @return The list of scorecards
     */
    public ArrayList<Scorecard> getData() {
        return new ArrayList<>(data);
    }

    /**
     * Set the list of scorecards
     * @param data The list of scorecards
     */
    public void setData(ArrayList<Scorecard> data) {
        this.data = data;
    }

    /**
     * Add a scorecard to the list
     * @param scorecard The scorecard to add
     */
    public void add(Scorecard scorecard) {
        data.add(scorecard);
    }

    @Override
    public String toString() {
        String contentString = "";
        for (Scorecard scorecard : data) {
            contentString += "\n" + scorecard.toString();
        }
        return "Data [data=" + contentString + "]";
    }
}
