package model;

public class Hotel {

	private Long id;
	private Long idCity;
	private String name;
	
	public Hotel(Long id, Long idCity, String name) {
		this.id = id;
		this.idCity = idCity;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
