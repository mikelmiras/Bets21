package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI(new User("demo", ""));
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
	public UserGUI(User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel(u.getUsername());
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Money: " + u.getMoney() + " €"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryQuestion"));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("depositmoney")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositMoneyGUI frame = new DepositMoneyGUI(u);
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_1);
	}

}
