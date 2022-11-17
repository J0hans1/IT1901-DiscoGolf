package discoGolf.json.parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import discoGolf.core.Data;
import java.io.IOException;

/**
 * Converts Data.java object to .json format object 
 *
 * @author Markus Johansen and Jakob Opland.
 * @version 1.0
 * @since 2022-10-03
 */
public class DataArraySerializer extends JsonSerializer<Data> {

  /**
   * Serialize a Data Java object to JSON format.
   *
   * @param value The scorecard to serialize
   * @param gen The generator to use
   * @param serializers The serializer provider
   * @throws IOException Error when trying to write to the database
   */
  @Override
  public void serialize(Data value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeStartObject();
    gen.writeObjectField("data", value.getData());
    gen.writeEndObject();
  }
}
