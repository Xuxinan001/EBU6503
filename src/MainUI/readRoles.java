package MainUI;
import java.io.*;
import javax.swing.*;

public class readRoles {
   /*public static void main(String[] args) {
        readRoles r = new readRoles();
        String fileP = "/Users/huanghaofeng/Desktop/RolesUndertaken/groupwork/student.json";
        String text = r.reader(fileP);
        r.split(text);
    }*/

    public String reader(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                String content = "";
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineT = bufferedReader.readLine();
                while (lineT != null) {
                    content = content + lineT;
                    lineT = bufferedReader.readLine();
                }
                bufferedReader.close();
                return content;
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            System.out.println("Cannot find the file!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file content!");
            e.printStackTrace();
        }
        return null;
    }

    public void split(String text, JPanel panel, JFrame frame) {
        int Index = text.indexOf("{");
        int y = 106;
        do{
            y = y + 44;
            int RolesIndex = text.indexOf("rolesUndertaken", Index);
            int RolesIndex_2 = text.indexOf(",", RolesIndex);
            String role = text.substring(RolesIndex + 19, RolesIndex_2 - 1);
        
            int TIndex = text.indexOf("Experience Period", RolesIndex_2);
            int TIndex_2 = text.indexOf("}", TIndex);
            Index = TIndex_2;
            String role1 = text.substring(TIndex + 21, TIndex_2 - 3);

            //System.out.println(role + "  " + role1);

            //System.out.println(text.substring(Index + 2, Index + 3));
            JButton btnNewButton = new JButton(role);
		    btnNewButton.setBounds(16, y, 107, 27);
		    panel.add(btnNewButton);
            btnNewButton.addActionListener(e -> {
                //frame.dispose();
                ReadComment rco = new ReadComment();
                rco.rc(role);
            });

            JButton btnNewButton_1 = new JButton(role1);
		    btnNewButton_1.setBounds(256, y, 177, 27);
		    panel.add(btnNewButton_1);
            btnNewButton_1.addActionListener(e -> {
                //frame.dispose();
                ReadComment rco = new ReadComment();
                rco.rc(role);
            });
        }while(!text.substring(Index + 2, Index + 3).equals("]"));
    }
}

/*,
		{
			"studentID": 170,
			"studentName": "Bob",
			"rolesUndertaken": "Volunteer",
			"Experience Period": "2020.01.18 ~ 2020.3.30"
		} */

