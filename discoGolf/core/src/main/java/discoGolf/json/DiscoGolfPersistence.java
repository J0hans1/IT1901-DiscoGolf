package discoGolf.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import discoGolf.core.Data;
import discoGolf.core.ScorecardInterface;
import discoGolf.json.parser.DiscoGolfModule;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Creates new objectMapper and adds modules to the serializers. Combines all the classes of the
 * parser module to create persistence.
 *
 * @author Markus Johansen and Jakob Opland.
 * @version 1.0
 * @since 2022-10-13
 */
public class DiscoGolfPersistence {
  private ObjectMapper mapper;
  private String path = System.getProperty("user.home") + "/discoGolf.json";

  /**
   * Initializing the ObjectMapper object by calling createMapper().
   */
  public DiscoGolfPersistence() {
    this.mapper = createMapper();
  }

  /**
   * Creates a new ObjectMapper object and adds the modules to the serializers.
   */
  public DiscoGolfPersistence(String path) {
    this.path = System.getProperty("user.home") + path;
    this.mapper = createMapper();
  }

  /**
   * Create a new ObjectMapper object and adds the DiscoGolfModule to it This makes it possible to
   * use DiscoGolfModules custom serializers and deserializers with the ObjectMapper.
   *
   * @return The new ObjectMapper object.
   */
  public static ObjectMapper createMapper() {
    return new ObjectMapper().registerModule(createModule());
  }


  /**
   * Returning a new SimpleModule object that initializes the serializer and deserializers.
   *
   * @return DiscoGolfModule object.
   */
  public static SimpleModule createModule() {
    return new DiscoGolfModule();
  }

  /**
   * returns the ObjectMapper object for a DiscoGolfPersistence object.
   *
   * @return The ObjectMapper object.
   */
  public ObjectMapper getMapper() {
    return mapper;
  }


  /**
   * Updates the Database.json with the new data (New scorecard submitted by user.
   *
   * @param scorecard - The scorecard that is to be added to the database.
   * @throws IOException Error when trying to write to the database.
   * @throws URISyntaxException Error when trying to parse Java Objects to JSON objects.
   */
  public void sendScorecardToDatabase(ScorecardInterface scorecard)
      throws IOException, URISyntaxException {
    Data data = readData();
    data.add(scorecard);
    saveData(data);
  }


  /**
   * Writes a data object to the database.json file.
   *
   * @param data - The data object that is to be written to the database.
   * @throws IOException Error while writing to the file.
   * @throws URISyntaxException Error translating Java Data object to JSON.
   */
  public void saveData(Data data) throws IOException, URISyntaxException {
    try (Writer writer = new FileWriter(getPathString(), StandardCharsets.UTF_8)) {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
    }
  }

  /**
   * Reads the JSON file and returns the Data object at the root of the JSON file.
   *
   * @return Data object containing all registered scorecards.
   * @throws IOException Error while reading the file.
   * @throws URISyntaxException Parsing the file to Java failed.
   */
  public Data readData() throws IOException, URISyntaxException {
    try (Reader reader = new FileReader(getPathString(), StandardCharsets.UTF_8)) {
      return mapper.readValue(reader, Data.class);
    }
  }

  /**
   * Translates a scorecard object to a json format string, and returns it.
   *
   * @param scorecard - the scorecard that is to be converted to json format.
   * @return Json format String.
   * @throws IOException Error while converting from .java to .json.
   */
  public String scorecardToJson(ScorecardInterface scorecard) throws IOException {
    return mapper.writeValueAsString(scorecard);
  }

  // return a data object as a json string.
  public String dataToJson(Data data) throws IOException {
    return mapper.writeValueAsString(data);
  }

  // return a data object from a json string.
  public Data jsonToData(String jsonString) throws IOException {
    return mapper.readValue(jsonString, Data.class);
  }

  // remove file from the database.
  public void deleteDatabase() throws IOException, URISyntaxException {
    File file = new File(getPathString());
    file.delete();
  }

  /**
   * Getter for the file path of the database.json file Useful for reading and writing to the file.
   * Finds the path of the application folder, and then adds the path of the database.json file to
   * it.
   *
   * @return String representation of the path to database.json.
   * @throws URISyntaxException Error while parsing the path to the database.json file.
   * @throws IOException Error while reading or writing the file.
   */
  public String getPathString() throws URISyntaxException, IOException {
    Path p = Paths.get(path);
    if (!(Files.exists(p))) {
      File f = new File(p.toString());
      f.createNewFile();
      Data data = new Data();
      saveData(data);
    }
    return p.toString();
  }

}
