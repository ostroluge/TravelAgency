package ui.category;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HotelCategoryTableContainer extends JPanel {

	HotelTable hotelTable = new HotelTable();
	CategoryTable categoryTable = new CategoryTable();
	
	public HotelCategoryTableContainer() {
		setLayout(new GridLayout(1, 2));
		add(hotelTable);
		add(categoryTable);
	}
}
