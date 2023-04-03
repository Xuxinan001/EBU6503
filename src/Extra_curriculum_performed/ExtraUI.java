package Extra_curriculum_performed;

import Entity.ScientificResearch;
import MainUI.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

public class ExtraUI {
    private static ExtraUI instance;

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

    private static final int WINDOW_LEFT_WIDTH = 20;
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

    private JTextArea researchArea;
    private JTextArea projectArea;


    public JPanel extraJpanel() throws IOException {
        ExtraUI.instance = this;

        // Configure window ExtraUI.
//        window = new JFrame("BUPT");//窗口名字
//        window.setLocationRelativeTo(null);//位置不变
//        window.setResizable(false);//大小不变
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
//        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        JPanel leftPanel = new JPanel();
//        leftPanel.setPreferredSize(new Dimension(WINDOW_LEFT_WIDTH, WINDOW_HEIGHT));//设置长宽高
//        window.add(leftPanel);//添加
//        window.pack();//调整大小
//        leftPanel.setBackground(new Color(0, 0, 0));
//        leftPanel.setLayout(null);
        JPanel rightPanel = new JPanel();

//    rightPanel.setPreferredSize(new Dimension(WINDOW_WIDTH-WINDOW_LEFT_WIDTH, WINDOW_HEIGHT));
        rightPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

//        window.add(rightPanel);
//        window.pack();//调整大小
        rightPanel.setBackground(new Color(238,238,238));
        rightPanel.setLayout(null);
        rightPanel.add(ExtraUI.textFieldInit("Extra_curriculum_performed", "", JTextField.CENTER, Font.BOLD,
                WINDOW_LEFT_WIDTH, 10, CONTENT_WIDTH, HEAD_HEIGHT, 33, false,
                false,new Color(255,0,0)));
        int currentHeight = HEAD_HEIGHT ;
        rightPanel.add(ExtraUI.jLabelInit(new ImageIcon("./Resource/fabiaolunwen.png"),WINDOW_LEFT_WIDTH,currentHeight,WIDTH_MARGIN,CONTENT_HEIGHT));
        rightPanel.add(ExtraUI.textFieldInit("research", "", JTextField.LEFT, Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, CONTENT_WIDTH, CONTENT_HEIGHT, 30, false,
                false,new Color(0,0,0)));
        currentHeight+=CONTENT_HEIGHT;
            //reasearch
        researchArea=ExtraUI.textAreaInit("",  Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, 20,  true, Login.studentSelected.getResearch());
        rightPanel.add(researchArea);
        currentHeight+=AREA_HEIGHT;
        rightPanel.add(ExtraUI.initButton("Save-r", (WINDOW_HEIGHT>>1)-(BUTTON_WIDTH>>1), currentHeight,
                BUTTON_WIDTH , BUTTON_HEIGHT, 25, event -> {
                    try {
                        saveR();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        currentHeight+=BUTTON_HEIGHT;
        rightPanel.add(ExtraUI.jLabelInit(new ImageIcon("./Resource/project.png"),WINDOW_LEFT_WIDTH,currentHeight,WIDTH_MARGIN,CONTENT_HEIGHT));
        rightPanel.add(ExtraUI.textFieldInit("project", "", JTextField.LEFT, Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, CONTENT_WIDTH, CONTENT_HEIGHT, 30, false,
                false,new Color(0,0,0)));
        currentHeight+=CONTENT_HEIGHT;
        //project
        projectArea=ExtraUI.textAreaInit("",  Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, 20,  true,Login.studentSelected.getProject());
        rightPanel.add(projectArea);
        currentHeight+=AREA_HEIGHT;
        rightPanel.add(ExtraUI.initButton("Save-p", (WINDOW_HEIGHT>>1)-(BUTTON_WIDTH>>1), currentHeight,
                BUTTON_WIDTH , BUTTON_HEIGHT, 25, event -> {
                    try {
                        saveP();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        currentHeight+=CONTENT_HEIGHT;
        //search

        rightPanel.add(ExtraUI.jLabelInit(new ImageIcon("./Resource/sousuo.png"),WINDOW_LEFT_WIDTH,currentHeight,WIDTH_MARGIN,CONTENT_HEIGHT));
        rightPanel.add(ExtraUI.textFieldInit("Find Research or Project", "", JTextField.LEFT, Font.PLAIN,
                WIDTH_MARGIN+WINDOW_LEFT_WIDTH, currentHeight, CONTENT_WIDTH, CONTENT_HEIGHT, 30, false,
                false,new Color(0,0,0)));
        currentHeight+=CONTENT_HEIGHT;
        rightPanel.add((ExtraUI.scrollPaneInit(WIDTH_MARGIN+WINDOW_LEFT_WIDTH,currentHeight,CONTENT_WIDTH,Table_Height)));
        return  rightPanel;


    }
    
//    public static ExtraUI getInstance() {
//        if (ExtraUI.instance == null)
//            ExtraUI.instance = new ExtraUI();
//        return ExtraUI.instance;
//    }
//    public void setVisibleStatus(Boolean status) {
//        window.setLocationRelativeTo(null);
//        window.setVisible(status);
//    }

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
    public static JTextArea textAreaInit(String fontName, int fontStyle, int x, int y, int fontSize, boolean editable,String text) {
        JTextArea textArea= new JTextArea(AREA_WIDTH, AREA_HEIGHT);
        textArea.setBounds(x, y, AREA_WIDTH, AREA_HEIGHT);
        textArea.setLineWrap(true);
        textArea.setEditable(editable);
        textArea.setFont(new Font(fontName, fontStyle, fontSize));
        textArea.setText(text);
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
    public static JScrollPane scrollPaneInit(int x ,int y,int xSize,int ySize ) throws IOException {
        String[] columnNames = {"Name", "Teacher", "Output", "Sign up","id"};

        // 表格所有行数据

        rowData=new Object[10][5];
        ArrayList<ScientificResearch> scientificResearches=HandleJson.getInstance().scientificResearches;
        for(int i=0;i<scientificResearches.size();i++){

            rowData[i][0]=scientificResearches.get(i).getSRname();
            rowData[i][1]=scientificResearches.get(i).getSRteacher();
            rowData[i][2]=scientificResearches.get(i).getSRoutput();
            rowData[i][3]=scientificResearches.get(i).getSRsign();
            rowData[i][4]=scientificResearches.get(i).getSRid();


        }
       // System.out.println(Arrays.stream(rowData).count());
        //System.out.println(rowData[0][1]);
        // 创建一个表格，指定 表头 和 所有行数据
//        JTable table = new JTable(rowData, columnNames);
        TableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        RowSorter sorter = new TableRowSorter(model);
        table.setRowSorter(sorter);


        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent event)
            {
                int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                scientificResearches.get(row).setSRsign((String) table.getValueAt(row,col));

               // System.out.println(row+col);
                //System.out.println((String) table.getValueAt(row,col));
                try {
                    List<String> myList = new ArrayList<>();
                    for(int i=0;i<scientificResearches.size();i++){
                        if(rowData[i][3]=="Y"){
                            myList.add((String) rowData[i][4]);
                        }
                    }
                    Login.studentSelected.setFindList(myList);

                    HandleJson.getInstance().commit();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });


        scrollPane.setBounds(x, y, xSize, ySize);
        return  scrollPane;
    }


    private void saveR() throws IOException {
        String researchStr=researchArea.getText();
        Login.studentSelected.setResearch(researchStr);
        HandleJson.getInstance().commit();
    }
    private void saveP() throws IOException {
        String projectStr=projectArea.getText();
        Login.studentSelected.setProject(projectStr);
        HandleJson.getInstance().commit();
    }
}
