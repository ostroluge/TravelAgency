package ui.listener.booking;

import javax.swing.JTable;

import model.Flight;

public interface BookingFlightSelectionListener {

	public void onBookingFlightSelection(Flight flight, JTable table);

}
