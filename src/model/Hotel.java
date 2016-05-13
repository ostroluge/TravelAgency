package model;

/**
 * The Class Hotel.
 */
public class Hotel {

	/** The id. */
	private Long id;
	
	/** The id city. */
	private Long idCity;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new hotel.
	 *
	 * @param id the id
	 * @param idCity the id city
	 * @param name the name
	 */
	public Hotel(Long id, Long idCity, String name) {
		this.id = id;
		this.idCity = idCity;
		this.name = name;
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
	 * Gets the id city.
	 *
	 * @return the id city
	 */
	public Long getIdCity() {
		return idCity;
	}

	/**
	 * Sets the id city.
	 *
	 * @param idCity the new id city
	 */
	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
