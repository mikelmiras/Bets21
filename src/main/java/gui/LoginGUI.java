package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.LoginResult;
import domain.User;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginGUI extends JFrame {

	private static BLFacade appFacadeInterface;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel errlabl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 908, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginUsername"));
		lblNewLabel.setBounds(10, 80, 335, 37);
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 36));
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 223, 491, 37);
		passwordField.setColumns(30);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(10, 127, 491, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 36));
		lblNewLabel_1.setBounds(10, 176, 170, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || passwordField.getText().equals("")) {
					errlabl.setText("Mesedez, sartu pasahitza eta erabiltzaile baliogarria.");
					System.out.println("Not valid");
					return;
				}
				String username = textField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				User loginUser = new User(username, password);
				BLFacade facade = MainGUI.getBusinessLogic();
				LoginResult result = facade.isLogin(loginUser);
				if (result.isValid()) {
					errlabl.setText(result.getErrmsg());
					errlabl.setForeground(new Color(0, 255, 0));
				}else {
					errlabl.setText(result.getErrmsg());
					errlabl.setForeground(new Color(255, 0, 0));
				}
				return;
				
			}
		});
		btnNewButton.setFont(new Font("Helvetica", Font.BOLD, 36));
		btnNewButton.setBounds(10, 285, 491, 63);
		contentPane.add(btnNewButton);
		
		errlabl = new JLabel("");
		errlabl.setForeground(new Color(255, 0, 0));
		errlabl.setFont(new Font("Helvetica", Font.BOLD, 17));
		errlabl.setBounds(10, 378, 491, 13);
		contentPane.add(errlabl);
	}
	
	public static BLFacade getBusinessLogic()
	{
	return appFacadeInterface;	
	}
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
}
