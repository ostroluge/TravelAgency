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

	public List<Hotel> getHotelsFromCity(Long idCity) {
		List<Hotel> hotels = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(
					"select * from hotel where id_city = ?");
			preparedStatement.clearParameters();
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();

			while (resultPreparedStatement.next()) {
				//todo: get les champs hotel et l'ajouter a la liste
			}
			
			return hotels;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
