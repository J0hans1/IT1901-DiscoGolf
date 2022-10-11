package discoGolf.json.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;

public class ScorecardDeserializer extends JsonDeserializer<Scorecard> {

    private CourseDeserializer courseDeserializer = new CourseDeserializer();
    /**
     * Deserialize a scorecard from JSON
     */
    @Override
    public Scorecard deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode node = p.getCodec().readTree(p);  
        return deserialize((JsonNode) node);

    }

    Scorecard deserialize(JsonNode node) {
        if (node instanceof ObjectNode objNode) {
            String playerName = ((TextNode) objNode.get("playerName")).asText();
            int totalScore = ((IntNode) objNode.get("score")).asInt();
            
            ArrayList<Integer> throwsList = new ArrayList<>();
            JsonNode throwsListNode = objNode.get("throwsList");
            for (JsonNode parValue :((ArrayNode) throwsListNode)) {
                throwsList.add(parValue.asInt());
            }

            JsonNode courseNode = objNode.get("course");
            Course course = courseDeserializer.deserialize(courseNode);

            return new Scorecard(course, playerName);
        }
        return null;
    }
    
}
