package MainUI;
import Entity.*;
import Extra_curriculum_performed.AchievementUI;
import Extra_curriculum_performed.ExtraUI;
import Extra_curriculum_performed.HandleJson;
import Extra_curriculum_performed.IO;
import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * LoginUi
 * @author Xiang Yifan
 * @version 1.4
 */

public class LoginUI {

    /**
     * Once we need the login page, call this function to set up.
     * You need to input a Jpanel and the function is to operate on it.
     * ATTENTION!!!
     * Once you want to make changes to the background, pay attention to the location of the codes,
     * it will matter the COVER relationship!!!
     * @param panel
     * @param frame
     */

    public void placeComponents(JPanel panel, JFrame frame)
//    public void placeComponents(JPanel panel,JFrame frame)
    {
        Font f = new Font("Times New Roman", Font.BOLD, 30);
        panel.setLayout(null);

        // create JLabel
        JLabel userLabel = new JLabel("<html><body>"
                + "<p style='color :black; font-size:15px; font-family = 'Times New Roman''>Student Id:</p >"
                + "</body></html>");
        userLabel.setBounds(300,200,180,50);
        userLabel.setFont(f);
        panel.add(userLabel);
        /*
         * To input the student id.
         */
        JTextField userText = new JTextField(50);
        userText.setFont(f);
        userText.setBounds(420,200,220,50);
        panel.add(userText);

        // Password Label
        JLabel passwordLabel = new JLabel("<html><body>"
                + "<p style='color :black; font-size:15px; font-family = 'Times New Roman''>Password:</p >"
                + "</body></html>");
        passwordLabel.setBounds(300,270,150,50);
        passwordLabel.setFont(f);
        panel.add(passwordLabel);

        /*
         * The input text will be black points to ensure the security.
         */
        JPasswordField passwordText = new JPasswordField(50);
        passwordText.setBounds(420,270,220,50);
        passwordText.setFont(f);
        panel.add(passwordText);

        JLabel NoticeLabel = new JLabel("<html><body>"
                + "<p style='color :black; font-size:30px; font-family = 'Times New Roman''>Welcome to login!</p >"
                + "</body></html>");
        NoticeLabel.setFont(f);
        NoticeLabel.setBounds(350,50,300,60);
        panel.add(NoticeLabel);


        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 350, 340, 40);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Student> students = null;
                try {
                    students = HandleJson.getInstance().Students;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                int isCorrect = 0;
                int isStudentId = 0;
                for(Student student1:students) {
                    if(userText.getText().equals(student1.getStudentId()) &&
                            new String(passwordText.getPassword()).equals(student1.getPassword())) {
                        NoticeLabel.setText("<html><body>"
                                + "<p style='color :black; font-size:30px; font-family = 'Times New Roman''>Welcome to login!</p >"
                                + "</body></html>");
                        isCorrect = 1;
                        Login.studentSelected = student1;
                        break;
                    }
                }
                if(isCorrect == 1) {
                    /**
                     * Login successfully, the following will create two panels.
                     * The left one is for select and the right on is for display.
                     * The functions are realized EXCEPT BACK button.
                     * The UI should be more beautiful!!!
                     */
                    panel.setVisible(false);

                    MyForm myform = new MyForm();
                    Roles roles = new Roles();
                    ExtraUI extraUI = new ExtraUI();
                    AchievementUI achievementUI = new AchievementUI();
                    SkillsFrame skillsFrame = new SkillsFrame();
                    try {
                        JPanel panelLeft= new JPanel();
                        panelLeft.setLayout(null);
                        panelLeft.setPreferredSize(new Dimension(200,800));
                        panelLeft.setBackground(Color.black);
                        panelLeft.setLayout(null);

                        /**
                         * BUTTON for upload photos.
                         * ATTENTION!!!
                         * The NAMING of the photos needs to be improved!!!
                         */
                        JButton uploadPhotoButton = new JButton("upload");
                        uploadPhotoButton.setBounds(37, 65, 120, 40);
                        panelLeft.add(uploadPhotoButton);

                        /**
                         * BUTTON for back to the Login page.
                         */
                        JButton backToFirstPageButton = new JButton("BACK");
                        backToFirstPageButton.setBounds(37, 665, 120, 40);
                        panelLeft.add(backToFirstPageButton);

                        JLabel photoLabel;
                        photoLabel = new JLabel("");
                        photoLabel.setBounds(20, 20, 150, 150);
                        String studentPhotoPath = "./photos/" + userText.getText() + ".jpg";
                        File file = new File(studentPhotoPath);
                        ImageIcon studentPhotoIcon;
                        if(file.exists()){
                            uploadPhotoButton.setVisible(false);
                            studentPhotoIcon = new ImageIcon(studentPhotoPath);
                        }
                        else {
                            uploadPhotoButton.setVisible(true);
                            studentPhotoIcon = new ImageIcon("./photos/noPhoto.jpg");
                        }
                        studentPhotoIcon.setImage(studentPhotoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                        photoLabel.setIcon(studentPhotoIcon);
                        photoLabel.getIcon();
                        panelLeft.add(photoLabel);

                        uploadPhotoButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ImageIcon imageIcon = uploadPictureButton.addPicture(uploadPhotoButton);
                                if (imageIcon != null) {
                                    imageIcon.setImage(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                                    photoLabel.setIcon(imageIcon);
                                    photoLabel.getIcon();
                                    uploadPhotoButton.setVisible(false);
                                }
                            }
                        });

                        JLabel nameLabel = new JLabel("Name: ");
                        Font ff = new Font("Times New Roman", Font.BOLD, 15);
                        nameLabel.setFont(ff);
                        nameLabel.setForeground(Color.white);
                        nameLabel.setBounds(20,500,80,50);
                        panelLeft.add(nameLabel);
                        JLabel nameLabelContent = new JLabel(Login.studentSelected.getStudentName());
                        nameLabelContent.setFont(ff);
                        nameLabelContent.setForeground(Color.white);
                        nameLabelContent.setBounds(70,500,80,50);
                        panelLeft.add(nameLabelContent);

                        JLabel studentIdLabel = new JLabel("Student Id: ");
                        studentIdLabel.setFont(ff);
                        studentIdLabel.setForeground(Color.white);
                        studentIdLabel.setBounds(20,535,80,50);
                        panelLeft.add(studentIdLabel);
                        JLabel studentIdLabelContent = new JLabel(Login.studentSelected.getStudentId());
                        studentIdLabelContent.setFont(ff);
                        studentIdLabelContent.setForeground(Color.white);
                        studentIdLabelContent.setBounds(100,535,100,50);
                        panelLeft.add(studentIdLabelContent);

                        JLabel studentClassLabel = new JLabel("Class No: ");
                        studentClassLabel.setFont(ff);
                        studentClassLabel.setForeground(Color.white);
                        studentClassLabel.setBounds(20,570,80,50);
                        panelLeft.add(studentClassLabel);
                        JLabel studentClassLabelContent = new JLabel(Login.studentSelected.getClassId());
                        studentClassLabelContent.setFont(ff);
                        studentClassLabelContent.setForeground(Color.white);
                        studentClassLabelContent.setBounds(100,570,100,50);
                        panelLeft.add(studentClassLabelContent);

                        JLabel studentMajorLabel = new JLabel("Major: ");
                        studentMajorLabel.setFont(ff);
                        studentMajorLabel.setForeground(Color.white);
                        studentMajorLabel.setBounds(20,605,80,50);
                        panelLeft.add(studentMajorLabel);
                        JLabel studentMajorLabelContent = new JLabel(Login.studentSelected.getMajor());
                        studentMajorLabelContent.setFont(ff);
                        studentMajorLabelContent.setForeground(Color.white);
                        studentMajorLabelContent.setBounds(80,605,100,50);
                        panelLeft.add(studentMajorLabelContent);

                        JButton firstPartButton = new JButton("Part 1");
                        firstPartButton.setBounds(0, 200, 200, 50);
                        panelLeft.add(firstPartButton);
                        JButton secondPartButton = new JButton("Part 2");
                        secondPartButton.setBounds(0, 250, 200, 50);
                        panelLeft.add(secondPartButton);
                        JButton thirdPartButton = new JButton("Part 3");
                        thirdPartButton.setBounds(0, 300, 200, 50);
                        panelLeft.add(thirdPartButton);
                        JButton fourthPartButton = new JButton("Part 4");
                        fourthPartButton.setBounds(0, 350, 200, 50);
                        panelLeft.add(fourthPartButton);
                        JButton fifthPartButton = new JButton("Part 5");
                        fifthPartButton.setBounds(0, 400, 200, 50);
                        panelLeft.add(fifthPartButton);


                        JPanel panel1 = new JPanel();
                        JPanel panel2 = new JPanel();
                        JPanel panel3 = new JPanel();
                        JPanel panel4 = new JPanel();
                        JPanel panel5 = new JPanel();
                        // ljz
                        panel1 = myform.getStudentsRecord();
                        // xxn
                        panel2 = extraUI.extraJpanel();
                        //hhf
                        panel3 = roles.RolesJP();
                        //zym
                        panel4 = achievementUI.configExtraMain();
                        //jtw
                        panel5 = skillsFrame.extraJpanel();

                        panel1.setPreferredSize(new Dimension(800,800));
                        panel1.setLayout(null);
                        panel2.setPreferredSize(new Dimension(800,800));
                        panel2.setLayout(null);
                        panel3.setPreferredSize(new Dimension(800,800));
                        panel3.setLayout(null);
                        panel4.setPreferredSize(new Dimension(800,800));
                        panel4.setLayout(null);
                        panel5.setPreferredSize(new Dimension(800,800));
                        panel5.setLayout(null);
                        Container container = frame.getContentPane();

                        /**
                         * Must have this final ones!!!!
                         * OR THERE WLL BE ERRORS!!!
                         */
                        JPanel finalPanel1 = panel1;
                        JPanel finalPanel2 = panel2;
                        JPanel finalPanel3 = panel3;
                        JPanel finalPanel4 = panel4;
                        JPanel finalPanel5 = panel5;

                        firstPartButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                finalPanel1.setVisible(true);
                                finalPanel2.setVisible(false);
                                finalPanel3.setVisible(false);
                                finalPanel4.setVisible(false);
                                finalPanel5.setVisible(false);
                                container.add(finalPanel1,BorderLayout.EAST);
                            }
                        });

                        secondPartButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                finalPanel1.setVisible(false);
                                finalPanel2.setVisible(true);
                                finalPanel3.setVisible(false);
                                finalPanel4.setVisible(false);
                                finalPanel5.setVisible(false);
                                container.add(finalPanel2,BorderLayout.EAST);
                            }
                        });

                        thirdPartButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                finalPanel1.setVisible(false);
                                finalPanel2.setVisible(false);
                                finalPanel3.setVisible(true);
                                finalPanel4.setVisible(false);
                                finalPanel5.setVisible(false);
                                container.add(finalPanel3,BorderLayout.EAST);
                            }
                        });

                        fourthPartButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                finalPanel1.setVisible(false);
                                finalPanel2.setVisible(false);
                                finalPanel3.setVisible(false);
                                finalPanel4.setVisible(true);
                                finalPanel5.setVisible(false);
                                container.add(finalPanel4,BorderLayout.EAST);
                            }
                        });

                        fifthPartButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                finalPanel1.setVisible(false);
                                finalPanel2.setVisible(false);
                                finalPanel3.setVisible(false);
                                finalPanel4.setVisible(false);
                                finalPanel5.setVisible(true);
                                container.add(finalPanel5,BorderLayout.EAST);
                            }
                        });

                        container.add(panelLeft,BorderLayout.WEST);
                        container.add(finalPanel1,BorderLayout.EAST);

                        backToFirstPageButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panelLeft.setVisible(false);
                                finalPanel1.setVisible(false);
                                finalPanel2.setVisible(false);
                                finalPanel3.setVisible(false);
                                finalPanel4.setVisible(false);
                                finalPanel5.setVisible(false);
                                JPanel newLogin = new JPanel();
                                frame.add(newLogin);
                                placeComponents(newLogin, frame);
                            }
                        });

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
                else {
                    for (Student student1:students) {
                        if(userText.getText().equals(student1.getStudentId())) {
                            isStudentId = 1;
                            break;
                        }
                    }
                    if(isStudentId == 1) {
                        NoticeLabel.setText("<html><body>"
                                + "<p style='color:red; font-size:30px; font-family = 'Times New Roman''>Wrong Password</p >"
                                + "</body></html>");
                    }
                    else {
                        NoticeLabel.setText("<html><body>"
                                + "<p style='color: blue; font-size:25px; font-family = 'Times New Roman''>Wrong Student Id!</p >"
                                + "</body></html>");
                    }
                }
            }
        });

        //create cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(750, 700, 80, 25);
        panel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //create sign up button
        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(500, 420, 140, 40);
        panel.add(signUpButton);

        JLabel firstTimeLabel = new JLabel("<html><body>"
                + "<p style='color :black; font-size:13px; font-family = 'Times New Roman''>First time? Click right button!</p >"
                + "</body></html>");
        Font f2 = new Font("Times New Roman", Font.BOLD, 20);
        firstTimeLabel.setFont(f2);
        firstTimeLabel.setBounds(250,410,350,60);
        panel.add(firstTimeLabel);


        /**
         * Set up the background 1
         * ATTENTION!!!
         * Pay attention to the path!!!
         */
        JLabel backgroundLabel_1;
        backgroundLabel_1 = new JLabel("");
        backgroundLabel_1.setBounds(0, 0, 1000, 800);
        //String backgroundPath = "./background.jpg";
        String backgroundPath = "./Resource/loginBackground.png";
        ImageIcon backgroundIcon = new ImageIcon(backgroundPath);
        backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));
        backgroundLabel_1.setIcon(backgroundIcon);
        backgroundLabel_1.getIcon();
        panel.add(backgroundLabel_1);


        signUpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {


                NoticeLabel.setText("<html><body>"
                        + "<p style='color :black; font-size:25px; font-family = 'Times New Roman''>Welcome to Sign Up!</p >"
                        + "</body></html>");
                userLabel.setEnabled(false);
                passwordLabel.setEnabled(false);
                passwordText.setEnabled(false);
                userText.setEnabled(false);
                loginButton.setEnabled(false);
                cancelButton.setEnabled(false);
                signUpButton.setEnabled(false);
                userLabel.setVisible(false);
                passwordLabel.setVisible(false);
                passwordText.setVisible(false);
                userText.setVisible(false);
                loginButton.setVisible(false);
                cancelButton.setVisible(false);
                signUpButton.setVisible(false);
                firstTimeLabel.setVisible(false);

                JLabel newIdLabel = new JLabel("Student Id:");
                Font f3 = new Font("Times New Roman", Font.BOLD, 15);
                newIdLabel.setFont(f3);
                newIdLabel.setBounds(360,170,80,35);
                panel.add(newIdLabel);
                JTextField newIdText = new JTextField(20);
                newIdText.setFont(f3);
                newIdText.setBounds(440,170,185,35);
                panel.add(newIdText);
                JLabel noticeIdLabel = new JLabel(" ");
                Font f4 = new Font("Times New Roman", Font.BOLD, 15);
                noticeIdLabel.setFont(f4);
                noticeIdLabel.setForeground(Color.red);
                noticeIdLabel.setBounds(630,170,150,35);
                panel.add(noticeIdLabel);

                JLabel newNameLabel = new JLabel("Name:");
                newNameLabel.setBounds(360,220,80,35);
                panel.add(newNameLabel);
                JTextField newNameText = new JTextField(20);
                newNameText.setBounds(440,220,185,35);
                panel.add(newNameText);
                JLabel noticeNameLabel = new JLabel(" ");
                noticeNameLabel.setFont(f4);
                noticeNameLabel.setForeground(Color.red);
                noticeNameLabel.setBounds(630,220,150,35);
                panel.add(noticeNameLabel);

                JLabel newPasswordLabel = new JLabel("Password:");
                newPasswordLabel.setBounds(360,270,80,35);
                panel.add(newPasswordLabel);
                JTextField newPasswordText = new JTextField(20);
                newPasswordText.setBounds(440,270,185,35);
                panel.add(newPasswordText);
                JLabel noticePasswordLabel = new JLabel(" ");
                noticePasswordLabel.setFont(f4);
                noticePasswordLabel.setForeground(Color.red);
                noticePasswordLabel.setBounds(630,270,150,35);
                panel.add(noticePasswordLabel);

                JLabel newClassLabel = new JLabel("Class No:");
                newClassLabel.setBounds(360,320,80,35);
                panel.add(newClassLabel);
                JTextField newClassText = new JTextField(20);
                newClassText.setBounds(440,320,185,35);
                panel.add(newClassText);
                JLabel noticeClassLabel = new JLabel(" ");
                noticeClassLabel.setFont(f4);
                noticeClassLabel.setForeground(Color.red);
                noticeClassLabel.setBounds(630,320,150,35);
                panel.add(noticeClassLabel);

                JLabel newEmailLabel = new JLabel("Email:");
                newEmailLabel.setBounds(360,370,80,35);
                panel.add(newEmailLabel);
                JTextField newEmailText = new JTextField(20);
                newEmailText.setBounds(440,370,185,35);
                panel.add(newEmailText);
                JLabel noticeEmailLabel = new JLabel(" ");
                noticeEmailLabel.setFont(f4);
                noticeEmailLabel.setForeground(Color.red);
                noticeEmailLabel.setBounds(630,370,150,35);
                panel.add(noticeEmailLabel);

                JLabel newMajorLabel = new JLabel("Major:");
                newMajorLabel.setBounds(360,420,80,35);
                panel.add(newMajorLabel);
                JTextField newMajorText = new JTextField(20);
                newMajorText.setBounds(440,420,185,35);
                panel.add(newMajorText);
                JLabel noticeMajorLabel = new JLabel(" ");
                noticeMajorLabel.setFont(f4);
                noticeMajorLabel.setForeground(Color.red);
                noticeMajorLabel.setBounds(630,420,150,35);
                panel.add(noticeMajorLabel);

                newPasswordLabel.setFont(f3);
                newPasswordText.setFont(f3);
                newEmailLabel.setFont(f3);
                newEmailText.setFont(f3);
                newMajorLabel.setFont(f3);
                newMajorText.setFont(f3);
                newClassLabel.setFont(f3);
                newClassText.setFont(f3);
                newNameLabel.setFont(f3);
                newNameText.setFont(f3);


                JLabel picturePreviewLabel;
                picturePreviewLabel = new JLabel("");
                picturePreviewLabel.setBounds(100, 100, 100, 120);
                panel.add(picturePreviewLabel);
                JButton uploadPictureB = new JButton("upload");
                uploadPictureB.setFont(new Font("Times New Roman", Font.BOLD, 15));
                uploadPictureB.setBounds(700,500,80,50);
                panel.add(uploadPictureB);
                uploadPictureB.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ImageIcon imageIcon = uploadPictureButton.addPicture(uploadPictureB);
                        if (imageIcon != null) {
                            imageIcon.setImage(imageIcon.getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));
                            picturePreviewLabel.setIcon(imageIcon);
                            picturePreviewLabel.getIcon();
                        }
                    }
                });

                JButton OKButton = new JButton("OK");
                OKButton.setBounds(420, 510, 135, 50);
                panel.add(OKButton);
                OKButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Student> studentsLog = null;
                        try {
                            studentsLog = HandleJson.getInstance().Students;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        /**
                         * Attention! This is the 1.2 version.
                         * Actually, we need to ensure the input information is legal before we store.
                         * We need a new class containing functions that judge the legitimacy of inputs.
                         * The Judgement class is JudgeInformation.java.
                         * The judgement is just the version 1.
                         */


                        String idNotice = JudgeInformation.judgeId(newIdText.getText());
                        String classIdNotice = JudgeInformation.judgeId(newClassText.getText());
                        String nameNotice = JudgeInformation.judgeBlank(newNameText.getText());
                        String passwordNotice = JudgeInformation.judgePassword(newPasswordText.getText());
                        String majorNotice = JudgeInformation.judgeBlank(newMajorText.getText());
                        String emailNotice = JudgeInformation.judgeBlank(newEmailText.getText());


                        /**
                         * CREATE a new Student!!!
                         * Remember to improve the ROBUSTNESS!!!
                         */
                        List findList = new ArrayList();
                        ArrayList<String> selectedList = new ArrayList<String>();
                        ArrayList<String> achievement = new ArrayList<String>();

                        if(idNotice.equals(" ") && classIdNotice.equals(" ") && nameNotice.equals(" ") && passwordNotice.equals(" ")
                        && majorNotice.equals(" ") && emailNotice.equals(" ")) {
                            Student newStudent = new Student(newIdText.getText(), newPasswordText.getText(), null,
                                    newEmailText.getText(), newMajorText.getText(), newNameText.getText(), newClassText.getText(), findList,"Empty!", null, achievement, selectedList);
                            studentsLog.add(newStudent);
                            try {
                                IO.write("Student.json", JSON.toJSONString(studentsLog));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            newIdLabel.setVisible(false);
                            newIdText.setVisible(false);
                            newPasswordLabel.setVisible(false);
                            newPasswordText.setVisible(false);
                            newEmailLabel.setVisible(false);
                            newEmailText.setVisible(false);
                            newMajorLabel.setVisible(false);
                            newMajorText.setVisible(false);
                            newClassLabel.setVisible(false);
                            newClassText.setVisible(false);
                            newNameLabel.setVisible(false);
                            newNameText.setVisible(false);
                            OKButton.setVisible(false);
                            uploadPictureB.setVisible(false);
                            noticeIdLabel.setVisible(false);
                            noticeNameLabel.setVisible(false);
                            noticeClassLabel.setVisible(false);
                            noticePasswordLabel.setVisible(false);
                            noticeEmailLabel.setVisible(false);
                            noticeMajorLabel.setVisible(false);
                            picturePreviewLabel.setVisible(false);
                            NoticeLabel.setText("Successfully sign up!");

                        }
                        else {
                            noticeClassLabel.setText(classIdNotice);
                            noticePasswordLabel.setText(passwordNotice);
                            noticeIdLabel.setText(idNotice);
                            noticeNameLabel.setText(nameNotice);
                            noticeEmailLabel.setText(emailNotice);
                            noticeMajorLabel.setText(majorNotice);
                        }


                    }
                });

                JButton BackButton = new JButton("Back");
                BackButton.setBounds(50, 30, 80, 25);
                panel.add(BackButton);

                /**
                 * Set up the background 2
                 * ATTENTION!!!
                 * Pay attention to the path!!!
                 */
                backgroundLabel_1.setVisible(false);
                JLabel backgroundLabel_2;
                backgroundLabel_2 = new JLabel("");
                backgroundLabel_2.setBounds(0, 0, 1000, 800);
                //String backgroundPath = "./background.jpg";
                String backgroundPath = "./Resource/loginBackground.png";
                ImageIcon backgroundIcon = new ImageIcon(backgroundPath);
                backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));
                backgroundLabel_2.setIcon(backgroundIcon);
                backgroundLabel_2.getIcon();
                panel.add(backgroundLabel_2);

                BackButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        picturePreviewLabel.setVisible(false);
                        backgroundLabel_1.setVisible(true);
                        backgroundLabel_2.setVisible(false);
                        NoticeLabel.setText("Welcome to login!");
                        userLabel.setEnabled(true);
                        passwordLabel.setEnabled(true);
                        passwordText.setEnabled(true);
                        userText.setEnabled(true);
                        loginButton.setEnabled(true);
                        cancelButton.setEnabled(true);
                        signUpButton.setEnabled(true);
                        userLabel.setVisible(true);
                        passwordLabel.setVisible(true);
                        passwordText.setVisible(true);
                        userText.setVisible(true);
                        loginButton.setVisible(true);
                        cancelButton.setVisible(true);
                        signUpButton.setVisible(true);
                        firstTimeLabel.setVisible(true);
                        BackButton.setVisible(false);
                        newIdLabel.setVisible(false);
                        newIdText.setVisible(false);
                        newPasswordLabel.setVisible(false);
                        newPasswordText.setVisible(false);
                        newEmailLabel.setVisible(false);
                        newEmailText.setVisible(false);
                        newMajorLabel.setVisible(false);
                        newMajorText.setVisible(false);
                        newClassLabel.setVisible(false);
                        newClassText.setVisible(false);
                        newNameLabel.setVisible(false);
                        newNameText.setVisible(false);
                        noticeIdLabel.setVisible(false);
                        noticeNameLabel.setVisible(false);
                        noticeClassLabel.setVisible(false);
                        noticePasswordLabel.setVisible(false);
                        noticeEmailLabel.setVisible(false);
                        noticeMajorLabel.setVisible(false);
                        OKButton.setVisible(false);
                        uploadPictureB.setVisible(false);
                    }
                });
            }


        });

    }
}
