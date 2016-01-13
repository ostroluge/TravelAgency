package model;

/**
 * The Class Category.
 */
public class Category {

	/** The hotel id. */
	private Long id, hotelId;
	
	/** The capacity. */
	private int capacity;
	
	/** The price. */
	private float price;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new category.
	 *
	 * @param id the id
	 * @param hotelId the hotel id
	 * @param capacity the capacity
	 * @param price the price
	 * @param name the name
	 */
	public Category(Long id, Long hotelId, int capacity, float price, String name){
		this.setId(id);
		this.setHotelId(hotelId);
		this.setCapacity(capacity);
		this.setPrice(price);
		this.setName(name);
	}
	
	/**
	 * Instantiates a new category.
	 *
	 * @param id the id
	 * @param capacity the capacity
	 * @param price the price
	 * @param name the name
	 */
	public Category(Long id, int capacity, float price, String name){
		this.setId(id);
		this.setCapacity(capacity);
		this.setPrice(price);
		this.setName(name);
	}
	
	/**
	 * Instantiates a new category.
	 *
	 * @param name the name
	 * @param capacity the capacity
	 * @param price the price
	 */
	public Category(String name, int capacity, float price) {
		this.name = name;
		this.capacity = capacity;
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
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the hotel id.
	 *
	 * @return the hotel id
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the hotel id.
	 *
	 * @param hotelId the new hotel id
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
}
