package marketplace;
import  java.awt.image.BufferedImage;

public class Product implements Comparable<Product> {
	private String name;
	private double price;
	private int stockQuantity;
	private String company;
	private Course course;
	private Cuisine cuisine;
	private BufferedImage image;
	private Integer id;

	public Product(String name, double price, int stockQuantity,
			String company, Course course, Cuisine cuisine, BufferedImage image, Integer id) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.company = company;
		this.course = course;
		this.cuisine = cuisine;
		this.setImage(image);
		this.id = id;
	}
	
	public int compareTo(Product other) {
		return name.compareTo(other.name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Integer getID() {
		return id;
	}

}
