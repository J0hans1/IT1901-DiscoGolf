package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Course;

/**
 * Writes a Course object to json format
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-03
 */
public class CourseSerializer extends JsonSerializer<Course> {

    /**
     * Serialize a course Java object to JSON format
     * 
     * @param value       The course to serialize
     * @param gen         The generator to use
     * @param serializers The serializer provider
     * @throws IOException Error when trying to write to the database
     */
    @Override
    public void serialize(Course course, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("courseName", course.getCourseName());
        gen.writeNumberField("numberOfHoles", course.getNumberOfHoles());
        gen.writeObjectField("parValues", course.getParValues());
        gen.writeObjectField("parForHoles", course.getPar());
        gen.writeEndObject();
    }
}
