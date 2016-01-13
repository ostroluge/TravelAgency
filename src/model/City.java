package model;

/**
 * The Class City.
 */
public class City {

	/** The id. */
	private Long id;
	
	/** The name city. */
	private String nameCity;
	
	/**
	 * Instantiates a new city.
	 *
	 * @param id the id
	 * @param nameCity the name city
	 */
	public City(Long id, String nameCity){
		this.setId(id);
		this.setNameCity(nameCity);
	}
	
	/**
	 * Instantiates a new city.
	 *
	 * @param name the name
	 */
	public City(String name) {
		this.nameCity = name;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the name city.
	 *
	 * @return the name city
	 */
	public String getNameCity() {
		return nameCity;
	}
	
	/**
	 * Sets the name city.
	 *
	 * @param nameCity the new name city
	 */
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	
}
