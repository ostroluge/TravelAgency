package model;

public class Room {

	private Long idHotel;
	private Long idCategory;
	private int roomNumber;
	private String nameRoom;

	public Room(Long idHotel, Long idCategory, int roomNumber, String name) {
		this.idHotel = idHotel;
		this.idCategory = idCategory;
		this.roomNumber = roomNumber;
		this.nameRoom = name;
	}

	public Room(int roomNumber, String name) {
		this.roomNumber = roomNumber;
		this.nameRoom = name;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getNameRoom() {
		return nameRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}
}
