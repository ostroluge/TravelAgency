package job.room;

import factory.RoomFactory;

public class DeleteRoom {

	public DeleteRoom(Long idHotel, Long idCategory, int roomNumber) {
		RoomFactory.getInstance().removeRoom(idHotel, idCategory, roomNumber);
	}
}
