package discoGolf.core;

import java.util.ArrayList;

public class Data {
    private ArrayList<Scorecard> data;

    public Data() {
        this.data = new ArrayList<>();
    }

    public ArrayList<Scorecard> getData() {
        return new ArrayList<>(data);
    }

    public void setData(ArrayList<Scorecard> data) {
        this.data = data;
    }

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
