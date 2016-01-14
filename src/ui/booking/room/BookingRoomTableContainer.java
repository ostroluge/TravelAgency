package ui.booking.room;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.category.CategoryTable;
import ui.category.HotelTable;
import ui.room.RoomTable;

@SuppressWarnings("serial")
public class BookingRoomTableContainer extends JPanel {
	
	HotelTable hotelTable;
	CategoryTable categoryTable = new CategoryTable();
	RoomTable roomTable = new RoomTable();
	
	public BookingRoomTableContainer(Long idCity) {
		setLayout(new GridLayout(1, 3));
		hotelTable = new HotelTable(idCity);
		add(hotelTable);
		add(categoryTable);
		add(roomTable);
	}

}
