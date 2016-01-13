package model;

import java.sql.Date;

/**
 * The Class Booking.
 */
public class Booking {

	/** The id city arrival. */
	private Long id, idClient, idFlight, idCategory, idHotel, idCityDeparture, idCityArrival;
	
	/** The nombre passagers. */
	private int roomNumber, nombrePassagers;
	
	/** The date return. */
	private Date dateDeparture, dateReturn;
	
	/** The price. */
	private float price;
	
	
	public Booking(Long idClient, Long idFlight, Long idCategory, Long idHotel, Long idCityDeparture, Long idCityArrival, int roomNumber, int nombrePassagers, Date dateDeparture, Date dateReturn, float price){
		this.idClient = idClient;
		this.idFlight = idFlight;
		this.idCategory = idCategory;
		this.idHotel = idHotel;
		this.idCityDeparture = idCityDeparture;
		this.idCityArrival = idCityArrival;
		this.roomNumber = roomNumber;
		this.nombrePassagers = nombrePassagers;
		this.dateDeparture = dateDeparture;
		this.dateReturn = dateReturn;
		this.price = price;
	}
	
	public Booking(Long id,Long idClient, Long idFlight, Long idCategory, Long idHotel, Long idCityDeparture, Long idCityArrival, int roomNumber, int nombrePassagers, Date dateDeparture, Date dateReturn, float price){
		this.id = id;
		this.idClient = idClient;
		this.idFlight = idFlight;
		this.idCategory = idCategory;
		this.idHotel = idHotel;
		this.idCityDeparture = idCityDeparture;
		this.idCityArrival = idCityArrival;
		this.roomNumber = roomNumber;
		this.nombrePassagers = nombrePassagers;
		this.dateDeparture = dateDeparture;
		this.dateReturn = dateReturn;
		this.price = price;
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
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public Long getIdClient() {
		return idClient;
	}
	
	/**
	 * Sets the id client.
	 *
	 * @param idClient the new id client
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	
	/**
	 * Gets the id flight.
	 *
	 * @return the id flight
	 */
	public Long getIdFlight() {
		return idFlight;
	}
	
	/**
	 * Sets the id flight.
	 *
	 * @param idFlight the new id flight
	 */
	public void setIdFlight(Long idFlight) {
		this.idFlight = idFlight;
	}
	
	/**
	 * Gets the id city arrival.
	 *
	 * @return the id city arrival
	 */
	public Long getIdCityArrival() {
		return idCityArrival;
	}
	
	/**
	 * Sets the id city arrival.
	 *
	 * @param idCityArrival the new id city arrival
	 */
	public void setIdCityArrival(Long idCityArrival) {
		this.idCityArrival = idCityArrival;
	}
	
	/**
	 * Gets the id city departure.
	 *
	 * @return the id city departure
	 */
	public Long getIdCityDeparture() {
		return idCityDeparture;
	}
	
	/**
	 * Sets the id city departure.
	 *
	 * @param idCityDeparture the new id city departure
	 */
	public void setIdCityDeparture(Long idCityDeparture) {
		this.idCityDeparture = idCityDeparture;
	}
	
	/**
	 * Gets the id hotel.
	 *
	 * @return the id hotel
	 */
	public Long getIdHotel() {
		return idHotel;
	}
	
	/**
	 * Sets the id hotel.
	 *
	 * @param idHotel the new id hotel
	 */
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}
	
	/**
	 * Gets the id category.
	 *
	 * @return the id category
	 */
	public Long getIdCategory() {
		return idCategory;
	}
	
	/**
	 * Sets the id category.
	 *
	 * @param idCategory the new id category
	 */
	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Gets the date departure.
	 *
	 * @return the date departure
	 */
	public Date getDateDeparture() {
		return dateDeparture;
	}
	
	/**
	 * Sets the date departure.
	 *
	 * @param dateDeparture the new date departure
	 */
	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	
	/**
	 * Gets the date return.
	 *
	 * @return the date return
	 */
	public Date getDateReturn() {
		return dateReturn;
	}
	
	/**
	 * Sets the date return.
	 *
	 * @param dateReturn the new date return
	 */
	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}
	
	/**
	 * Gets the room number.
	 *
	 * @return the room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * Sets the room number.
	 *
	 * @param roomNumber the new room number
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/**
	 * Gets the nombre passagers.
	 *
	 * @return the nombre passagers
	 */
	public int getNombrePassagers() {
		return nombrePassagers;
	}
	
	/**
	 * Sets the nombre passagers.
	 *
	 * @param nombrePassagers the new nombre passagers
	 */
	public void setNombrePassagers(int nombrePassagers) {
		this.nombrePassagers = nombrePassagers;
	}
}
