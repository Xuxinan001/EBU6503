package Entity;

public class Modules {
    private double gpa;
    private double grade;
    private String moduleName;
    private String moduleId;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    private int credit;

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "gpa=" + gpa +
                ", grade=" + grade +
                ", moduleName='" + moduleName + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", credit=" + credit +
                '}';
    }
}

