package Extra_curriculum_performed;
import Entity.*;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //
             List<String> myList = new ArrayList<>();
        myList.add("001");
        //Student student=new Student("2020213574","1234",null,"xuxinan@bupt.edu.cn","IOT","Xinan Xu","2020215118",myList,"an SCI I--the first author","miniproject--about lost and found", null, null);
        Student student2=new Student("2020213573","1234",null,"xiangyifan@bupt.edu.cn","IOT","Yifan Xiang","2020215118",myList,"an SCI I--the first author","miniproject--about lost and found", null, null);
        //Student student1=new Student("2020213888","1234",null,"dashuaige@bupt.edu.cn","IOT","Shuaige Da","2020215118","an SCI I--the second author");
        //先写点数据
        ArrayList<Student> students=new ArrayList<Student>();
        students.add(student2);
        IO.write("Student.json",    JSON.toJSONString(students));
    }
}
