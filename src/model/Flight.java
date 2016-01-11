package model;

public class Flight {

	private Long id;
	private Long idLine;
	private String dayOfWeek;
	private String departureTime;
	private int flightDuration;
	private int maxPassengerFirstClass;
	private float priceFirstClass;
	private int maxPassengerSecondClass;
	private float priceSecondClass;
	private int cancellationTime;
	
	public Flight(Long id, Long idLine, String dayOfWeek, String departureTime,
			int flightDuration, int maxPassengerFirstClass,
			float priceFirstClass, int maxPassengerSecondClass,
			float priceSecondClass, int cancellationTime) {
		this.id = id;
		this.idLine = idLine;
		this.dayOfWeek = dayOfWeek;
		this.departureTime = departureTime;
		this.flightDuration = flightDuration;
		this.maxPassengerFirstClass = maxPassengerFirstClass;
		this.priceFirstClass = priceFirstClass;
		this.maxPassengerSecondClass = maxPassengerSecondClass;
		this.priceSecondClass = priceSecondClass;
		this.cancellationTime = cancellationTime;
	}

	public Flight(Long idLine, String dayOfWeek, String departureTime,
			int flightDuration, int maxPassengerFirstClass,
			float priceFirstClass, int maxPassengerSecondClass,
			float priceSecondClass, int cancellationTime) {
		this.idLine = idLine;
		this.dayOfWeek = dayOfWeek;
		this.departureTime = departureTime;
		this.flightDuration = flightDuration;
		this.maxPassengerFirstClass = maxPassengerFirstClass;
		this.priceFirstClass = priceFirstClass;
		this.maxPassengerSecondClass = maxPassengerSecondClass;
		this.priceSecondClass = priceSecondClass;
		this.cancellationTime = cancellationTime;
	}

	public Flight(String dayOfWeek, String departureTime, int flightDuration,
			int maxPassengerFirstClass, float priceFirstClass,
			int maxPassengerSecondClass, float priceSecondClass,
			int cancellationTime) {
		this.dayOfWeek = dayOfWeek;
		this.departureTime = departureTime;
		this.flightDuration = flightDuration;
		this.maxPassengerFirstClass = maxPassengerFirstClass;
		this.priceFirstClass = priceFirstClass;
		this.maxPassengerSecondClass = maxPassengerSecondClass;
		this.priceSecondClass = priceSecondClass;
		this.cancellationTime = cancellationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdLine() {
		return idLine;
	}

	public void setIdLine(Long idLine) {
		this.idLine = idLine;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public int getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}

	public int getMaxPassengerFirstClass() {
		return maxPassengerFirstClass;
	}

	public void setMaxPassengerFirstClass(int maxPassengerFirstClass) {
		this.maxPassengerFirstClass = maxPassengerFirstClass;
	}

	public float getPriceFirstClass() {
		return priceFirstClass;
	}

	public void setPriceFirstClass(float priceFirstClass) {
		this.priceFirstClass = priceFirstClass;
	}

	public int getMaxPassengerSecondClass() {
		return maxPassengerSecondClass;
	}

	public void setMaxPassengerSecondClass(int maxPassengerSecondClass) {
		this.maxPassengerSecondClass = maxPassengerSecondClass;
	}

	public float getPriceSecondClass() {
		return priceSecondClass;
	}

	public void setPriceSecondClass(float priceSecondClass) {
		this.priceSecondClass = priceSecondClass;
	}

	public int getCancellationTime() {
		return cancellationTime;
	}

	public void setCancellationTime(int cancellationTime) {
		this.cancellationTime = cancellationTime;
	}
}
