package MainUI;

import Entity.Skills;
import Extra_curriculum_performed.HandleJsonSkills;
import Extra_curriculum_performed.IO;
import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkillsFrame extends JFrame {
    private String current_studentId=Login.studentSelected.getStudentId();

    public String getCurrent_studentId() {
        return current_studentId;
    }

    public void setCurrent_studentId(String current_studentId) {
        this.current_studentId = current_studentId;
    }

    private final int NUM_OF_SKILL_PROPERTY=3;
    /**
     * The width of the entire window.
     */
    private final int WINDOW_WIDTH=1000;
    /**
     * The height of the entire window.
     */
    private final int WINDOW_HEIGHT=800;
    private final int SIDEBAR_WIDTH=200;

    public  List<Skills> listOfSkills = null;

    public List<Skills> wholeListOfSkills;

    public List<Skills> SkillsInit(){
        try {
            wholeListOfSkills = HandleJsonSkills.getInstance().skills;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<Skills> tempList=new ArrayList<>();
        for(Skills skill : wholeListOfSkills){
            if(skill.getStudentId().equals(this.current_studentId)){
                tempList.add(new Skills(skill.getStudentId(), skill.getSkillName(), skill.getPriority(), skill.isMajority()));
            }
        }
        return tempList;
    }

    public JPanel extraJpanel(){
        listOfSkills=SkillsInit();
        JPanel jPanel=new JPanel();
        jPanel.setBounds(200,0,WINDOW_WIDTH-SIDEBAR_WIDTH,WINDOW_HEIGHT);

        String[] header={"skill name","priority","majority or not"};
        int tableHeight=listOfSkills.size();
        String[][] content=new String[tableHeight][NUM_OF_SKILL_PROPERTY];
        for(int i=0;i<tableHeight;i++){
            content[i][0]=listOfSkills.get(i).getSkillName();
            content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
            content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
        }
        JTable jTable=new JTable(content,header);
        jTable.setRowHeight(30);
        jTable.setBounds(0,100,100,100);
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(20,100,800,600);
        JButton priorityButton=new JButton("Ordered by Priority");
        priorityButton.setBounds(50,400,150,50);
        priorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfSkills=SkillsInit();
                listOfSkills.sort(new Comparator<Skills>() {
                    @Override
                    public int compare(Skills s1, Skills s2) {
                        return s1.getPriority()-s2.getPriority();
                    }
                });
                for(int i=0;i<tableHeight;i++){
                    content[i][0]=listOfSkills.get(i).getSkillName();
                    content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
                    content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
                }
                jTable.repaint();
            }
        });

        JButton majorityButton=new JButton("Majority Skills First");
        majorityButton.setBounds(220,400,150,50);
        majorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfSkills=SkillsInit();
                listOfSkills.sort(new Comparator<Skills>() {
                    @Override
                    public int compare(Skills s1, Skills s2) {
                        return s1.isMajority()?-1:1;
                    }
                });
                for(int i=0;i<tableHeight;i++){
                    content[i][0]=listOfSkills.get(i).getSkillName();
                    content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
                    content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
                }
                jTable.repaint();
            }
        });

        JButton addButton=new JButton("Add a Skill");
        addButton.setBounds(390,400,150,50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog=new JDialog(SkillsFrame.this,"Add a Skill");
                jDialog.setBounds(0,0,500,250);
                jDialog.setResizable(false);
                jDialog.setLocationRelativeTo(null);
                jDialog.setLayout(null);

                JLabel jLabelSkillName=new JLabel("Skill Name:",JLabel.LEFT);
                jLabelSkillName.setBounds(20,20,120,25);

                JLabel jLabelSkillPriority=new JLabel("Skill Priority:",JLabel.LEFT);
                jLabelSkillPriority.setBounds(20,60,120,25);

                JLabel jLabelSkillMajority=new JLabel("Skill Majority:",JLabel.LEFT);
                jLabelSkillMajority.setBounds(20,100,120,25);

                JTextField jTextFieldSkillName=new JTextField();
                jTextFieldSkillName.setBounds(160,20,200,25);

                JTextField jTextFieldSkillPriority=new JTextField();
                jTextFieldSkillPriority.setBounds(160,60,200,25);


                JPanel jPanel=new JPanel();
                jPanel.setBounds(160,100,200,25);
                ButtonGroup buttonGroup=new ButtonGroup();
                JRadioButton b1=new JRadioButton("true",true);
                JRadioButton b2=new JRadioButton("false");
                buttonGroup.add(b1);
                buttonGroup.add(b2);
                jPanel.add(b1);
                jPanel.add(b2);

                JButton OKButton=new JButton("OK");
                OKButton.setBounds(120,150,260,40);
                OKButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!jTextFieldSkillPriority.getText().matches("[0-9]")){
                            JOptionPane.showMessageDialog(null,"Priority can only be a number from 0 to 9","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            Skills newSkill=new Skills(SkillsFrame.this.getCurrent_studentId(),jTextFieldSkillName.getText(),Integer.parseInt(jTextFieldSkillPriority.getText()),b1.isSelected());
                            wholeListOfSkills.add(newSkill);
                            try {
                                System.out.println(JSON.toJSONString(wholeListOfSkills));
                                IO.write("Skills.json",JSON.toJSONString(wholeListOfSkills));
                                HandleJsonSkills.getInstance().commit();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            jDialog.dispose();

                        }
                    }
                });

                jDialog.add(jLabelSkillName);
                jDialog.add(jLabelSkillMajority);
                jDialog.add(jLabelSkillPriority);
                jDialog.add(jTextFieldSkillName);
                jDialog.add(jTextFieldSkillPriority);
