package app.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import app.Scorecard;

public class ScorecardSerializer extends JsonSerializer<Scorecard>{

    /**
     * Serializes a scorecard object to JSON format
     * format : { name: "...", totalScore: "...", courseName: "..." }
     */
    @Override
    public void serialize(Scorecard scorecard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", scorecard.getNameOfPlayer());
        gen.writeNumberField("totalScore", scorecard.getTotalScore());
        gen.writeStringField("courseName", scorecard.getCourseName());
        gen.writeEndObject();
    }
}
