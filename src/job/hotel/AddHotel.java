package job.hotel;

import model.Hotel;
import factory.HotelFactory;

public class AddHotel {

		public AddHotel(Hotel hotel) {
			HotelFactory.getInstance().addHotel(hotel.getIdCity(), hotel.getName());
		}
	
}
