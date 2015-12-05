package domaine;

public class Customer {

	private Long id;
	private String lastName;
	private String firstName;
	private String birthdate;
	private String originCity;
	
	public Customer(Long id, String lastName, String firstName,
			String birthdate, String originCity) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.originCity = originCity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
}
