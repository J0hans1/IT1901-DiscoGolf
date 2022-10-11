package discoGolf.json.parser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import discoGolf.core.Data;

public class DataArraySerializer extends JsonSerializer<Data>{

    @Override
    public void serialize(Data value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("data", value.getData());     
        gen.writeEndObject();   
    }
    
}
