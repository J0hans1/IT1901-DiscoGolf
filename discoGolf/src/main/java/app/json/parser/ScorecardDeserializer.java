package app.json.parser;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import app.Scorecard;

public class ScorecardDeserializer extends JsonDeserializer<Scorecard> {
    
    /**
     * Deserialize a scorecard from JSON
     */
    public Scorecard deserialize(String jsonString) {
        
    }
}
