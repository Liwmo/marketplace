package marketplace.tests;
import java.util.ArrayList;

import junit.framework.TestCase;
import marketplace.Course;
import marketplace.Cuisine;
import marketplace.Product;
import marketplace.ProductSorter;

public class ProductSorterTest extends TestCase {
	
	private ArrayList<Product> products;

	protected void setUp() throws Exception {
		super.setUp();
		products = new ArrayList<Product>();
		products.add(new Product("A", 1.00, 0, null, Course.MAIN, Cuisine.EAST, null, null));
		products.add(new Product("C", 3.00, -1, null, Course.DESSERT, Cuisine.WEST, null, null));
		products.add(new Product("B", 2.00, 3, null, Course.SIDE, Cuisine.EAST, null, null));
		products.add(new Product("F", -2.00, 80, null, Course.DESSERT, Cuisine.WEST, null, null));
		products.add(new Product("D", 2.00, 2, null, Course.SIDE, Cuisine.EAST, null, null));
		products.add(new Product("E", 0.00, 3, null, Course.MAIN, Cuisine.WEST, null, null));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		products.clear();
	}
	
	public void testSortByName() {
		ProductSorter.sortByName(products);
		assertEquals("A", products.get(0).getName());
		assertEquals("B", products.get(1).getName());
		assertEquals("C", products.get(2).getName());
		assertEquals("D", products.get(3).getName());
		assertEquals("E", products.get(4).getName());
		assertEquals("F", products.get(5).getName());
	}
	
	public void testSortByPrice() {
		ProductSorter.sortByPrice(products);
		assertEquals(-2.00, products.get(0).getPrice());
		assertEquals(0.00, products.get(1).getPrice());
		assertEquals(1.00, products.get(2).getPrice());
		assertEquals(2.00, products.get(3).getPrice());
		assertEquals(2.00, products.get(4).getPrice());
		assertEquals(3.00, products.get(5).getPrice());
	}
	
	public void testSortByQuantity() {
		ProductSorter.sortByQuantity(products);
		assertEquals(-1, products.get(0).getStockQuantity());
		assertEquals(0, products.get(1).getStockQuantity());
		assertEquals(2, products.get(2).getStockQuantity());
		assertEquals(3, products.get(3).getStockQuantity());
		assertEquals(3, products.get(4).getStockQuantity());
		assertEquals(80, products.get(5).getStockQuantity());
	}
	
	public void testSortByCourse() {
		ProductSorter.sortByCourse(products);
		assertEquals(Course.SIDE, products.get(0).getCourse());
		assertEquals(Course.SIDE, products.get(1).getCourse());
		assertEquals(Course.MAIN, products.get(2).getCourse());
		assertEquals(Course.MAIN, products.get(3).getCourse());
		assertEquals(Course.DESSERT, products.get(4).getCourse());
		assertEquals(Course.DESSERT, products.get(5).getCourse());	
	}
	
	public void testSortByCuisine() {
		ProductSorter.sortByCuisine(products);
		assertEquals(Cuisine.WEST, products.get(0).getCuisine());
		assertEquals(Cuisine.WEST, products.get(1).getCuisine());
		assertEquals(Cuisine.WEST, products.get(2).getCuisine());
		assertEquals(Cuisine.EAST, products.get(3).getCuisine());
		assertEquals(Cuisine.EAST, products.get(4).getCuisine());
		assertEquals(Cuisine.EAST, products.get(5).getCuisine());
	}
}
