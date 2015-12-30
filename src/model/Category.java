package model;

public class Category {

	private Long id;
	
	private int capacity;
	
	private float price;
	
	private String name;
	
	public Category(Long id, int capacity, float price, String name){
		this.setId(id);
		this.setCapacity(capacity);
		this.setPrice(price);
		this.setName(name);
	}
	
	public Category(String name, int capacity, float price) {
		this.name = name;
		this.capacity = capacity;
		this.price = price;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
