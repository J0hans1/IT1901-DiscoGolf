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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import discoGolf.core.Course;

/**
 * Transforms a Course object stored in json format to a new Course class instance
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-02
 */
public class CourseDeserializer extends JsonDeserializer<Course> {

    /**
     * Deserialize a Course object from JSON file to java object
     * @param parser The parser to use
     * @param context The context to use
     * @return The deserialized course object
     * @throws JacksonException Error when trying to use other deserializers or methods from jackson library
     * @throws IOException Error when trying to read from the database
     */
    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode node = p.getCodec().readTree(p);  
        return deserialize((JsonNode) node);
    }

    /**
     * Deserialize a Course object from JSON node to java object
     * Makes it possible to use this method in other deserializers
     * @param node The node to deserialize
     * @return The deserialized Course object
     */
    Course deserialize(JsonNode node) {
        if (node instanceof ObjectNode objNode) {
            ArrayList<Integer> parValues = new ArrayList<>();
            String courseName = ((TextNode) objNode.get("courseName")).asText();
            JsonNode parValuesNode = objNode.get("parValues");
            for (JsonNode parValue :((ArrayNode) parValuesNode)) {
                parValues.add(parValue.asInt());
            }            
            return new Course(courseName, parValues);     
        }
        return null;
    }
}
