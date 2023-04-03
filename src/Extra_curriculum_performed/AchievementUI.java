package Extra_curriculum_performed;

import MainUI.*;
import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AchievementUI {
    private static AchievementUI instance;

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

    private static final int WIDTH_MARGIN = 800-700>>1;

    private static final int CONTENT_WIDTH = 700;
    private static final int AREA_HEIGHT=200;
    private static final int AREA_WIDTH=500;
    private static final int HEAD_HEIGHT=100;
    private static final int BUTTON_HEIGHT=50;
    private static final int BUTTON_WIDTH=100;

    private JTextArea researchArea;

    private JTextArea achievementArea;

    private JDialog dialog;


    public JPanel configExtraMain(){
        AchievementUI.instance = this;

        // Configure window ExtraUI.
//        window = new JFrame("BUPT");//窗口名字
//        window.setLocationRelativeTo(null);//位置不变
//        window.setResizable(false);//大小不变
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
//        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(WINDOW_LEFT_WIDTH, WINDOW_HEIGHT));//设置长宽高
//        window.add(leftPanel);//添加
//        window.pack();//调整大小
        leftPanel.setBackground(new Color(0, 0, 0));
        leftPanel.setLayout(null);
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(WINDOW_WIDTH-WINDOW_LEFT_WIDTH, WINDOW_HEIGHT));
//        window.add(rightPanel);
//        window.pack();//调整大小
        rightPanel.setBackground(new Color(238,238,238));
        rightPanel.setLayout(null);
        rightPanel.add(AchievementUI.textFieldInit("Extra_curriculum_performed", "", JTextField.CENTER, Font.BOLD,
                WINDOW_LEFT_WIDTH, 10, CONTENT_WIDTH-100, HEAD_HEIGHT, 33, false,
                false,new Color(255,0,0)));
        int currentHeight = HEAD_HEIGHT ;
        rightPanel.add(AchievementUI.jLabelInit(new ImageIcon("./Resource/fabiaolunwen.png"),WINDOW_LEFT_WIDTH,currentHeight,WIDTH_MARGIN,CONTENT_HEIGHT));
/*        rightPanel.add(ExtraUI.textFieldInit("research", "", JTextField.LEFT, Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, CONTENT_WIDTH, CONTENT_HEIGHT, 30, false,
                false,new Color(0,0,0)));
        currentHeight+=CONTENT_HEIGHT;
*/        rightPanel.add(AchievementUI.textFieldInit("achievement", "", JTextField.LEFT, Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, CONTENT_WIDTH, CONTENT_HEIGHT, 30, false,
                false,new Color(0,0,0)));
        currentHeight+=CONTENT_HEIGHT;
/*        researchArea=ExtraUI.textAreaInit("",  Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, 20,  true,ExtraMain.student.getResearch());
        rightPanel.add(researchArea);
        currentHeight+=AREA_HEIGHT;
        rightPanel.add(ExtraUI.initButton("Save", (WINDOW_HEIGHT>>1)-(BUTTON_WIDTH>>1), currentHeight,
                BUTTON_WIDTH , BUTTON_HEIGHT, 25, event -> {
                    try {
                        save();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
*/       // System.out.println(WINDOW_LEFT_WIDTH+(WINDOW_HEIGHT>>1)-(BUTTON_WIDTH>>1));
        achievementArea= AchievementUI.textAreaInit("",  Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, 20,  true, Login.studentSelected.getAchievement());
        rightPanel.add(achievementArea);
        currentHeight+=AREA_HEIGHT;
        rightPanel.add(AchievementUI.initButton("Save", 250, currentHeight,
                BUTTON_WIDTH , BUTTON_HEIGHT, 25, event -> {
                    try {
                          save();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        rightPanel.add(AchievementUI.initButton("Select", 450, currentHeight,
                125 , BUTTON_HEIGHT, 25, event -> {
                    try {
                            String str = showDialog();
                            select(str);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        rightPanel.add(AchievementUI.initButton("List", 250, currentHeight+75,
                100 , BUTTON_HEIGHT, 25, event -> {
                    try {
                        showList();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        return rightPanel;
    }
    private String showDialog(){

        dialog = new JDialog(new Frame(), true);

        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        dialog.setLayout(null);

        dialog.setSize(300,180);

        dialog.setTitle("DialogTest");

        JTextField text=new JTextField();

        text.setSize(100,100);

        JButton button=new JButton("confirm");

        button.setBounds(160,40,80,40);

        button.addActionListener(event->{dialog.dispose();});

        dialog.add(text);

        dialog.add(button);

        dialog.setLocationRelativeTo(null);

        dialog.setVisible(true); //显示对话框，窗口阻塞，不往下执行，只有等到对话框关闭了才往下执行。

        return text.getText();


    }
    
    public static AchievementUI getInstance() {
        if (AchievementUI.instance == null)
            AchievementUI.instance = new AchievementUI();
        return AchievementUI.instance;
    }
    public void setVisibleStatus(Boolean status) {
        window.setLocationRelativeTo(null);
        window.setVisible(status);
    }

    public static JTextField textFieldInit(String content, String fontName, int alignment, int fontStyle, int x, int y,
                                      int width, int height, int fontSize, boolean opaqueStatus, boolean editable,Color color) {
        JTextField textField = new JTextField(content);
        textField.setHorizontalAlignment(alignment);
        textField.setBounds(x, y, width, height);
        textField.setEditable(editable);
        textField.setOpaque(opaqueStatus);
        textField.setBorder(null);
        textField.setFont(new Font(fontName, fontStyle, fontSize));
        textField.setForeground(color);
        return textField;
    }
    public static JLabel jLabelInit(ImageIcon imageIcon, int x, int y,
                                           int width, int height) {
        JLabel jLabel = new JLabel(imageIcon);

        jLabel.setBounds(x, y, width, height);

        jLabel.setBorder(null);

        return jLabel;
    }
    public static JTextArea textAreaInit(String fontName, int fontStyle, int x, int y, int fontSize, boolean editable,ArrayList<String> text) {
        JTextArea textArea= new JTextArea(AREA_WIDTH, AREA_HEIGHT);
        textArea.setBounds(x, y, AREA_WIDTH, AREA_HEIGHT);
        textArea.setLineWrap(true);
        textArea.setEditable(editable);
        textArea.setFont(new Font(fontName, fontStyle, fontSize));
        String text_str = StringUtils.join(text,",");//将arraylist转换为string
        textArea.setText(text_str.replace("[","").replace("]",""));
        return textArea;
    }
    public static JButton initButton(String content, int x, int y, int xSize, int ySize, int fontSize,
                                     ActionListener event) {
        JButton button = new JButton(content);
        button.setBounds(x, y, xSize, ySize);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));//设置光标 变成小手
        button.addActionListener(event);
        button.setFocusable(false);
        return button;
    }
    private void save() throws IOException {
/*        String researchStr=researchArea.getText();
        ExtraMain.student.setResearch(researchStr);
        HandleJson.getInstance().commit();
*/      String text_str=achievementArea.getText();
        ArrayList<String>list= new ArrayList<String>(Arrays.asList(text_str.split(",")));//将字符串转换成arraylist
        Login.studentSelected.setAchievement(list);
        HandleJson.getInstance().commit();
    }
    public void select(String str) throws  IOException{
        ArrayList<String>numList= new ArrayList<String>(Arrays.asList(str.split(",")));
        int[] num = new int[numList.size()];
        for(int i=0;i<numList.size();i++)
        {
            num[i]=Integer.parseInt(numList.get(i));//将字符变成数字
        }
        ArrayList<String> list= Login.studentSelected.selectAchievement(num);
        String text=StringUtils.join(list,",");
        achievementArea.setText(text.replace("[","").replace("]",""));
        Login.studentSelected.setSelectList(list);
        HandleJson.getInstance().commit();
    }
    private void showList() throws IOException {
/*        String researchStr=researchArea.getText();
        ExtraMain.student.setResearch(researchStr);
        HandleJson.getInstance().commit();
*/
        String text_str=StringUtils.join(Login.studentSelected.getSelectList(),",");
        achievementArea.setText(text_str.replace("[","").replace("]","").replace("\"",""));
    }
}
