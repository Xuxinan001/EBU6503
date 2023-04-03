package MainUI;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Roles extends JFrame {
     /**
     * A static constant holding the width of current window.
     */
    private static final int WINDOW_WIDTH = 1000;





    /**
     * A static constant holding the height of current window.
     */
    private static final int WINDOW_HEIGHT = 800;

    /**
     * A static constant holding the height of the vertical interval of contents of current window.
     */

    /**
     * A static {@code JFrame} holding the instance of current window.
     */
    private static JFrame window;

    private static final int WINDOW_LEFT_WIDTH = 200;
    private static final int CONTENT_HEIGHT = 50;

    public static Object[][] rowData=null;
    private static final int WIDTH_MARGIN = 800-700>>1;

    private static final int CONTENT_WIDTH = 700;
    private static final int AREA_HEIGHT=150;
    private static final int AREA_WIDTH=700;
    private static final int HEAD_HEIGHT=100;
    private static final int BUTTON_HEIGHT=50;
    private static final int BUTTON_WIDTH=100;

    private static final int Table_Height=150;

    public JPanel contentPane;
    private JTextField txtRolesUndertaken;
    private JTextField txtEmploymentPeriod;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Roles frame = new Roles();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void run() {
        try {
            Roles frame = new Roles();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel RolesJP() throws IOException {
        return contentPane;
    }

    /**
     * Create the frame.
     */
    public Roles() {
        /*JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(WINDOW_LEFT_WIDTH, WINDOW_HEIGHT));//设置长宽高
        //leftPanel.setBackground(new Color(0, 0, 0));
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.BLACK);
        JFrame.add(leftPanel);*/

        String id = "123";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 100, 1000, 800);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(173, 82, 700, 566);
        contentPane.add(panel);
        panel.setLayout(null);
        
        txtRolesUndertaken = new JTextField();
        txtRolesUndertaken.setEditable(false);
        txtRolesUndertaken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        txtRolesUndertaken.setText("Roles Undertaken");
        txtRolesUndertaken.setBounds(6, 106, 186, 32);
        panel.add(txtRolesUndertaken);
        txtRolesUndertaken.setColumns(10);
        
        txtEmploymentPeriod = new JTextField();
        txtEmploymentPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        txtEmploymentPeriod.setText("Employment Period");
        txtEmploymentPeriod.setEditable(false);
        txtEmploymentPeriod.setBounds(266, 106, 206, 32);
        panel.add(txtEmploymentPeriod);
        txtEmploymentPeriod.setColumns(10);
        
        JTextArea txtrRolesUndertaken = new JTextArea();
        txtrRolesUndertaken.setBackground(new Color(255, 255, 255));
        txtrRolesUndertaken.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
        txtrRolesUndertaken.setEditable(false);
        txtrRolesUndertaken.setText("   Roles Undertaken");
        txtrRolesUndertaken.setBounds(250, 43, 385, 137);
        contentPane.add(txtrRolesUndertaken);
        
        /*JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("/Users/huanghaofeng/Desktop/截屏2023-03-27 21.03.34.png"));
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(30, 25, 97, 87);
        contentPane.add(lblNewLabel);*/

        readRoles r = new readRoles();
        String fileP = "./Data/" + id + ".json";
        String text = r.reader(fileP);
        r.split(text, panel, this);
    }
}
