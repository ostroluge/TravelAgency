package job.city;

import model.City;
import factory.CityFactory;


public class CityManager {

	public static CityManager INSTANCE = new CityManager();

	public void addCity(City city){
		CityFactory.getInstance().addCity(city.getNameCity());
	}

	public void deleteCity(Long id){
		CityFactory.getInstance().removeCity(id);
	}
}
