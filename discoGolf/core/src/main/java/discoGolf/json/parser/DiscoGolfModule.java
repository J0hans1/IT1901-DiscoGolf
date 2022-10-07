package discoGolf.json.parser;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;

public class DiscoGolfModule extends SimpleModule {
    
    private static final String NAME = "DiscoGolfModule";

    /**
     * Inizializing the serializers and deserializers
     */
    public DiscoGolfModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(Course.class, new CourseSerializer());
        addDeserializer(Course.class, new CourseDeserializer());
        
        addSerializer(Scorecard.class, new ScorecardSerializer());
        addDeserializer(Scorecard.class, new ScorecardDeserializer());
    }
}
