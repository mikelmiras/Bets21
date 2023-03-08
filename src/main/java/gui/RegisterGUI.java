package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.LoginResult;
import domain.User;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usnameinput;
	private JPasswordField passinput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 5, 5));
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginUsername"));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		usnameinput = new JTextField("");
		usnameinput.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(usnameinput);
		usnameinput.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginPasword"));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(lblNewLabel_1);
		
		passinput = new JPasswordField();
		contentPane.add(passinput);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterBTN"));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 18));
		contentPane.add(btnNewButton);
		
		JLabel statuslabl = new JLabel("");
		contentPane.add(statuslabl);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String usname = usnameinput.getText();
				String pass = "";
				for (char c: passinput.getPassword()) {
					pass += c;
				}
				if (usname.equals("") || pass.equals("")) {
					statuslabl.setForeground(new Color(255, 0, 0));
					statuslabl.setText("Jarritako erabiltzailea existitzen da edo ez da baliozkoa");
					return;
				}
				User newuser = new User(usname, pass);
				newuser.hashPassword();
				LoginResult l = facade.register(newuser);
				if (l.isValid()) {
					statuslabl.setForeground(new Color(0, 255, 0));
					statuslabl.setText("Kontua ondo sortu da.");
				}else {
					statuslabl.setForeground(new Color(255, 0, 0));
					statuslabl.setText("Jarritako erabiltzailea existitzen da edo ez da baliozkoa");
				}
			}
		});
	}

}
