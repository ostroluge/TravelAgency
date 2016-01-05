package job.hotel;

import model.Hotel;
import factory.HotelFactory;

public class AddHotel {

		public AddHotel(Long idCity, String nomHotel) {
			HotelFactory.getInstance().addHotel(idCity, nomHotel);
		}
	
}
