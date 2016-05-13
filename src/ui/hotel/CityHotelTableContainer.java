package ui.hotel;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CityHotelTableContainer extends JPanel{

	CityHotelTable cityTable = new CityHotelTable();
	HotelCityTable hotelTable = new HotelCityTable();
	
	public CityHotelTableContainer() {
		setLayout(new GridLayout(1, 2));
		add(cityTable);
		add(hotelTable);
	}
}