//                jDialog.add(jTextFieldSkillMarjority);
                jDialog.add(jPanel);
                jDialog.add(OKButton);
                jDialog.setVisible(true);
            }
        });

        JButton delButton=new JButton("Delete a Skill");
        delButton.setBounds(560,400,150,50);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog=new JDialog(SkillsFrame.this,"Delete Skills");
                jDialog.setBounds(0,0,500,250);
                jDialog.setResizable(false);
                jDialog.setLocationRelativeTo(null);
                jDialog.setLayout(null);

                JLabel jLabelSkillName=new JLabel("Skill Name:",JLabel.LEFT);
                jLabelSkillName.setBounds(20,60,120,25);

                JTextField jTextFieldSkillName=new JTextField();
                jTextFieldSkillName.setBounds(160,60,200,25);

                JButton OKButton=new JButton("OK");
                OKButton.setBounds(120,150,260,40);
                OKButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int i=0;i<wholeListOfSkills.size();i++){
                            Skills tempSkills=wholeListOfSkills.get(i);
                            if(tempSkills.getStudentId().equals(SkillsFrame.this.getCurrent_studentId())&&tempSkills.getSkillName().toLowerCase().equals(jTextFieldSkillName.getText().toLowerCase())){
                                wholeListOfSkills.remove(tempSkills);
                            }
                        }
                        try {
                            System.out.println(JSON.toJSONString(wholeListOfSkills));
                            IO.write("Skills.json",JSON.toJSONString(wholeListOfSkills));
                            HandleJsonSkills.getInstance().commit();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        jDialog.dispose();
                    }
                });

                jDialog.add(jLabelSkillName);
                jDialog.add(jTextFieldSkillName);
                jDialog.add(OKButton);
                jDialog.setVisible(true);
            }
        });

        jPanel.add(priorityButton);
        jPanel.add(majorityButton);
        jPanel.add(addButton);
        jPanel.add(delButton);
        jPanel.add(jScrollPane);

        jPanel.setLayout(null);
        jPanel.setVisible(true);

        return jPanel;
    }

    public SkillsFrame() throws HeadlessException {
//        listOfSkills=SkillsInit();
//
//        String[] header={"skill name","priority","majority or not"};
//        int tableHeight=listOfSkills.size();
//        String[][] content=new String[tableHeight][NUM_OF_SKILL_PROPERTY];
//        for(int i=0;i<tableHeight;i++){
//            content[i][0]=listOfSkills.get(i).getSkillName();
//            content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
//            content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
//        }
//        JTable jTable=new JTable(content,header);
//        jTable.setRowHeight(30);
//        jTable.setBounds(100,100,100,100);
//        JScrollPane jScrollPane=new JScrollPane(jTable);
//        jScrollPane.setBounds(200,100,800,600);
//
//        JButton priorityButton=new JButton("Ordered by Priority");
//        priorityButton.setBounds(250,400,150,50);
//        priorityButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                listOfSkills=SkillsInit();
//                listOfSkills.sort(new Comparator<Skills>() {
//                    @Override
//                    public int compare(Skills s1, Skills s2) {
//                        return s1.getPriority()-s2.getPriority();
//                    }
//                });
//                for(int i=0;i<tableHeight;i++){
//                    content[i][0]=listOfSkills.get(i).getSkillName();
//                    content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
//                    content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
//                }
//                jTable.repaint();
//            }
//        });
//
//        JButton majorityButton=new JButton("Majority Skills First");
//        majorityButton.setBounds(420,400,150,50);
//        majorityButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                listOfSkills=SkillsInit();
//                listOfSkills.sort(new Comparator<Skills>() {
//                    @Override
//                    public int compare(Skills s1, Skills s2) {
//                        return s1.isMajority()?-1:1;
//                    }
//                });
//                for(int i=0;i<tableHeight;i++){
//                    content[i][0]=listOfSkills.get(i).getSkillName();
//                    content[i][1]=String.valueOf(listOfSkills.get(i).getPriority());
//                    content[i][2]=String.valueOf(listOfSkills.get(i).isMajority());
//                }
//                jTable.repaint();
//            }
//        });
//
//        JButton addButton=new JButton("Add a Skill");
//        addButton.setBounds(590,400,150,50);
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JDialog jDialog=new JDialog(SkillsFrame.this,"Add a Skill");
//                jDialog.setBounds(0,0,500,250);
//                jDialog.setResizable(false);
//                jDialog.setLocationRelativeTo(null);
//                jDialog.setLayout(null);
//
//                JLabel jLabelSkillName=new JLabel("Skill Name:",JLabel.LEFT);
//                jLabelSkillName.setBounds(20,20,120,25);
//
//                JLabel jLabelSkillPriority=new JLabel("Skill Priority:",JLabel.LEFT);
//                jLabelSkillPriority.setBounds(20,60,120,25);
//
//                JLabel jLabelSkillMajority=new JLabel("Skill Majority:",JLabel.LEFT);
//                jLabelSkillMajority.setBounds(20,100,120,25);
//
//                JTextField jTextFieldSkillName=new JTextField();
//                jTextFieldSkillName.setBounds(160,20,200,25);
//
//                JTextField jTextFieldSkillPriority=new JTextField();
//                jTextFieldSkillPriority.setBounds(160,60,200,25);
//
//
//                JPanel jPanel=new JPanel();
//                jPanel.setBounds(160,100,200,25);
//                ButtonGroup buttonGroup=new ButtonGroup();
//                JRadioButton b1=new JRadioButton("true",true);
//                JRadioButton b2=new JRadioButton("false");
//                buttonGroup.add(b1);
//                buttonGroup.add(b2);
//                jPanel.add(b1);
//                jPanel.add(b2);
//
//                JButton OKButton=new JButton("OK");
//                OKButton.setBounds(120,150,260,40);
//                OKButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if(!jTextFieldSkillPriority.getText().matches("[0-9]")){
//                            JOptionPane.showMessageDialog(null,"Priority can only be a number from 0 to 9","Warning",JOptionPane.WARNING_MESSAGE);
//                        }
//                        else {
//                            Skills newSkill=new Skills(SkillsFrame.this.getCurrent_studentId(),jTextFieldSkillName.getText(),Integer.parseInt(jTextFieldSkillPriority.getText()),b1.isSelected());
//                            wholeListOfSkills.add(newSkill);
//                            try {
//                                System.out.println(JSON.toJSONString(wholeListOfSkills));
//                                IO.write("Skills.json",JSON.toJSONString(wholeListOfSkills));
//                                HandleJsonSkills.getInstance().commit();
//                            } catch (IOException ioException) {
//                                ioException.printStackTrace();
//                            }
//                            jDialog.dispose();
//
//                        }
//                    }
//                });
//
//                jDialog.add(jLabelSkillName);
//                jDialog.add(jLabelSkillMajority);
//                jDialog.add(jLabelSkillPriority);
//                jDialog.add(jTextFieldSkillName);
//                jDialog.add(jTextFieldSkillPriority);
////                jDialog.add(jTextFieldSkillMarjority);
//                jDialog.add(jPanel);
//                jDialog.add(OKButton);
//                jDialog.setVisible(true);
//            }
//        });
//
//        JButton delButton=new JButton("Delete a Skill");
//        delButton.setBounds(760,400,150,50);
//        delButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JDialog jDialog=new JDialog(SkillsFrame.this,"Delete Skills");
//                jDialog.setBounds(0,0,500,250);
//                jDialog.setResizable(false);
//                jDialog.setLocationRelativeTo(null);
//                jDialog.setLayout(null);
//
//                JLabel jLabelSkillName=new JLabel("Skill Name:",JLabel.LEFT);
//                jLabelSkillName.setBounds(20,60,120,25);
//
//                JTextField jTextFieldSkillName=new JTextField();
//                jTextFieldSkillName.setBounds(160,60,200,25);
//
//                JButton OKButton=new JButton("OK");
//                OKButton.setBounds(120,150,260,40);
//                OKButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        for(int i=0;i<wholeListOfSkills.size();i++){
//                            Skills tempSkills=wholeListOfSkills.get(i);
//                            if(tempSkills.getStudentId().equals(SkillsFrame.this.getCurrent_studentId())&&tempSkills.getSkillName().toLowerCase().equals(jTextFieldSkillName.getText().toLowerCase())){
//                                wholeListOfSkills.remove(tempSkills);
//                            }
//                        }
//                        try {
//                            System.out.println(JSON.toJSONString(wholeListOfSkills));
//                            IO.write("Skills.json",JSON.toJSONString(wholeListOfSkills));
//                            HandleJsonSkills.getInstance().commit();
//                        } catch (IOException ioException) {
//                            ioException.printStackTrace();
//                        }
//                        jDialog.dispose();
//                    }
//                });
//
//                jDialog.add(jLabelSkillName);
//                jDialog.add(jTextFieldSkillName);
//                jDialog.add(OKButton);
//                jDialog.setVisible(true);
//            }
//        });
//
//
//        JPanel sidebar = new JPanel();
//        sidebar.setBounds(0,0,SIDEBAR_WIDTH, WINDOW_HEIGHT);
//        sidebar.setBackground(Color.LIGHT_GRAY);
//
//        JPanel mainPanel=new JPanel();
//        mainPanel.setBounds(200,0,WINDOW_WIDTH-SIDEBAR_WIDTH,WINDOW_HEIGHT);
//
//        this.add(priorityButton);
//        this.add(majorityButton);
//        this.add(addButton);
//        this.add(delButton);
//        this.add(jScrollPane);
//
//        this.add(sidebar);
//        this.add(mainPanel);
//
//        //this.setLocationRelativeTo(null);
//        this.setLayout(null);
//        this.setBounds(400,200,WINDOW_WIDTH,WINDOW_HEIGHT);
//        this.setResizable(false);
//        this.setVisible(true);
//        this.setTitle("Skills");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SkillsFrame(String current_studentId) throws HeadlessException {
        this.current_studentId = current_studentId;
    }
}
