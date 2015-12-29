package job.city;

import factory.CityFactory;
import model.City;

public class AddCity {

	public AddCity(City city) {
		CityFactory.getInstance().addCity(city.getNameCity());
	}
}
