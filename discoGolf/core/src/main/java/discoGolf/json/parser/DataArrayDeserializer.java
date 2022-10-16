package discoGolf.json.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import discoGolf.core.Data;
import discoGolf.core.Scorecard;

public class DataArrayDeserializer extends JsonDeserializer<Data> {
    private ScorecardDeserializer scorecardDeserializer = new ScorecardDeserializer();

    /**
     * Deserialize a data object from JSON file
     * 
     * @param parser  The parser to use
     * @param context The context to use
     * @return The deserialized data object
     * @throws JacksonException Error when trying to use other deserializers or
     *                          methods from jackson library
     * @throws IOException      Error when trying to read from the database
     */
    @Override
    public Data deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        if (node instanceof ObjectNode objNode) {
            ArrayList<Scorecard> list = new ArrayList<>();
            ArrayNode jsonList = (ArrayNode) objNode.get("data");
            for (JsonNode n : jsonList) {
                Scorecard scorecard = scorecardDeserializer.deserialize(n);
                list.add(scorecard);
            }
            Data data = new Data();
            data.setData(list);
            return data;
        }
        return null;
    }
}
