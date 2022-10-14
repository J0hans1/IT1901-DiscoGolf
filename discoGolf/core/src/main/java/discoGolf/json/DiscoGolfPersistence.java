package discoGolf.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.net.URISyntaxException;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.json.parser.DiscoGolfModule;

public class DiscoGolfPersistence {
    private ObjectMapper mapper;


    /**
     * Initializing the ObjectMapper object by calling createMapper()
     * @see createMapper()
     */
    public DiscoGolfPersistence() {
        this.mapper = createMapper();
    }


    /**
     * Create a new ObjectMapper object and adds the DiscoGolfModule to it
     * This makes it possible to use DiscoGolfModules custom serializers and deserializers with the ObjectMapper
     * @return The new ObjectMapper object
     * @see createModule()
     */
    public static ObjectMapper createMapper() {
        return new ObjectMapper().registerModule(createModule());
    }


    /**
     * Returning a new SimpleModule object that initializes the serializer and deserializers
     * @return DiscoGolfModule object
     */
    public static SimpleModule createModule() {
        return new DiscoGolfModule();
    }


    /**
     * Updates the Database.json with the new data (New scorecard submitted by user)
     * @param Scorecard scorecard - The scorecard that is to be added to the database
     * @throws IOException Error when trying to write to the database
     * @throws URISyntaxException Error when trying to parse Java Objects to JSON objects
     */
    public void sendScorecardToDatabase(Scorecard scorecard) throws IOException, URISyntaxException {
        Data data = readData();
        data.add(scorecard);
        saveData(data);
    }


    /**
     * Writes a data object to the database.json file
     * @param Data data - The data object that is to be written to the database
     * @throws IOException Error while writing to the file
     * @throws URISyntaxException Error translating Java Data object to JSON
     */
    public void saveData(Data data) throws IOException, URISyntaxException {
        if (getPathString() == null) {
            throw new IllegalStateException("no existing filepath");
        }try (Writer writer = new FileWriter(getPathString(), StandardCharsets.UTF_8)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } 
    }


    /**
     * Reads the JSON file and returns the Data object at the root of the JSON file
     * @return Data object containing all registered scorecards
     * @throws IOException Error while reading the file 
     * @throws URISyntaxException Parsing the file to Java failed
     */
    public Data readData() throws IOException, URISyntaxException {
        if (getPathString() == null) {
            throw new IllegalStateException("no existing filepath");
        }try (Reader reader = new FileReader(getPathString(), StandardCharsets.UTF_8)) {
            return mapper.readValue(reader, Data.class);
        } 
    }


    /**
     * Getter for the file path of the database.json file
     * Useful for reading and writing to the file
     * Finds the path of the application folder, and then adds the path of the database.json file to it
     * @return String representation of the path to database.json
     */
    private String getPathString() throws URISyntaxException {
        String path = new File(getClass().getResource("").toURI()).getAbsolutePath().split("target")[0];
        path = path + "src/main/resources/database.json";
        return path;
    }
}



