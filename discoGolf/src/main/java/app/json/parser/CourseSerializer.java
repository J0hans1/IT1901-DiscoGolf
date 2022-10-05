package app.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import app.Course;

public class CourseSerializer extends JsonSerializer<Course> {
    public Course course;

    public CourseSerializer (Course course) {
        this.course = course;
    }

    /**
     * Serialize a course object to JSON format
     */    
    @Override
    public void serialize(Course course, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //konverter ArrayList<Integer> til int[] for å kunne bruke writeArray() funksjonen.
        int[] arr = course.getParValues().stream().mapToInt(i -> i).toArray();

        //skriver ut objektet
        gen.writeStartObject();
        gen.writeStringField("name", course.getCourseName());
        gen.writeArray(arr, 0, arr.length);
        gen.writeEndObject();
    }
}
