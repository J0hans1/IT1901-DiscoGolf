package app.json;

import java.io.File;
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

import app.Course;
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
     */
    public Scorecard readScorecard(Reader reader) throws IOException {
        return mapper.readValue(reader, Scorecard.class);
      }

    /**
     * 
     * @param scorecard
     * @param write
     */
    public void writeScorecard(Scorecard scorecard, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, scorecard);;
    }

    public void writeCourse(Course course, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, course);
    }

    /**
    Getter for the file path of the database.txt file
    @return String representation of the path
    */
    public String getPathString() throws URISyntaxException {
        String path = new File(getClass().getResource("").toURI())
        .getAbsolutePath()
        .split("target")[0]; //Obtain a absolute path to the "discoGolf" folder in a way that will work for every user
        path = path + "src/main/data/database.json"; //Add the final part of the path
        return path;
    }

    /**
     * Saves a scorecard object in the database.json file
     * @param scorecard a finished scorcard object
     * @throws IOException 
     * @throws URISyntaxException
     */
    public void saveScorecard(Scorecard scorecard) throws IOException, URISyntaxException {
        try (Writer writer = new FileWriter(getPathString(), StandardCharsets.UTF_8)) {
            writeScorecard(scorecard, writer);
        } 
    }

    public void saveCourse(Course course) throws IOException, URISyntaxException {
        try (Writer writer = new FileWriter(getPathString(), StandardCharsets.UTF_8)) {
            writeCourse(course, writer);
        } 
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Course Dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        Scorecard scorecard = new Scorecard(Dragvoll, "Jakob");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                scorecard.addThrow();
            }
            scorecard.nextHole();
        }

        DiscoGolfPersistence parsing = new DiscoGolfPersistence();
        parsing.saveScorecard(scorecard);
    }
}



