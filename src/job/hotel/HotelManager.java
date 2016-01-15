package job.hotel;

import java.util.List;

import model.Hotel;
import factory.HotelFactory;

/**
 * Classe permettant de gerer les hotels
 */
public class HotelManager {

	public static HotelManager INSTANCE = new HotelManager();
	
	/**
	 * Ajoute un hotel en base de donnees
	 * @param idCity
	 * @param nomHotel
	 */
	public void addHotel(Long idCity, String nomHotel){
		HotelFactory.getInstance().addHotel(idCity, nomHotel);
	}
	
	/**
	 * Supprimer un hotel en base de donnees
	 * @param id
	 */
	public void deleteHotel(Long id){
		HotelFactory.getInstance().removeHotel(id);
	}
	
	/**
	 * Récupère tous les hotel en base de donnees
	 * @return List<Hotel>
	 */
	public List<Hotel> getAllHotels() {
		return HotelFactory.getInstance().getAllHotels();
	}
	
	/**
	 * Récupère un hotel à partir de son id
	 * @param id
	 * @return Hotel
	 */
	public Hotel getHotelById(Long id) {
		return HotelFactory.getInstance().getHotelById(id);
	}
		
	/**
	 * Récupère les hotels selon leur ville
	 * @param idCity
	 * @return List<Hotel>
	 */
	public List<Hotel> getHotelsFromCity(Long idCity) {
		return HotelFactory.getInstance().getHotelsFromCity(idCity);
	}
}
