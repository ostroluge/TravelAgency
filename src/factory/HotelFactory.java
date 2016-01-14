package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hotel;
import ui.hotel.HotelCityTable;
import db.DbManager;

/**
 * La classe fabrique des hotels
 */
public class HotelFactory {

	private static HotelFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<HotelCityTable> listeners = new ArrayList<>();
	
	/**
	 * Récupère l'instance de l'HotelFactory
	 * @return HotelFactory
	 */
	public static HotelFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HotelFactory();
		}
		return INSTANCE;
	}

	/**
	 * Fonction de création d'un hotel
	 * @param id
	 * @param idCity
	 * @param name
	 * @return new Hotel
	 */
	public Hotel create(Long id, Long idCity, String name){
		return new Hotel(id, idCity, name);
	}
	
	/**
	 * Récupère tous les hotel en base de donnees
	 * @return List<Hotel>
	 */
	public List<Hotel> getAllHotels() {
		List<Hotel> hotels = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from hotel order by name asc");
			preparedStatement.clearParameters();
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();

			while (resultPreparedStatement.next()) {
				Long id = resultPreparedStatement.getLong(1);
				Long idCity = resultPreparedStatement.getLong(2);
				String name = resultPreparedStatement.getString(3);
				hotels.add(new Hotel(id, idCity, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if (hotels != null) {
			return hotels;
		}
		return null;
	}
	
	/**
	 * Récupère un hotel à partir de son id
	 * @param id
	 * @return Hotel
	 */
	public Hotel getHotelById(Long id) {
		Hotel hotel = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from hotel where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idHotel = resultPreparedStatement.getLong(1);
				Long idCity = resultPreparedStatement.getLong(2);
				String name = resultPreparedStatement.getString(3);
				hotel = new Hotel(idHotel, idCity, name);
			}
			
			if (hotel != null) {
				return hotel;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Récupère les hotels selon leur ville
	 * @param idCity
	 * @return List<Hotel>
	 */
	public List<Hotel> getHotelsFromCity(Long idCity) {
		List<Hotel> hotels = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from hotel where id_city = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCity);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();

			while (resultPreparedStatement.next()) {
				Long idDb = resultPreparedStatement.getLong(1);
				Long idCityDb = resultPreparedStatement.getLong(2);
				String nameDb = resultPreparedStatement.getString(3);
				hotels.add(new Hotel(idDb, idCityDb, nameDb));
			}
			
			if (hotels != null) {
				return hotels;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Permet d'ajouter un hotel à la base de données
	 * @param idCity
	 * @param name
	 * @return code retour
	 */
	public int addHotel(Long idCity, String name) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into hotel (id_city, name) values (?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCity);
			preparedStatement.setString(2, name);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Permet de supprimer un hotel dans la base de données
	 * @param id
	 * @return code retour
	 */
	public int removeHotel(Long id) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from hotel where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
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
	public void addListener(HotelCityTable hotelCityTable) {
		listeners.add(hotelCityTable);
	}
	
	/**
	 * Permet de vérifier si les hotels ont changés (via les listeners)
	 */
	private static void fireModelChangeEvent() {
		for (HotelCityTable listener : listeners) {
			listener.hotelCityHasChanged();
		}
	}

}
