package job.room;

import java.util.List;

import model.Room;
import factory.RoomFactory;

/**
 * Classe permettant de gerer les chambres
 */
public class RoomManager {

	public static RoomManager INSTANCE = new RoomManager();
	
	/**
	 * Ajoute une chambre en base de donnees
	 * @param idHotel
	 * @param idCategory
	 * @param roomNumber
	 * @param nameRoom
	 */
	public void addRoom(Long idHotel, Long idCategory, int roomNumber, String nameRoom){
		RoomFactory.getInstance().addRoom(idHotel, idCategory, roomNumber, nameRoom);
	}
	
	/**
	 * Supprime une chambre en base de donnees
	 * @param idHotel
	 * @param idCategory
	 * @param roomNumber
	 */
	public void deleteRoom(Long idHotel, Long idCategory, int roomNumber) {
		RoomFactory.getInstance().removeRoom(idHotel, idCategory, roomNumber);
	}

	public Room getRoomById(Long idCategory, int roomNumer) {
		return RoomFactory.getInstance()
		.getRoomByIds(idCategory, roomNumer);
	}
	
	public List<Room> getRoomsByCategory(Long idCategory) {
		return RoomFactory.getInstance()
				.getRoomsByCategory(idCategory);
	}
}
