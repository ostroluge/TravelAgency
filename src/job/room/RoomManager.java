package job.room;

import factory.RoomFactory;

public class RoomManager {

	public static RoomManager INSTANCE = new RoomManager();
	
	public void addRoom(Long idHotel, Long idCategory, int roomNumber, String nameRoom){
		RoomFactory.getInstance().addRoom(idHotel, idCategory, roomNumber, nameRoom);
	}
	
	public void deleteRoom(Long idHotel, Long idCategory, int roomNumber) {
		RoomFactory.getInstance().removeRoom(idHotel, idCategory, roomNumber);
	}
}
