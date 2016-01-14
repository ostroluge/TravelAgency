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

/**
 * La classe fabrique des chambres 
 */
public class RoomFactory {

	private static RoomFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;

	private static List<RoomChangeListener> listeners = new ArrayList<>();
	
	/**
	 * Récupère l'instance de la RoomFactory
	 * @return RoomFactory
	 */
	public static RoomFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RoomFactory();
		}
		return INSTANCE;
	}

	/**
	 * Récupère une chambre à partir de ses ids
	 * @param idCategory
	 * @param roomNumber
	 * @return Room
	 */
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
	
	/**
	 * Récupère toutes les chambres d'une category à partir de son id
	 * @param idCategory
	 * @return List<Room>
	 */
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

	/**
	 * Permet d'ajouter une chambre à la base de données
	 * @param idHotel
	 * @param idCategory
	 * @param roomNumber
	 * @param nameRoom
	 * @return code retour
	 */
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

	/**
	 * Permet de supprimer une chambre dans la base de données
	 * @param idHotel
	 * @param idCategory
	 * @param roomNumber
	 * @return code retour
	 */
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
	
	/**
	 * Permet d'ajouter un listener à la liste de listeners de la factory
	 * @param listener
	 */
	public void addListener(RoomChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Permet de vérifier si les chambres ont changées (via les listeners)
	 */
	private static void fireModelChangeEvent() {
		for (RoomChangeListener listener : listeners) {
			listener.roomHasChanged();
		}
	}
}
