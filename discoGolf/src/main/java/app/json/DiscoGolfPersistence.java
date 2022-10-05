package app.json;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import app.Scorecard;
import app.json.parser.DiscoGolfModule;

public class DiscoGolfPersistence {
    
    private ObjectMapper mapper;

    /**
     * Initializing the ObjectMapper object by calling createMapper()
     */
    public DiscoGolfPersistence() {
        this.mapper = createMapper();
    }

    /**
     * returning a new SimpleModule object that initializes the serializer and deserializers
     * @return DiscoGolfModule object
     */
    public static SimpleModule createModule() {
        return new DiscoGolfModule();
    }

    /**
     * Create a new ObjectMapper object and register it to the new module DiscoGolfModule
     * @return the new ObjectMapper object
     */
    public static ObjectMapper createMapper() {
        return new ObjectMapper().registerModule(createModule());
    }

    /**
     * 
     * @param reader
     * @return
     * @throws IOException
     */
    public Scorecard readScorecard(Reader reader) throws IOException {
        return mapper.readValue(reader, Scorecard.class);
      }

    /**
     * 
     * @param scorecard
     * @param write
     */
    public void writeScorecard(Scorecard scorecard) throws IOException {
        mapper.writeValueAsString(scorecard);
    }

    /**
    Getter for the file path of the database.txt file
    @return String representation of the path
    */
    public Path getPath() throws URISyntaxException {
        String path = new File(getClass().getResource("").toURI())
        .getAbsolutePath()
        .split("target")[0]; //Obtain a absolute path to the "discoGolf" folder in a way that will work for every user
        path = path + "src/main/data/database.txt"; //Add the final part of the path
        return Path(path);
    }
}


