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

public class CourseDeserializer extends JsonDeserializer<Course> {

    /**
     * Deserialize a Course from JSON
     */
    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode node = p.getCodec().readTree(p);  
        return deserialize((JsonNode) node);
    }

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
