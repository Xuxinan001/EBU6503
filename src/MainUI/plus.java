package MainUI;
import java.io.*;

public class plus {
    public static void main(String arg[]){
        plus p = new plus();
        p.add();
    }
    public void add(){
        String contents = "";
        readRoles rd = new readRoles();
        contents = rd.reader("/Users/huanghaofeng/Desktop/RolesUndertaken/student.json");
        String newLine = "		{			\"rolesUndertaken\": \"hahah\",			\"Experience Period\": \"2020.11.18 ~ 2025.3.30\"		}";
        System.out.println(newLine);
        contents = contents + newLine;
        String fileName = "student.json";
        try {
            FileWriter fileWriter = new FileWriter(fileName); 
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); 
            bufferedWriter.write(contents);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Errors occured");
            System.exit(1); }
   } 
}
