package marketplace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductSorter {
	private static SortOption sortOption;
	
	public ProductSorter(SortOption sortOption) {
		ProductSorter.sortOption = sortOption;
	}

	public static void sortByName(ArrayList<Product> products) {
		Collections.sort(products);
		setSortOption(SortOption.NAME);
	}

	public static void sortByPrice(ArrayList<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			public int compare(Product one, Product two) {
				return new Double(one.getPrice()).compareTo(new Double(two
						.getPrice()));
			}
		});
		setSortOption(SortOption.PRICE);
	}

	public static void sortByQuantity(ArrayList<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			public int compare(Product one, Product two) {
				return one.getStockQuantity() - two.getStockQuantity();
			}
		});
		setSortOption(SortOption.QUANTITY);
	}

	public static void sortByCourse(ArrayList<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			public int compare(Product one, Product two) {
				return one.getCourse().getNumVal()
						- two.getCourse().getNumVal();
			}
		});
	}

	public static void sortByCuisine(ArrayList<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			public int compare(Product one, Product two) {
				if (one.getCuisine() == two.getCuisine()) {
					return 0;
				} else if (one.getCuisine() == Cuisine.WEST) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		setSortOption(SortOption.CUISINE);
	}

	public static SortOption getSortOption() {
		return sortOption;
	}

	public static void setSortOption(SortOption so) {
		sortOption = so;
	}
	
}
