package Extra_curriculum_performed;

import Entity.Skills;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;

public class HandleJsonSkills {
    private static HandleJsonSkills instance = null;

    /**
     * get the unique instance of HandleJson
     * @return reference of HandleJson object
     * @throws IOException
     */
    public static HandleJsonSkills getInstance() throws IOException {
        if (instance == null) {
            instance = new HandleJsonSkills();
        }
        return instance;
    }

    public ArrayList<Skills> skills;


    /**
     * HandleJson constructor
     * @throws IOException
     */
    private HandleJsonSkills() throws IOException {

        /**
         * Read information which are stored in JSON files.
         */
        skills    = (ArrayList<Skills>) JSON.parseArray(IO.read("Skills.json"),    Skills.class);
    }

    /**
     * write all data from memory into disk (files)
     * @throws IOException
     */
    public void commit() throws IOException {
        IO.write("Skills.json", JSON.toJSONString(skills));
    }

}
