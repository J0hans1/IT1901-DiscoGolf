package discoGolf.json.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import discoGolf.core.Data;
import discoGolf.core.Scorecard;

public class DataArrayDeserializer extends JsonDeserializer<Data>{
    private ScorecardDeserializer scorecardDeserializer = new ScorecardDeserializer();

    @Override
    public Data deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        ArrayList<Scorecard> list = new ArrayList<>();
        ArrayNode jsonList = (ArrayNode) node.get("data");

        for (JsonNode n : jsonList) {
            Scorecard scorecard = scorecardDeserializer.deserialize(n);
            list.add(scorecard);
        }

        Data data = new Data();
        data.setData(list);
        return data;
    }
}
