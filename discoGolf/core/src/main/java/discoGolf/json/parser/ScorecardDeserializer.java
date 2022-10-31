package discoGolf.json.parser;

import java.io.IOException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import discoGolf.core.Course;
import discoGolf.core.ScorecardDAO;

/**
 * Creates a Scorecard object from json data
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-09 
 */
public class ScorecardDeserializer extends JsonDeserializer<ScorecardDAO> {

    private CourseDeserializer courseDeserializer = new CourseDeserializer();

    /**
     * Deserialize a scorecard from JSON
     * 
     * @param parser  The parser to use
     * @param context The context to use
     * @return The deserialized scorecard
     * @throws JacksonException Error when trying to use other deserializers or
     *                          methods from jackson library
     * @throws IOException      Error when trying to read from the database
     */
    @Override
    public ScorecardDAO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode node = p.getCodec().readTree(p);
        return deserialize((JsonNode) node);
    }

    /**
     * Deserialize a scorecard from a JSON node Makes it possible to use this
     * deserializer in other deserializers (DataArrayDeserializer)
     * 
     * @param node The node to deserialize
     * @see DataArrayDeserializer
     */
    ScorecardDAO deserialize(JsonNode node) {
        if (node instanceof ObjectNode objNode) {
            String playerName = ((TextNode) objNode.get("playerName")).asText();
            int totalScore = objNode.get("score").intValue();
            int bestHole = objNode.get("bestHole").intValue();
            JsonNode courseNode = objNode.get("course");
            Course course = courseDeserializer.deserialize(courseNode);
            return new ScorecardDAO(course, playerName, totalScore, bestHole);
        }
        return null;
    }

}
