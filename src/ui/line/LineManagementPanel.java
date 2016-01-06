package ui.line;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import job.line.AddLine;
import job.line.DeleteLine;
import model.City;
import model.Line;
import ui.listener.line.LineSelectionListener;
import factory.CityFactory;

@SuppressWarnings("serial")
public class LineManagementPanel extends JPanel implements ActionListener, LineSelectionListener {

	protected JLabel labelDepartureCity = new JLabel();
	protected JLabel labelArrivalCity = new JLabel();
	
	protected JComboBox departureCity;
	protected JComboBox arrivalCity;
	
	protected JButton addButton;
	protected JButton deleteButton;
	protected JButton clearButton;

	protected Line lineSelected;
	protected JTable tableSelected;
	protected City dCity;
	protected City aCity;
	
	public LineManagementPanel() {
		LineTable.INSTANCE.addListener(this);
		setLabels();
		setComboBoxs();
		setButtons();
		setPanel();
	}
	
	private void setLabels() {
		labelDepartureCity.setText("Ville de départ :");
		labelArrivalCity.setText("Ville d'arrivée :");
	}
	
	private void setComboBoxs() {
		
		List<City> cities = CityFactory.getInstance().getAllCity();
		List<String> nameCities = new ArrayList<>();
		if (cities != null) {
			for (City city : cities) {
				nameCities.add(city.getNameCity());
			}
			departureCity = new JComboBox(nameCities.toArray());
			arrivalCity = new JComboBox(nameCities.toArray());
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
	}
	
	private void setButtons() {
		addButton = new JButton("Ajouter");
		clearButton = new JButton("Clear");
		deleteButton = new JButton("Supprimer");
		
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (!departureCity.getSelectedItem().equals(arrivalCity.getSelectedItem()) &&
					dCity != null && aCity != null) {
				new AddLine(dCity.getId(), aCity.getId());
			} else {
				System.out.println("Veuillez choisir 2 villes differentes");
			}
		} else if (e.getSource() == deleteButton) {
			if (lineSelected != null) {
				new DeleteLine(lineSelected.getId());
			}
		} else if (e.getSource() == clearButton) {
			if (lineSelected != null) {
				clearSelection();
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
		}
	}

	private void clearSelection() {
		lineSelected = null;
		tableSelected.clearSelection();
	}
	
	@Override
	public void onLineSelection(Line line, JTable table) {
		if (line != null) {
			lineSelected = line;
			
			City dCity = CityFactory.getInstance()
					.getCityById(line.getIdDepartureCity());
			City aCity = CityFactory.getInstance()
					.getCityById(line.getIdArrivalCity());
			
			departureCity.setSelectedItem(dCity.getNameCity());
			arrivalCity.setSelectedItem(aCity.getNameCity());
		}
		tableSelected = table;
	}
	
}
