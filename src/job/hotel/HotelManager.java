package job.hotel;

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
}
