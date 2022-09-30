package app.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import app.Scorecard;

public class ScorecardSerializer extends JsonSerializer<Scorecard>{
    /**
     * Serializes a scorecard object to JSON format
     */
    @Override
    public void serialize(Scorecard value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
    }
}
