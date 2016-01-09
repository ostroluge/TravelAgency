package ui.city;

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

import job.city.AddCity;
import job.city.DeleteCity;
import model.City;
import ui.listener.city.CitySelectionListener;
import ui.menu.MainMenuFrame;

@SuppressWarnings("serial")
public class CityManagementPanel extends JPanel implements ActionListener, CitySelectionListener {

	protected JLabel labelName = new JLabel();
	
	protected JTextField name;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;
	protected JButton returnButton;
	
	protected City citySelected;
	protected JTable tableSelected;
	
	public CityManagementPanel() {
		CityTable.INSTANCE.addListener(this);
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelName.setText("Nom :");
	}

	private void setTextFields() {
		Document modelTextField = new PlainDocument();
		name = new JTextField(modelTextField, "", 10);
	}
	
	private void setButtons() {
		addButton = new JButton("Ajouter");
		clearButton = new JButton("Clear");
		deleteButton = new JButton("Supprimer");
		returnButton = new JButton("Retour");
		
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		deleteButton.addActionListener(this);
		returnButton.addActionListener(this);
	}
	
	private void setPanel() {
		setLayout(new GridLayout(2,4));
		add(labelName);
		add(name);
		add(clearButton);
		add(addButton);
		add(deleteButton);
		add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!name.getText().trim().equals("")) {
				City cityToAdd = new City(name.getText());
				new AddCity(cityToAdd);
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez renseigner le nom de la ville");
			}
		} else if (e.getSource() == deleteButton) {
			if (citySelected != null) {
				new DeleteCity(citySelected.getId());
				clearSelection();
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez selectionner une ville a supprimer");
			}
		} else if (e.getSource() == clearButton) {
			if (citySelected != null) {
				clearSelection();
			} else {
				name.setText("");
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}

	private void clearSelection() {
		citySelected = null;
		tableSelected.clearSelection();
		name.setText("");
	}
	
	@Override
	public void onCitySelection(City city, JTable table) {
		if (city != null) {
			citySelected = city;
			name.setText(city.getNameCity());
		}
		tableSelected = table;
	}
}
