package ui.listener.booking;

import javax.swing.JTable;

import model.Booking;

public interface BookingSelectionListener {

	public void onBookingSelection(Booking booking, JTable table);
}
