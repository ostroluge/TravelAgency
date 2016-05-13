package model;

/**
 * The Class Room.
 */
public class Room {

	/** The id hotel. */
	private Long idHotel;
	
	/** The id category. */
	private Long idCategory;
	
	/** The room number. */
	private int roomNumber;
	
	/** The name room. */
	private String nameRoom;

	/**
	 * Instantiates a new room.
	 *
	 * @param idHotel the id hotel
	 * @param idCategory the id category
	 * @param roomNumber the room number
	 * @param name the name
	 */
	public Room(Long idHotel, Long idCategory, int roomNumber, String name) {
		this.idHotel = idHotel;
		this.idCategory = idCategory;
		this.roomNumber = roomNumber;
		this.nameRoom = name;
	}

	/**
	 * Instantiates a new room.
	 *
	 * @param roomNumber the room number
	 * @param name the name
	 */
	public Room(int roomNumber, String name) {
		this.roomNumber = roomNumber;
		this.nameRoom = name;
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
	 * Gets the name room.
	 *
	 * @return the name room
	 */
	public String getNameRoom() {
		return nameRoom;
	}

	/**
	 * Sets the name room.
	 *
	 * @param nameRoom the new name room
	 */
	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}
}
