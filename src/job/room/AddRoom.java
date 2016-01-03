package job.room;

import factory.RoomFactory;

public class AddRoom {

	public AddRoom(Long idHotel, Long idCategory, int roomNumber, String nameRoom) {
		RoomFactory.getInstance().addRoom(idHotel, idCategory, roomNumber, nameRoom);
	}
}
