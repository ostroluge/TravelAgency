package job.city;

import model.City;
import factory.CityFactory;

/**
 * Classe permettant de gerer les villes
 */
public class CityManager {

	public static CityManager INSTANCE = new CityManager();

	/**
	 * Ajoute une ville dans la base de donnees
	 * @param city
	 */
	public void addCity(City city){
		CityFactory.getInstance().addCity(city.getNameCity());
	}

	/**
	 * Supprimer une ville en base de donnees
	 * @param id
	 */
	public void deleteCity(Long id){
		CityFactory.getInstance().removeCity(id);
	}
}
