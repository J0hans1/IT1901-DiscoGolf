//package discoGolf.json;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeAll;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import discoGolf.core.Course;
//import discoGolf.core.Data;
//import discoGolf.core.Scorecard;
//
///**
// * J-unit test for discoGolfModule
// * @author Jakob Opland
// * @version 1.0
// * @since 2022-10-09
// */
//public class DiscoGolfModuleTest {
//    private static ObjectMapper mapper;
//
//    @BeforeAll
//    public static void setUp() {
//        mapper = DiscoGolfPersistence.createMapper();
//    }
//
//    final static String dataInJsonFormat = """
//        {
//            "data" : [{
//                "playerName" : "Jakob",
//                "score" : 17,
//                "throwsList" : [ 8, 5, 6, 4, 6, 6, 3, 6, 4 ],
//                "course" : {
//                  "courseName" : "Dragvoll",
//                  "numberOfHoles" : 9,
//                  "parValues" : [ 3, 4, 3, 4, 3, 4, 3, 4, 3 ],
//                  "parForHoles" : {
//                    "1" : 3,
//                    "2" : 4,
//                    "3" : 3,
//                    "4" : 4,
//                    "5" : 3,
//                    "6" : 4,
//                    "7" : 3,
//                    "8" : 4,
//                    "9" : 3
//                  }
//                }
//            }]
//        }
//    """;
//
//    /**
//     * Creates a data object similar to the dataInJsonFormat String above
//     * @return a data object containing one scorecard object 
//     */
//    private Data createDataObject() {
//        ArrayList<Integer> parValues = new ArrayList<>(Arrays.asList(3, 4, 3, 4, 3, 4, 3, 4, 3));
//        ArrayList<Integer> throwsList = new ArrayList<>(Arrays.asList(8, 5, 6, 4, 6, 6, 3, 6, 4));
//        Course course = new Course("Dragvoll", parValues);
//        Scorecard scorecard = new Scorecard(course, "Jakob", throwsList);
//        Data data = new Data();
//        data.add(scorecard);
//        return data;
//    }
//
//    /**
//     * Test serializers by comparing the dataInJsonFormat with 
//     * the data object as string using mapper.writeValueAsString method
//     */
//    @Test
//    public void testSerializers() {
//        Data data = createDataObject();
//        try {
//            assertEquals(dataInJsonFormat.replaceAll("\\s+", ""), mapper.writeValueAsString(data));
//        } catch (JsonProcessingException e) {
//            fail(e.getMessage());
//        }
//    }
//
//    /**
//     * Test deserializers by reading the string dataInJsonFormat 
//     * and compare the scorecard object to expected values
//     */
//    @Test
//    public void testDeserializers() {
//        try {
//            Data data = mapper.readValue(dataInJsonFormat, Data.class);
//            Scorecard scorecardRead = data.getData().get(0);
//            assertEquals("Jakob", scorecardRead.getPlayerName());
//            assertEquals(17, scorecardRead.getTotalScore());
//            assertEquals(new ArrayList<>(Arrays.asList(8, 5, 6, 4, 6, 6, 3, 6, 4)), scorecardRead.getThrowsList());
//            assertEquals("Dragvoll", scorecardRead.getCourse().getCourseName());
//            assertEquals(new ArrayList<>(Arrays.asList(3, 4, 3, 4, 3, 4, 3, 4, 3)), scorecardRead.getCourse().getParValues());
//        } catch (JsonProcessingException e) {
//            fail(e.getMessage());
//        }
//    }
//
//    /**
//     * Test both serializers and deserializers by writing the createDataObject 
//     * as string, then read the string with mapper.readValue and comparing the new 
//     * scorecard pbject  in the data object to expected values
//     */
//    @Test 
//    public void testSerializersAndDeserializers() {
//        try {
//            String json = mapper.writeValueAsString(createDataObject());
//            Data data = mapper.readValue(json, Data.class);
//            Scorecard scorecardRead = data.getData().get(0);
//            assertEquals("Jakob", scorecardRead.getPlayerName());
//            assertEquals(17, scorecardRead.getTotalScore());
//            assertEquals(new ArrayList<>(Arrays.asList(8, 5, 6, 4, 6, 6, 3, 6, 4)), scorecardRead.getThrowsList());
//            assertEquals("Dragvoll", scorecardRead.getCourse().getCourseName());
//            assertEquals(new ArrayList<>(Arrays.asList(3, 4, 3, 4, 3, 4, 3, 4, 3)), scorecardRead.getCourse().getParValues());
//        } catch (JsonProcessingException e) {
//            fail(e.getMessage());
//        }
//    }
//}
