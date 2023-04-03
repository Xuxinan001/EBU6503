package Entity;

import Extra_curriculum_performed.HandleJson;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static Extra_curriculum_performed.ExtraMain.student;

public class Student {
    private String studentId;
    private String password;
    private Modules modules;
    private String email;
    private String major;
    private String studentName;
    private String classId;
    private List FindList;
    private String research;
    private String project;
    private ArrayList<String> achievement;
    private ArrayList<String> selectList;


    public Student(String studentId, String password, Modules modules, String email, String major, String studentName, String classId, List findList, String research, String project, ArrayList<String> achievement, ArrayList<String> selectList) {
        this.studentId = studentId;
        this.password = password;
        this.modules = modules;
        this.email = email;
        this.major = major;
        this.studentName = studentName;
        this.classId = classId;
        FindList = findList;
        this.research = research;
        this.project = project;
        this.achievement = achievement;
        this.selectList = selectList;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List getFindList() {
        return FindList;
    }

    public void setFindList(List findList) {
        FindList = findList;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public ArrayList<String> getAchievement() {
        return achievement;
    }

    public void setAchievement(ArrayList<String> achievement) {
        this.achievement = achievement;
    }

    public ArrayList<String> getSelectList() {
        return selectList;
    }

    public void setSelectList(ArrayList<String> selectList) {
        this.selectList = selectList;
    }

    public ArrayList<String> selectAchievement(int[] num) {
        try {
            ArrayList<Student> students = HandleJson.getInstance().Students;
            for (Student student1 : students) {
                if (student1.getStudentId().equals("2020213574")) {
                    student = student1;
                }
            }
            ArrayList<String> list = student.getAchievement();
            ArrayList<String> list1 = new ArrayList<String>();
            for(int i=0;i<num.length;i++){
                list1.add(list.get(num[i]));
            }
            return list1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", modules=" + modules +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", studentName='" + studentName + '\'' +
                ", classId='" + classId + '\'' +
                ", FindList=" + FindList +
                ", research='" + research + '\'' +
                ", project='" + project + '\'' +
                ", achievement=" + achievement +
                ", selectList=" + selectList +
                '}';
    }
}
