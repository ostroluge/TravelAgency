package job.hotel;

import factory.HotelFactory;

public class HotelManager {

	public static HotelManager INSTANCE = new HotelManager();
	
	public void addHotel(Long idCity, String nomHotel){
		HotelFactory.getInstance().addHotel(idCity, nomHotel);
	}
	
	public void deleteHotel(Long id){
		HotelFactory.getInstance().removeHotel(id);
	}
}
