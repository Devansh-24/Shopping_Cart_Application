package Model;

public class Product {
	private int id;
	private String name;
	private String Category;
	private double price;
	private String image;
	public Product(int id, String name, String category, double price, String image) {
		super();
		this.id = id;
		this.name = name;
		Category = category;
		this.price = price;
		this.image = image;
	}
	public Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", Category=" + Category + ", price=" + price + ", image="
				+ image + "]";
	}
	
	

}
