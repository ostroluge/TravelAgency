package ui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.customer.AddCustomer;
import job.customer.DeleteCustomer;
import job.customer.EditCustomer;
import model.Customer;
import ui.customer.listener.CustomerSelectionListener;

@SuppressWarnings("serial")
public class CustomerManagementPanel extends JPanel implements ActionListener, CustomerSelectionListener {

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
	protected JButton clearButton;
	
	protected Customer customerSelected;
	protected JTable tableSelected;
	
	public CustomerManagementPanel() {
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
		clearButton = new JButton("Clear");
		deleteButton = new JButton("Supprimer");
		
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		clearButton.addActionListener(this);
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
		add(clearButton);
		add(deleteButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (!lastName.getText().trim().toString().equals("") &&
					!firstName.getText().trim().toString().equals("") &&
					!birthdate.getText().trim().toString().equals("") &&
					!originCity.getText().trim().toString().equals("")) {
				Customer customerToAdd = new Customer(lastName.getText(),
						firstName.getText(), birthdate.getText(), originCity.getText());
				new AddCustomer(customerToAdd);
			} else {
				System.out.println("Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == editButton) {
			if (customerSelected != null) {
				if (!lastName.getText().trim().toString().equals("") &&
						!firstName.getText().trim().toString().equals("") &&
						!birthdate.getText().trim().toString().equals("") &&
						!originCity.getText().trim().toString().equals("")) {
					Customer customerToEdit = new Customer(lastName.getText(),
							firstName.getText(), birthdate.getText(), originCity.getText());
					new EditCustomer(customerSelected.getId(), customerToEdit);
				} else {
					System.out.println("Veuillez renseigner tous les champs");
				}
			} else {
				System.out.println("Veuillez selectionner un client a modifier");
			}
		} else if (e.getSource() == deleteButton) {
			if (customerSelected != null) {
				new DeleteCustomer(customerSelected.getId());
				clearSelection();
			} else {
				System.out.println("Veuillez selectionner un client a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			if (customerSelected != null) {
				clearSelection();
			}
		}
	}

	private void clearSelection() {
		customerSelected = null;
		tableSelected.clearSelection();
		lastName.setText("");
		firstName.setText("");
		birthdate.setText("");
		originCity.setText("");
	}
	
	@Override
	public void onCustomerSelection(Customer customer, JTable table) {
		if (customer != null) {
			customerSelected = customer;
			lastName.setText(customer.getLastName());
			firstName.setText(customer.getFirstName());
			birthdate.setText(customer.getBirthdate());
			originCity.setText(customer.getOriginCity());
		}
		tableSelected = table;
	}
}
