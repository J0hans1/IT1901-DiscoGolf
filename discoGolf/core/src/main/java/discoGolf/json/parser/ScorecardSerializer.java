package discoGolf.json.parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import discoGolf.core.ScorecardInterface;
import java.io.IOException;

/**
 * Converts Scorecard.java objects to .json format
 *
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-10
 */
public class ScorecardSerializer extends JsonSerializer<ScorecardInterface> {

  /**
   * Serialize a scorecard Java object to JSON format.
   *
   * @param gen The generator to use
   * @param serializers The serializer provider
   * @throws IOException Error when trying to write to the database
   */
  @Override
  public void serialize(ScorecardInterface scorecard, JsonGenerator gen,
      SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("playerName", scorecard.getPlayerName());
    gen.writeNumberField("score", scorecard.getScore());
    gen.writeNumberField("bestHole", scorecard.getBestHole());
    gen.writeObjectField("course", scorecard.getCourse());
    gen.writeEndObject();
  }
}
