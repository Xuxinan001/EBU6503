package Extra_curriculum_performed;
import Entity.*;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraMain {
    public static Student student;
    public static void main(String[] args) throws IOException {
//        List<String> myList = new ArrayList<>();
//        myList.add("001");
//         Student student=new Student("2020213574","1234",null,"xuxinan@bupt.edu.cn","IOT","Xinan Xu","2020215118",myList,"an SCI I--the first author","miniproject--about lost and found");
//        //Student student1=new Student("2020213888","1234",null,"dashuaige@bupt.edu.cn","IOT","Shuaige Da","2020215118","an SCI I--the second author");
//        //先写点数据
//        ArrayList<Student> students=new ArrayList<Student>();
//        students.add(student);
//        IO.write("Student.json",    JSON.toJSONString(students));
        //上面成功了(得用数组)，下面添加一个数据,注意：必须设置get set方法 不然不行，我也不知道为啥
//        ArrayList<Student> students =  HandleJson.getInstance().Students;
//        students.add((student1));
//        HandleJson.getInstance().commit();
//        上面是json处理方法
        ArrayList<Student> students =  HandleJson.getInstance().Students;
        for(Student student1:students){
            if(student1.getStudentId().equals("2020213574")){
                student=student1;
            }
        }
//
//        ExtraUI.getInstance().configExtraMain();
//        ExtraUI.getInstance().setVisibleStatus(true);


    }
}
