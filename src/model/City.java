package model;

public class City {

	private Long id;
	private String nameCity;
	
	public City(Long id, String nameCity){
		this.setId(id);
		this.setNameCity(nameCity);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameCity() {
		return nameCity;
	}
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	
}
