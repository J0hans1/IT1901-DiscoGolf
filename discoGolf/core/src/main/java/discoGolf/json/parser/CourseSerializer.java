package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Course;

public class CourseSerializer extends JsonSerializer<Course> {
    public Course course;

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

        //https://attacomsian.com/blog/jackson-create-json-array
        /*gen.writeArray(arr, 0, arr.length);*/ //!her krasjer programmet
        gen.writeEndObject();
    }
}


/*
 * 
 * class Foo {
    @JsonSerialize(using = MySerializer.class)
    private List<String> fooElements;
}
https://stackoverflow.com/questions/18623667/serializing-array-with-jackson
public class MySerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        List<String> fooList = (List<String>) value;

        if (fooList.isEmpty()) {
            return;
        }

        String fooValue = fooList.get(0);
        String[] fooElements = fooValue.split(",");

        jgen.writeStartArray();
        for (String fooValue : fooElements) {
            jgen.writeString(fooValue);
        }
        jgen.writeEndArray();
    }
}
 */
