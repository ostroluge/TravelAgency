package ui;

import model.Booking;
import ui.booking.room.BookingRoomFrame;
import ui.menu.MainMenuFrame;
import ui.room.RoomFrame;

public class Main {

	public static void main(String[] args) {
		
//		LineFrame frame = new LineFrame();
		
//		HotelFrame frame = new HotelFrame();
		
//		CategoryFrame frame = new CategoryFrame();
		
//		CityFrame frame = new CityFrame();
		
//		CustomerFrame frame = new CustomerFrame();
		
		MainMenuFrame frame = new MainMenuFrame();
		
//		BookingRoomFrame frame = new BookingRoomFrame(new Booking(null, null, 2L, 0, null, null));
			
//		RoomFrame frame = new RoomFrame();
		frame.setVisible(true);
	}
}
