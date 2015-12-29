package job.city;

import factory.CityFactory;

public class DeleteCity {

	public DeleteCity(Long id) {
		CityFactory.getInstance().removeCity(id);
	}
}
