package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import ui.listener.city.CityChangeListener;
import db.DbManager;

public class CityFactory {

	private static CityFactory INSTANCE;
	private Connection conn = DbManager.getInstance().getConnection();
	private PreparedStatement preparedStatement;
	
	private static List<CityChangeListener> listeners = new ArrayList<>();
	
	public static CityFactory getInstance(){
		if(INSTANCE == null){
			INSTANCE = new CityFactory();
		}
		return INSTANCE;
	}
	
	public City create(Long id, String nameCity){
		return new City(id, nameCity);
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
	
	public City getCityById(Long id) {
		City city = null;
		try {
			preparedStatement = conn.prepareStatement(
					"select * from city where id = ?");
			preparedStatement.clearParameters();
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultPreparedStatement = preparedStatement.executeQuery();
			
			while (resultPreparedStatement.next()) {
				Long idCity = resultPreparedStatement.getLong(1);
				String name = resultPreparedStatement.getString(2);
				city = new City(idCity, name);
			}
			
			if (city != null) {
				return city;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int addCity(String nameCity){
		try {
			preparedStatement = conn.prepareStatement("insert into city " +
			"(city_name) values (?)");
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, nameCity);
			
			int resultCode = preparedStatement.executeUpdate();
			
			fireModelChangeEvent();
			
			return resultCode;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
		
	}
	
	public int removeCity(Long id){
		try {
			preparedStatement = conn.prepareStatement("delete from city " +
			"where id = ?");
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

	public void addListener(CityChangeListener listener) {
		listeners.add(listener);
	}

	private static void fireModelChangeEvent() {
		for (CityChangeListener listener : listeners) {
			listener.cityHasChanged();
		}
	}
}
