package ui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import model.Customer;
import ui.customer.listener.CustomerSelectionListener;

@SuppressWarnings("serial")
public class ManagementPanel extends JPanel implements ActionListener, CustomerSelectionListener {

	protected JLabel labelLastName = new JLabel();
	protected JLabel labelFirstName = new JLabel();
	protected JLabel labelBirthdate = new JLabel();
	protected JLabel labelOriginCity = new JLabel();
	
	protected JTextField lastName;
	protected JTextField firstName;
	protected JTextField birthdate;
	protected JTextField originCity;
	
	protected JButton addButton;
	protected JButton editButton;
	protected JButton deleteButton;
	
	public ManagementPanel() {
		CustomerTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}

	private void setLabels() {
		labelLastName.setText("Nom :");
		labelFirstName.setText("Prénom :");
		labelBirthdate.setText("Date de naissance :");
		labelOriginCity.setText("Ville d'origine :");
	}

	private void setTextFields() {
		Document modelTextFieldln = new PlainDocument();
		Document modelTextFieldfn = new PlainDocument();
		Document modelTextFieldbd = new PlainDocument();
		Document modelTextFieldoc = new PlainDocument();
		lastName = new JTextField(modelTextFieldln, "", 10);
		firstName = new JTextField(modelTextFieldfn, "", 10);
		birthdate = new JTextField(modelTextFieldbd, "", 10);
		originCity = new JTextField(modelTextFieldoc, "", 10);
	}

	private void setButtons() {
		addButton = new JButton("Ajouter");
		editButton = new JButton("Modifier");
		deleteButton = new JButton("Supprimer");
		
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}

	private void setPanel() {
		setLayout(new GridLayout(3,4));
		add(labelLastName);
		add(lastName);
		add(labelFirstName);
		add(firstName);
		add(labelBirthdate);
		add(birthdate);
		add(labelOriginCity);
		add(originCity);
		add(addButton);
		add(editButton);
		add(deleteButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			System.out.println("Ajout d'un client");
		} else if (e.getSource() == editButton) {
			System.out.println("Modif d'un client");
		} else if (e.getSource() == deleteButton) {
			System.out.println("Suppression d'un client");
		}
	}

	@Override
	public void onCustomerSelection(Customer customer) {
		if (customer != null) {
			lastName.setText(customer.getLastName());
			firstName.setText(customer.getFirstName());
			birthdate.setText(customer.getBirthdate());
			originCity.setText(customer.getOriginCity());
		}
	}
}
