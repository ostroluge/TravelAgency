package ui.listener.hotel;

import javax.swing.JTable;

import model.Hotel;

public interface HotelCitySelectionListener {

	public void onHotelSelection(Hotel hotel, JTable table);
}

