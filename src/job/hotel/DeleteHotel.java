package job.hotel;

import factory.HotelFactory;

public class DeleteHotel {

	public DeleteHotel(Long id) {
		HotelFactory.getInstance().removeHotel(id);
	}
}
