package app.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import app.Course;

public class CourseSerializer extends JsonSerializer<Course> {

    /**
     * Serialize a course object to JSON format
     */
    @Override
    public void serialize(Course value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        
    }
    
}
