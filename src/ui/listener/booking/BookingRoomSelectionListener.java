package ui.listener.booking;

import javax.swing.JTable;

import model.Room;

public interface BookingRoomSelectionListener {

	public void onBookingRoomSelection(Room room, JTable table);
}
