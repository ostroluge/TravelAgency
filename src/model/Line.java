package model;

public class Line {

	private Long id;
	private Long idDepartureCity;
	private Long idArrivalCity;
	
	public Line(Long id, Long idDepartureCity, Long idArrivalCity) {
		this.id = id;
		this.idDepartureCity = idDepartureCity;
		this.idArrivalCity = idArrivalCity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdDepartureCity() {
		return idDepartureCity;
	}

	public void setIdDepartureCity(Long idDepartureCity) {
		this.idDepartureCity = idDepartureCity;
	}

	public Long getIdArrivalCity() {
		return idArrivalCity;
	}

	public void setIdArrivalCity(Long idArrivalCity) {
		this.idArrivalCity = idArrivalCity;
	}
}
