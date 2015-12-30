package ui;

import java.sql.SQLException;

import ui.category.CategoryFrame;

public class Main {

	public static void main(String[] args) throws SQLException {
//		CustomerFrame frame = new CustomerFrame();
//		frame.setVisible(true);

//		CityFrame cityFrame = new CityFrame();
//		cityFrame.setVisible(true);
	
		CategoryFrame categoryFrame = new CategoryFrame();
		categoryFrame.setVisible(true);
	}
}
