package model;

/**
 * The Class Flight.
 */
public class Flight {

	/** The id. */
	private Long id;
	
	/** The id line. */
	private Long idLine;
	
	/** The day of week. */
	private String dayOfWeek;
	
	/** The departure time. */
	private String departureTime;
	
	/** The flight duration. */
	private int flightDuration;
	
	/** The max passenger first class. */
	private int maxPassengerFirstClass;
	
	/** The price first class. */
	private float priceFirstClass;
	
	/** The max passenger second class. */
	private int maxPassengerSecondClass;
	
	/** The price second class. */
	private float priceSecondClass;
	
	/** The cancellation time. */
	private int cancellationTime;
	
	/**
	 * Instantiates a new flight.
	 *
	 * @param id the id
	 * @param idLine the id line
	 * @param dayOfWeek the day of week
	 * @param departureTime the departure time
	 * @param flightDuration the flight duration
	 * @param maxPassengerFirstClass the max passenger first class
	 * @param priceFirstClass the price first class
	 * @param maxPassengerSecondClass the max passenger second class
	 * @param priceSecondClass the price second class
	 * @param cancellationTime the cancellation time
	 */
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

	/**
	 * Instantiates a new flight.
	 *
	 * @param idLine the id line
	 * @param dayOfWeek the day of week
	 * @param departureTime the departure time
	 * @param flightDuration the flight duration
	 * @param maxPassengerFirstClass the max passenger first class
	 * @param priceFirstClass the price first class
	 * @param maxPassengerSecondClass the max passenger second class
	 * @param priceSecondClass the price second class
	 * @param cancellationTime the cancellation time
	 */
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

	/**
	 * Instantiates a new flight.
	 *
	 * @param dayOfWeek the day of week
	 * @param departureTime the departure time
	 * @param flightDuration the flight duration
	 * @param maxPassengerFirstClass the max passenger first class
	 * @param priceFirstClass the price first class
	 * @param maxPassengerSecondClass the max passenger second class
	 * @param priceSecondClass the price second class
	 * @param cancellationTime the cancellation time
	 */
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
	 * Gets the id line.
	 *
	 * @return the id line
	 */
	public Long getIdLine() {
		return idLine;
	}

	/**
	 * Sets the id line.
	 *
	 * @param idLine the new id line
	 */
	public void setIdLine(Long idLine) {
		this.idLine = idLine;
	}

	/**
	 * Gets the day of week.
	 *
	 * @return the day of week
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Sets the day of week.
	 *
	 * @param dayOfWeek the new day of week
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Gets the departure time.
	 *
	 * @return the departure time
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the departure time.
	 *
	 * @param departureTime the new departure time
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Gets the flight duration.
	 *
	 * @return the flight duration
	 */
	public int getFlightDuration() {
		return flightDuration;
	}

	/**
	 * Sets the flight duration.
	 *
	 * @param flightDuration the new flight duration
	 */
	public void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}

	/**
	 * Gets the max passenger first class.
	 *
	 * @return the max passenger first class
	 */
	public int getMaxPassengerFirstClass() {
		return maxPassengerFirstClass;
	}

	/**
	 * Sets the max passenger first class.
	 *
	 * @param maxPassengerFirstClass the new max passenger first class
	 */
	public void setMaxPassengerFirstClass(int maxPassengerFirstClass) {
		this.maxPassengerFirstClass = maxPassengerFirstClass;
	}

	/**
	 * Gets the price first class.
	 *
	 * @return the price first class
	 */
	public float getPriceFirstClass() {
		return priceFirstClass;
	}

	/**
	 * Sets the price first class.
	 *
	 * @param priceFirstClass the new price first class
	 */
	public void setPriceFirstClass(float priceFirstClass) {
		this.priceFirstClass = priceFirstClass;
	}

	/**
	 * Gets the max passenger second class.
	 *
	 * @return the max passenger second class
	 */
	public int getMaxPassengerSecondClass() {
		return maxPassengerSecondClass;
	}

	/**
	 * Sets the max passenger second class.
	 *
	 * @param maxPassengerSecondClass the new max passenger second class
	 */
	public void setMaxPassengerSecondClass(int maxPassengerSecondClass) {
		this.maxPassengerSecondClass = maxPassengerSecondClass;
	}

	/**
	 * Gets the price second class.
	 *
	 * @return the price second class
	 */
	public float getPriceSecondClass() {
		return priceSecondClass;
	}

	/**
	 * Sets the price second class.
	 *
	 * @param priceSecondClass the new price second class
	 */
	public void setPriceSecondClass(float priceSecondClass) {
		this.priceSecondClass = priceSecondClass;
	}

	/**
	 * Gets the cancellation time.
	 *
	 * @return the cancellation time
	 */
	public int getCancellationTime() {
		return cancellationTime;
	}

	/**
	 * Sets the cancellation time.
	 *
	 * @param cancellationTime the new cancellation time
	 */
	public void setCancellationTime(int cancellationTime) {
		this.cancellationTime = cancellationTime;
	}
}
