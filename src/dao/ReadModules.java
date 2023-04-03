package dao;

import com.alibaba.fastjson.JSONArray;
import Entity.Modules;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadModules {
    private String studentId;

    public ReadModules(String studentId) {
        this.studentId = studentId;
    }

    public List<Modules> getModulesList(String studentId) throws IOException {
        File file=new File("C:/Users/XiangYF/Documents/softwareEngineeringGroupwork_task7/Data/Modules_"+studentId+".json");
        String jsonString=nioMethod(file);
        JSONArray jsonArray=JSONArray.parseArray(jsonString);
        List<Modules> ModulesList=JSONObject.parseArray(jsonArray.toJSONString(),Modules.class);
        return ModulesList;
    }

    private  static String nioMethod(File file) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
        return jsonString;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
