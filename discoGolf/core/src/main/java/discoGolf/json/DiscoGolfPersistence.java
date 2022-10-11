package discoGolf.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import discoGolf.json.parser.DiscoGolfModule;

public class DiscoGolfPersistence {
    
    private ObjectMapper mapper;


    /**
     * Initializing the ObjectMapper object by calling createMapper()
     */
    public DiscoGolfPersistence() {
        this.mapper = createMapper();
    }

    /**
     * Create a new ObjectMapper object and register it to the new module DiscoGolfModule
     * @return the new ObjectMapper object
     */
    public static ObjectMapper createMapper() {
        return new ObjectMapper().registerModule(createModule());
    }

    /**
     * returning a new SimpleModule object that initializes the serializer and deserializers
     * @return DiscoGolfModule object
     */
    public static SimpleModule createModule() {
        return new DiscoGolfModule();
    }

    /**
     * 
     * @param reader
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public void readScorecard(Reader reader) throws IOException, URISyntaxException {  
            Scorecard scorecard = mapper.readValue(reader, Scorecard.class);
            System.out.println(scorecard);
    }

    /**
     * 
     * @param scorecard
     * @param write
     */
    public void writeScorecard(Scorecard scorecard, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, scorecard);
    }

    /**
     * Getter for the file path of the database.txt file
     * @return String representation of the path
    */
    public String getPathString() throws URISyntaxException {
        String path = new File(getClass().getResource("").toURI())
        .getAbsolutePath()
        .split("target")[0]; //Obtain a absolute path to the "discoGolf" folder in a way that will work for every user
        path = path + "src/main/resources/database.json"; //Add the final part of the path
        return path;
    }

    /**
     * Saves a scorecard object in the database.json file
     * @param scorecard a finished scorcard object
     * @throws IOException 
     * @throws URISyntaxException
     */
    public void saveScorecard(Scorecard scorecard) throws IOException, URISyntaxException {
        if (getPathString() == null) {
            throw new IllegalStateException("no existing filepath");
        } try (Writer writer = new FileWriter(getPathString(), StandardCharsets.UTF_8)) {
            writeScorecard(scorecard, writer);
        } 
    }

     /**
     * Saves a scorecard object in the database.json file
     * @param scorecard a finished scorcard object
     * @throws IOException 
     * @throws URISyntaxException
     */
    public void loadScorecard() throws IOException, URISyntaxException {
        if (getPathString() == null) {
            throw new IllegalStateException("no existing filepath");
        } try (Reader reader = new FileReader(getPathString(), StandardCharsets.UTF_8)) {
            readScorecard(reader);;
        } 
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Course dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        Scorecard scorecard = new Scorecard(dragvoll, "Jakob");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                scorecard.addThrow();
            }
            scorecard.nextHole();
        }

        DiscoGolfPersistence parsing = new DiscoGolfPersistence();
        parsing.saveScorecard(scorecard);
        parsing.loadScorecard();
    }
}



