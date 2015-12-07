package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hotel;
import db.DbManager;

public class HotelFactory {

	private static HotelFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	public static HotelFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HotelFactory();
		}
		return INSTANCE;
	}

	public Hotel create(Long id, Long idCity, String name){
		return new Hotel(id, idCity, name);
	}
	
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

	public int addHotel(Long idCity, String name) {
		try {
			preparedStatement = conn.prepareStatement(
					"insert into hotel (id_city, name) values (?, ?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, idCity);
			preparedStatement.setString(2, name);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int removeHotel(Long id) {
		try {
			preparedStatement = conn.prepareStatement(
					"delete from hotel where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			int resultCode = preparedStatement.executeUpdate();
			
			return resultCode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
