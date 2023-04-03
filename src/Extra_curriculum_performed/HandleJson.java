package Extra_curriculum_performed;

import com.alibaba.fastjson.JSON;
import Entity.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * manage for data
 * @author Xinan Xu
 * @version 1.0
 */
public class HandleJson {
    private static HandleJson instance = null;

    /**
     * get the unique instance of HandleJson
     * @return reference of HandleJson object
     * @throws IOException
     */
    public static HandleJson getInstance() throws IOException {
        if (instance == null) {
            instance = new HandleJson();
        }
        return instance;
    }

    public ArrayList<Student>    Students;
    public ArrayList<ScientificResearch> scientificResearches;


    /**
     * HandleJson constructor
     * @throws IOException
     */
    private HandleJson() throws IOException {

        /**
         * Read information which are stored in JSON files.
         */
        Students    = (ArrayList<Student>)    JSON.parseArray(IO.read("Student.json"),    Student.class);
        scientificResearches=(ArrayList<ScientificResearch>)JSON.parseArray(IO.read("FindResearchOrProject.json"),    ScientificResearch.class);
  }

    /**
     * write all data from memory into disk (files)
     * @throws IOException
     */
    public void commit() throws IOException {
        IO.write("Student.json",    JSON.toJSONString(Students));
        IO.write("FindResearchOrProject.json",    JSON.toJSONString(scientificResearches));


  }

}
