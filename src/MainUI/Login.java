package MainUI;
import Entity.Student;

import javax.swing.*;

/**
 * Login Main function
 * @author Xiang Yifan
 * @version 1.2
 * I am very satisfied with my work!!!
 * I am very satisfied to myself!!!
 */

public class Login {

    public static Student studentSelected = null;

    public static void main(String[] args) {

        /**
         * UI
         */
        JFrame frame = new JFrame("Login");
        // Setting the width and height of frame
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        /*
          Set the panel, this panel is only for Login!
          Once we need to log in again (for instance log out),
          then call this panel.
         */
        JPanel panel_1 = new JPanel(); //Login
        frame.add(panel_1);
        LoginUI loginUI = new LoginUI();
        /*
          transmit a Login panel to the following function, it'll add UI on it.
         */
        loginUI.placeComponents(panel_1, frame);

        frame.setVisible(true);

    }
}