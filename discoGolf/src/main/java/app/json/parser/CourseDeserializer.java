package app.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import app.Course;

public class CourseDeserializer extends JsonDeserializer<Course> {

    /**
     * Deserialize a Course from JSON
     */
    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
