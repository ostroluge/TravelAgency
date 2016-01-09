package ui.listener.flight;

import javax.swing.JTable;

import model.Flight;

public interface FlightSelectionListener {

	public void onFlightSelection(Flight flight, JTable table);
}
