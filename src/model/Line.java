package model;

/**
 * The Class Line.
 */
public class Line {

	/** The id. */
	private Long id;
	
	/** The id departure city. */
	private Long idDepartureCity;
	
	/** The id arrival city. */
	private Long idArrivalCity;
	
	/**
	 * Instantiates a new line.
	 *
	 * @param id the id
	 * @param idDepartureCity the id departure city
	 * @param idArrivalCity the id arrival city
	 */
	public Line(Long id, Long idDepartureCity, Long idArrivalCity) {
		this.id = id;
		this.idDepartureCity = idDepartureCity;
		this.idArrivalCity = idArrivalCity;
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
	 * Gets the id departure city.
	 *
	 * @return the id departure city
	 */
	public Long getIdDepartureCity() {
		return idDepartureCity;
	}

	/**
	 * Sets the id departure city.
	 *
	 * @param idDepartureCity the new id departure city
	 */
	public void setIdDepartureCity(Long idDepartureCity) {
		this.idDepartureCity = idDepartureCity;
	}

	/**
	 * Gets the id arrival city.
	 *
	 * @return the id arrival city
	 */
	public Long getIdArrivalCity() {
		return idArrivalCity;
	}

	/**
	 * Sets the id arrival city.
	 *
	 * @param idArrivalCity the new id arrival city
	 */
	public void setIdArrivalCity(Long idArrivalCity) {
		this.idArrivalCity = idArrivalCity;
	}
}
