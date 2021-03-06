package ui.line;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import job.city.CityManager;
import job.line.LineManager;

import model.City;
import model.Line;
import ui.listener.line.LineSelectionListener;
import ui.menu.MainMenuFrame;
import factory.CityFactory;
import factory.LineFactory;

@SuppressWarnings("serial")
public class LineManagementPanel extends JPanel implements ActionListener, LineSelectionListener {

	protected JLabel labelDepartureCity = new JLabel();
	protected JLabel labelArrivalCity = new JLabel();
	
	protected JComboBox<Object> departureCity;
	protected JComboBox<Object> arrivalCity;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;
	protected JButton returnButton;

	protected Line lineSelected;
	protected JTable tableSelected;
	protected City dCity;
	protected City aCity;
	
	public LineManagementPanel() {
		LineTable.INSTANCE.addListener(this);
		setLabels();
		setComboBoxes();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelDepartureCity.setText("Ville de départ :");
		labelArrivalCity.setText("Ville d'arrivée :");
	}
	
	private void setComboBoxes() {
		
		List<City> cities = CityManager.INSTANCE.getAllCity();
		List<String> nameCities = new ArrayList<>();
		nameCities.add("");
		if (cities != null) {
			for (City city : cities) {
				nameCities.add(city.getNameCity());
			}
			departureCity = new JComboBox<>((nameCities.toArray()));
			arrivalCity = new JComboBox<>(nameCities.toArray());
			departureCity.addActionListener(this);
			arrivalCity.addActionListener(this);
		}
		
	}
	
	private void setPanel() {
		setLayout(new GridLayout(2, 4));
		add(labelDepartureCity);
		add(departureCity);
		add(labelArrivalCity);
		add(arrivalCity);
		add(addButton);
		add(clearButton);
		add(deleteButton);
		add(returnButton);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		if (e.getSource() == addButton) {
			if (!departureCity.getSelectedItem().equals(arrivalCity.getSelectedItem()) &&
					dCity != null && aCity != null) {
				if(!LineManager.INSTANCE.lineAlreadyExists(dCity.getId(), aCity.getId())){
					LineManager.INSTANCE.addLine(dCity.getId(), aCity.getId());					
				}
				else{
					JOptionPane.showMessageDialog(topFrame, "Cette ligne existe déjà");
				}
			} else {
				JOptionPane.showMessageDialog(topFrame, "Veuillez choisir deux villes différentes");
			}
		} else if (e.getSource() == deleteButton) {
			if (lineSelected != null) {
				LineManager.INSTANCE.deleteLine(lineSelected.getId());
				clearSelection();
			}
			else{
				JOptionPane.showMessageDialog(topFrame, "Veuillez sélectionner une ligne");
			}
		} else if (e.getSource() == clearButton) {
			if (lineSelected != null) {
				clearSelection();
			} else {
				departureCity.setSelectedItem("");
				arrivalCity.setSelectedItem("");
			}
		} else if (e.getSource() == departureCity) {
			String citySelected = (String) departureCity.getSelectedItem();
			City city = CityFactory.getInstance().getCityByName(citySelected);
			if (city != null) {
				dCity = city;
			}
		} else if (e.getSource() == arrivalCity) {
			String citySelected = (String) arrivalCity.getSelectedItem();
			City city = CityFactory.getInstance().getCityByName(citySelected);
			if (city != null) {
				aCity = city;
			}
		} else if (e.getSource() == returnButton) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			currentFrame.dispose();
			MainMenuFrame frame = new MainMenuFrame();
			frame.setVisible(true);
		}
	}

	private void clearSelection() {
		lineSelected = null;
		tableSelected.clearSelection();
		departureCity.setSelectedItem("");
		arrivalCity.setSelectedItem("");
	}
	
	@Override
	public void onLineSelection(Line line, JTable table) {
		if (line != null) {
			lineSelected = line;
			
			City dCity =  CityManager.INSTANCE
					.getCityById(line.getIdDepartureCity());
			City aCity =  CityManager.INSTANCE
					.getCityById(line.getIdArrivalCity());
			
			departureCity.setSelectedItem(dCity.getNameCity());
			arrivalCity.setSelectedItem(aCity.getNameCity());
		}
		tableSelected = table;
	}
	
}
