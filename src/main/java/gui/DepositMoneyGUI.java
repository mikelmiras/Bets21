package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import sun.security.util.Resources;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DepositMoneyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositMoneyGUI frame = new DepositMoneyGUI(new User("demo", "demo", false));
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
	public DepositMoneyGUI(User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		BLFacade facade = MainGUI.getBusinessLogic();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("depositmoney"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(u.getMoney() + " €");
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		contentPane.add(lblNewLabel_2);
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("depositbtn"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double m = Integer.parseInt(textField.getText());
					facade.depositMoney(u, m);
					lblNewLabel_1.setText((u.getMoney() + m) + " €");
					
				} catch (Exception except) {
					lblNewLabel_2.setText("Sartu baliozko zenbaki bat.");
				}
			}
		});
		contentPane.add(btnNewButton);

	}

}
