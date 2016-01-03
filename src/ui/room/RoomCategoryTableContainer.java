package ui.room;

import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.category.CategoryTable;
import ui.category.HotelTable;

@SuppressWarnings("serial")
public class RoomCategoryTableContainer extends JPanel {

	HotelTable hotelTable = new HotelTable();
	CategoryTable categoryTable = new CategoryTable();
	RoomTable roomTable = new RoomTable();
	
	public RoomCategoryTableContainer() {
		setLayout(new GridLayout(1, 3));
		add(hotelTable);
		add(categoryTable);
		add(roomTable);
	}
}
