package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Scorecard;

public class ScorecardSerializer extends JsonSerializer<Scorecard>{

    /**
     * Serialize a scorecard Java object to JSON format
     * @param value The scorecard to serialize
     * @param gen The generator to use
     * @param serializers The serializer provider
     * @throws IOException Error when trying to write to the database
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
