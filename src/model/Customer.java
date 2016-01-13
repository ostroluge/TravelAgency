package model;

/**
 * The Class Customer.
 */
public class Customer {

	/** The id. */
	private Long id;
	
	/** The last name. */
	private String lastName;
	
	/** The first name. */
	private String firstName;
	
	/** The birthdate. */
	private String birthdate;
	
	/** The origin city. */
	private String originCity;
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param id the id
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param birthdate the birthdate
	 * @param originCity the origin city
	 */
	public Customer(Long id, String lastName, String firstName,
			String birthdate, String originCity) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.originCity = originCity;
	}
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param birthdate the birthdate
	 * @param originCity the origin city
	 */
	public Customer(String lastName, String firstName, String birthdate,
			String originCity) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.originCity = originCity;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the birthdate.
	 *
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Sets the birthdate.
	 *
	 * @param birthdate the new birthdate
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Gets the origin city.
	 *
	 * @return the origin city
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * Sets the origin city.
	 *
	 * @param originCity the new origin city
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
}
