package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Scorecard;

public class ScorecardSerializer extends JsonSerializer<Scorecard>{

    /**
     * Serializes a scorecard object to JSON format
     * format : { name: "...", totalScore: "...", courseName: "..." }
     */
    @Override
    public void serialize(Scorecard scorecard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("playerName", scorecard.getPlayerName());
        gen.writeNumberField("score", scorecard.getTotalScore());
        gen.writeObjectField("throwsList", scorecard.getThrowsList());
        gen.writeObjectField("course", scorecard.getCourse());
        gen.writeEndObject();
    }
}
