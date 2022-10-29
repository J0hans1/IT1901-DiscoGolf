package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Scorecard;
import discoGolf.core.ScorecardInterface;

/**
 * Writes data from Scorcard object to json format
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-10
 */
public class ScorecardSerializer extends JsonSerializer<ScorecardInterface>{

    /**
     * Serialize a scorecard Java object to JSON format
     * @param value       The scorecard to serialize
     * @param gen         The generator to use
     * @param serializers The serializer provider
     * @throws IOException Error when trying to write to the database
     */
    @Override
    public void serialize(ScorecardInterface scorecard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("playerName", scorecard.getPlayerName());
        gen.writeNumberField("score", scorecard.getTotalScore());
        gen.writeNumberField("bestHole", scorecard.getBestHoleScore());
        gen.writeObjectField("course", scorecard.getCourse());
        gen.writeEndObject();
    }
}
