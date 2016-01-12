package ui.customer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import job.customer.CustomerManager;
import model.Customer;
import ui.listener.customer.CustomerSelectionListener;
import ui.menu.MainMenuFrame;

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
	protected JButton returnButton;
	
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
		returnButton = new JButton("Retour");
		
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		clearButton.addActionListener(this);
		deleteButton.addActionListener(this);
		returnButton.addActionListener(this);
	}

	private void setPanel() {
		setLayout(new GridLayout(4,4));
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
		add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!lastName.getText().trim().equals("") &&
					!firstName.getText().trim().equals("") &&
					!birthdate.getText().trim().equals("") &&
					!originCity.getText().trim().equals("")) {
				Customer customerToAdd = new Customer(lastName.getText(),
						firstName.getText(), birthdate.getText(), originCity.getText());
				CustomerManager.INSTANCE.addCustomer(customerToAdd);
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
			}
		} else if (e.getSource() == editButton) {
			if (customerSelected != null) {
				if (!lastName.getText().trim().equals("") &&
						!firstName.getText().trim().equals("") &&
						!birthdate.getText().trim().equals("") &&
						!originCity.getText().trim().equals("")) {
					Customer customerToEdit = new Customer(lastName.getText(),
							firstName.getText(), birthdate.getText(), originCity.getText());
					CustomerManager.INSTANCE.editCustomer(customerSelected.getId(), customerToEdit);
				} else {
					JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner tous les champs");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner un client à modifier");
			}
		} else if (e.getSource() == deleteButton) {
			if (customerSelected != null) {
				CustomerManager.INSTANCE.deleteCustomer(customerSelected.getId());
				clearSelection();
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner un client à supprimer");
			}
		} else if (e.getSource() == clearButton) {
			if (customerSelected != null) {
				clearSelection();
			} else {
				lastName.setText("");
				firstName.setText("");
				birthdate.setText("");
				originCity.setText("");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
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
