package job.city;

import java.util.List;

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
	
	/**
	 * Récupère toutes les villes de la base
	 * @return List<City>
	 */
	public List<City> getAllCity(){
		return CityFactory.getInstance().getAllCity();
	}
	
	/**
	 * Permet de récupérer une City par son id
	 * @param id
	 * @return City
	 */
	public City getCityById(Long id){
		return CityFactory.getInstance().getCityById(id);
	}
	
	/**
	 * Permet de récupérer une City grâce à son nom
	 * @param citySelected
	 * @return City
	 */
	public City getCityByName(String citySelected){
		return CityFactory.getInstance().getCityByName(citySelected);
	}
}
