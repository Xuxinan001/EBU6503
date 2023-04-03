package MainUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CommentUI extends JFrame{
    private JPanel contentPane;
	private JTextField txtRolesUndertaken;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentUI frame = new CommentUI("Volunteer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public void run(String comment) {
        try {
            CommentUI frame = new CommentUI(comment);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Create the frame.
	 */
	public CommentUI(String comment){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(173, 82, 396, 366);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtRolesUndertaken = new JTextField();
		txtRolesUndertaken.setEditable(false);
		txtRolesUndertaken.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtRolesUndertaken.setText("Comment about the Role");
		txtRolesUndertaken.setBounds(120, 6, 184, 45);
		panel.add(txtRolesUndertaken);
		txtRolesUndertaken.setColumns(10);
		
		JTextArea textArea = new JTextArea(comment);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(97, 73, 228, 198);
		panel.add(textArea);
		
		JTextArea txtrRolesUndertaken = new JTextArea();
		txtrRolesUndertaken.setBackground(new Color(255, 255, 255));
		txtrRolesUndertaken.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtrRolesUndertaken.setEditable(false);
		txtrRolesUndertaken.setText("   Roles Undertaken");
		txtrRolesUndertaken.setBounds(279, 33, 185, 37);
		contentPane.add(txtrRolesUndertaken);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./Data/icon1.png"));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(30, 25, 97, 87);
		contentPane.add(lblNewLabel);

        JButton back = new JButton("Back");
		back.setBounds(158, 315, 117, 29);
		panel.add(back);
        back.addActionListener(e -> {
			this.dispose();
            /*Roles r = new Roles();
            r.run();*/
        });
	}
}