package marketplace.tests;
 
import marketplace.Course;
import marketplace.Cuisine;
import marketplace.ProductFilter;
import junit.framework.TestCase;

public class ProductFilterTest extends TestCase {

	private ProductFilter pf;

	protected void setUp() throws Exception {
		super.setUp();
		pf = new ProductFilter();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testEast() {
		pf.removeEast(pf.getProcessedList());
		assertEquals(4, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
			  assertNotSame(Cuisine.EAST, pf.getProcessedList().get(i).getCuisine());
			}
		pf.addEast();
		assertEquals(7, pf.getProcessedListSize());
		assertEquals(Cuisine.WEST, pf.getProcessedList().get(0).getCuisine());
		assertEquals(Cuisine.EAST,
				pf.getProcessedList().get(pf.getProcessedListSize() - 1)
						.getCuisine());
	}

	public void testWest() {
		pf.removeWest(pf.getProcessedList());
		assertEquals(3, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
			  assertNotSame(Cuisine.WEST, pf.getProcessedList().get(i).getCuisine());
			}
		pf.addWest();
		assertEquals(7, pf.getProcessedListSize());
		assertEquals(Cuisine.WEST,
				pf.getProcessedList().get(pf.getProcessedListSize() - 1)
						.getCuisine());
	}

	public void testSide() {
		pf.removeSide(pf.getProcessedList());
		assertEquals(6, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
		  assertNotSame(Course.SIDE, pf.getProcessedList().get(i).getCourse());
		}
		pf.addSide();
		assertEquals(7, pf.getProcessedListSize());
		assertEquals(Course.SIDE,
				pf.getProcessedList().get(pf.getProcessedListSize() - 1)
						.getCourse());	
		}

	public void testRemoveMain() {
		pf.removeMain(pf.getProcessedList());
		assertEquals(2, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
		  assertNotSame(Course.MAIN, pf.getProcessedList().get(i).getCourse());
		}
		pf.addMain();
		assertEquals(7, pf.getProcessedListSize());
		assertEquals(Course.MAIN,
				pf.getProcessedList().get(pf.getProcessedListSize() - 1)
						.getCourse());	
		}

	public void testRemoveDessert() {
		pf.removeDessert(pf.getProcessedList());
		assertEquals(6, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
		  assertNotSame(Course.DESSERT, pf.getProcessedList().get(i).getCourse());
		}
		pf.addDessert();
		assertEquals(7, pf.getProcessedListSize());
		assertEquals(Course.DESSERT,
				pf.getProcessedList().get(pf.getProcessedListSize() - 1)
						.getCourse());		}

	public void testRectifyPriceRange() {
		double lowerMax = 12.0;
		double higherMax = 20.0;
		pf.rectifyPriceRange(100, lowerMax);
		assertEquals(4, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
		  assertTrue(pf.getProcessedList().get(i).getPrice() <= lowerMax);
		}
		pf.rectifyPriceRange(lowerMax, higherMax);
		assertEquals(6, pf.getProcessedListSize());
		for (int i = 0; i < pf.getProcessedListSize(); i++) {
			  assertTrue(pf.getProcessedList().get(i).getPrice() <= higherMax);
			}
		}
}
