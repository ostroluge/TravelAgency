package factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.City;

import db.DbManager;

public class CityFactory {

	private static CityFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	public static CityFactory getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CityFactory();
		}
		return INSTANCE;
	}
	
	public List<City> getAllCity(){
		List<City> cities = new ArrayList<>();
	
		try {
			preparedStatement = conn.prepareStatement("select * from city");
			preparedStatement.clearParameters();
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while(resultPreparedStatement.next()){
				Long id = resultPreparedStatement.getLong(1);
				String nameCity = resultPreparedStatement.getString(2);
				cities.add(new City(id,nameCity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(cities != null){
			return cities;
		}
		
		return null;
	}
}
