package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateCuoteGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	public CreateCuoteGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(40, 47, 485, 20));
		jLabelListOfEvents.setBounds(new Rectangle(40, 23, 277, 20));
		jLabelQuery.setBounds(new Rectangle(25, 211, 75, 20));
		jTextFieldQuery.setBounds(new Rectangle(100, 211, 429, 20));
		jLabelMinBet.setBounds(new Rectangle(25, 243, 75, 20));
		jTextFieldPrice.setBounds(new Rectangle(100, 243, 60, 20));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 275, 130, 30));
		jButtonCreate.setEnabled(false);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 275, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(175, 240, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		BLFacade facade = MainGUI.getBusinessLogic();
		Vector<Event> events = facade.getEvents(new Date());
		JComboBox<Event> jComboBoxEvents_1 = new JComboBox<Event>();
		DefaultComboBoxModel<Event> model = new DefaultComboBoxModel<Event>();
		model.addAll(events);
		jComboBoxEvents.setModel(model);
		this.getContentPane().add(jComboBoxEvents, null);
		
		
		
		jComboBoxEvents_1.setBounds(new Rectangle(275, 47, 250, 20));
		jComboBoxEvents_1.setBounds(40, 98, 485, 20);
		
		
		
		jComboBoxEvents.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item selected on list");  										 
				Event selected = (Event) jComboBoxEvents.getSelectedItem();
				
				int eventid = selected.getEventNumber();
			}
		});		

		getContentPane().add(jComboBoxEvents_1);
		
		JLabel lblListOfQuestions = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateCuoteGUI.lblListOfQuestions.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblListOfQuestions.setBounds(new Rectangle(290, 18, 277, 20));
		lblListOfQuestions.setBounds(40, 78, 277, 20);
		getContentPane().add(lblListOfQuestions);
	}

	
	
	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());

				if (inputPrice <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = MainGUI.getBusinessLogic();

					facade.createQuestion(event, inputQuery, inputPrice);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
