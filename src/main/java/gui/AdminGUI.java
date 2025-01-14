package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.*;
public class AdminGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(new User("demo", ""));
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
	public AdminGUI(User u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 6));
		
		JLabel lblNewLabel = new JLabel(u.getUsername());
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateCuoteGUI(new Vector<Event>());
				a.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new CreateEventGUI();
				a.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton_3);
	}

}
