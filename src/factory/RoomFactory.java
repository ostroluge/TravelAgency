package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Room;
import ui.listener.room.RoomChangeListener;
import db.DbManager;

public class RoomFactory {

	private static RoomFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;

	private static List<RoomChangeListener> listeners = new ArrayList<>();
	
	public static RoomFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RoomFactory();
		}
		return INSTANCE;
	}

	public Room getRoomByIds(Long idCategory, int roomNumber) {
		Room room = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from room where id_category = ?"
					+ " and room_number = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCategory);
			preparedStatement.setInt(2, roomNumber);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long rIdHotel = resultPreparedStatement.getLong(1);
				Long rIdCategory = resultPreparedStatement.getLong(2);
				int rRoomNumber = resultPreparedStatement.getInt(3);
				String name = resultPreparedStatement.getString(4);
				room = new Room(rIdHotel, rIdCategory, rRoomNumber, name);
			}
		
			if (room != null) {
				return room;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Room> getRoomsByCategory(Long idCategory) {
		List<Room> rooms = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from room where id_category = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCategory);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idHotel = resultPreparedStatement.getLong(1);
				Long rIdCategory = resultPreparedStatement.getLong(2);
				int roomNumber = resultPreparedStatement.getInt(3);
				String name = resultPreparedStatement.getString(4);
				rooms.add(new Room(idHotel, rIdCategory, roomNumber, name));
			}
			
			if (!rooms.isEmpty()) {
				return rooms;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addRoom(Long idHotel, Long idCategory, int roomNumber, String nameRoom) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into room "
					+ "(id_hotel, id_category, room_number, name_room) values (?, ?, ?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idHotel);
			preparedStatement.setLong(2, idCategory);
			preparedStatement.setInt(3, roomNumber);
			preparedStatement.setString(4, nameRoom);
		
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int removeRoom(Long idHotel, Long idCategory, int roomNumber) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from room where id_hotel = ? "
					+ "and id_category = ? and room_number = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idHotel);
			preparedStatement.setLong(2, idCategory);
			preparedStatement.setInt(3, roomNumber);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addListener(RoomChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (RoomChangeListener listener : listeners) {
			listener.roomHasChanged();
		}
	}
}
