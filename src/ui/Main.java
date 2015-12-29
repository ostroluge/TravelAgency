package ui;

import java.sql.SQLException;

import ui.city.CityFrame;
import ui.customer.CustomerFrame;

public class Main {

	public static void main(String[] args) throws SQLException {
//		CustomerFrame frame = new CustomerFrame();
//		frame.setVisible(true);

		CityFrame cityFrame = new CityFrame();
		cityFrame.setVisible(true);
	}
}
