package ui.listener.city;

import javax.swing.JTable;

import model.City;

public interface CitySelectionListener {

	public void onCitySelection(City city, JTable table);
}
