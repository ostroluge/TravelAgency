package model;

public class Category {

	private Long id;
	
	private int capacity;
	
	private float price;
	
	public Category(Long id, int capacity, float price){
		this.setId(id);
		this.setCapacity(capacity);
		this.setPrice(price);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
