package discoGolf.json.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import discoGolf.core.Course;

public class CourseDeserializer extends JsonDeserializer<Course> {

    /**
     * Deserialize a Course from JSON
     */
    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode node = p.getCodec().readTree(p);   //? må tolkes
        return deserialize((JsonNode) node);        //? må tolkes
    }

    public Course deserialize(JsonNode node) {
        if (node instanceof ObjectNode o) {
            // read the Json tree and collect values from fields in JSON object
            JsonNode name = o.get("name");
            JsonNode parValues = o.get("parValues");

            // Translate ParValues from JsonNode to ArrayList<Integer>.
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < parValues.size(); i++) {
                System.out.println(parValues.get(i).asInt());
            }

            if (name != null && parValues != null) {
                return new Course(name.asText(), list);
            }
        }
        return null;
    }
    
}
