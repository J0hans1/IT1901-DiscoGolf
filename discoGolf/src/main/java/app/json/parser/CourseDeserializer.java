package app.json.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import app.Course;

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
        if (node instanceof ObjectNode o) {         //? hva er object Node
            JsonNode name = o.get("name");
            JsonNode parValues = o.get("parValues");
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < parValues.size(); i++) {
                System.out.println(parValues.get(i).asInt());
            }
            Course course = new Course(name.asText(), list); //? må tolkes
            return course;
        }
        return null;
    }
    
}
