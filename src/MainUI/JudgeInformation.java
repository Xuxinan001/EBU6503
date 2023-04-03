package MainUI;
public class JudgeInformation {
    public static String judgeBlank(String name) {
        if(name.length() == 0) {
            return "×  No blank!"; // name contains blank;
        }
        return " ";
    }

    public static String judgeId(String id) {
        if(id.length() != 10) {
            return "×  length error!"; // name too short!
        }
        for(int i = 0; i < id.length(); i++) {
            if(!Character.isDigit(id.charAt(i))) {
                return "×  Only number!";
            }
        }
        return " ";
    }

    public static String judgePassword(String password) {
        if(password.contains(" ")) {
            return "×  No blank!"; // name contains blank;
        }
        if(password.length() == 0) {
            return "×  No input!";
        }
        return " ";
    }

}
