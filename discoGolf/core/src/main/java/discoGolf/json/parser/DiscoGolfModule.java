package discoGolf.json.parser;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;

/**
 * Initializes deserializer and serializer objects
 * @author Markus Johansen and Jakob Opland
 * @version 1.0
 * @since 2022-10-05
 */
public class DiscoGolfModule extends SimpleModule {
    
    private static final String NAME = "DiscoGolfModule";

    /**
     * Inizializing the serializers and deserializers
     */
    public DiscoGolfModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Course.class, new CourseSerializer());
        addSerializer(Scorecard.class, new ScorecardSerializer());
        addDeserializer(Scorecard.class, new ScorecardDeserializer());
        addSerializer(Data.class, new DataArraySerializer());
        addDeserializer(Data.class, new DataArrayDeserializer());
    }
}
