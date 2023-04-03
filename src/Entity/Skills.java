package Entity;

import java.util.List;

public class Skills {
    private String studentId;
    private String skillName;

    private int priority;

    private boolean majority;
    private static List<Skills> listOfSkills = null;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillsName) {
        this.skillName = skillsName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isMajority() {
        return majority;
    }

    public void setMajority(boolean majority) {
        this.majority = majority;
    }

    public Skills() {

    }

    public Skills(String studentId, String skillName, int priority, boolean majority) {
        this.studentId = studentId;
        this.skillName = skillName;
        this.priority = priority;
        this.majority = majority;
    }


//    @Override
//    public String toString() {
//        return "Skills{" +
//                "studentId='" + studentId + '\'' +
//                ", skillName='" + skillName + '\'' +
//                ", priority=" + priority +
//                ", majority=" + majority +
//                '}';
//    }

    //    public static void whenJavaList_thanConvertToJsonCorrect() {
//        String jsonOutput= JSON.toJSONString(listOfSkills);
//        System.out.println(jsonOutput);
//    }
}
