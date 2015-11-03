package marketplace;
import java.util.ArrayList;

public class ProductFilter extends ProductSorter {
	private ProductParser pp = new ProductParser();
	private ArrayList<Product> processedList;
	private int processedListSize;
	private final ArrayList<Product> products;
	private final int productSize;
	private boolean eastSelected, westSelected, sideSelected, mainSelected,
			dessertSelected;
	
	public int getProcessedListSize() {
		return processedListSize;
	}

	public ArrayList<Product> getProcessedList() {
		return processedList;
	}

	public boolean isEastSelected() {
		return eastSelected;
	}

	public boolean isWestSelected() {
		return westSelected;
	}

	public boolean isSideSelected() {
		return sideSelected;
	}

	public boolean isMainSelected() {
		return mainSelected;
	}

	public boolean isDessertSelected() {
		return dessertSelected;
	}

	public ProductFilter() {
		super(SortOption.NAME);
		processedList = pp.getProductList();
		processedListSize = pp.getProductListSize();
		products = pp.getProductList();
		productSize = pp.getProductListSize();
		eastSelected = true;
		westSelected = true;
		sideSelected = true;
		mainSelected = true;
		dessertSelected = true;
	}
	
	public void refresh() {
		processedList = pp.getProductList();
		processedListSize = pp.getProductListSize();
		eastSelected = true;
		westSelected = true;
		sideSelected = true;
		mainSelected = true;
		dessertSelected = true;
	}

	public void setEastSelected(boolean refilterEast) {
		this.eastSelected = refilterEast;
	}

	public void setWestSelected(boolean refilterWest) {
		this.westSelected = refilterWest;
	}

	public void setSideSelected(boolean refilterSide) {
		this.sideSelected = refilterSide;
	}

	public void setMainSelected(boolean refilterMain) {
		this.mainSelected = refilterMain;
	}

	public void setDessertSelected(boolean refilterDessert) {
		this.dessertSelected = refilterDessert;
	}

	public void removeEast(ArrayList<Product> p) {
		sortByCuisine(p);
		int i = p.size() - 1;
		while (i >= 0 && p.get(i).getCuisine() == Cuisine.EAST) {
			p.remove(i);
			i--;
		}
		processedListSize = p.size();
	}

	public void addEast() {
		ArrayList<Product> temps = new ArrayList<Product>();
		sortByCuisine(products);
		for (int i = productSize - 1; i >= 0
				&& products.get(i).getCuisine() != Cuisine.WEST; i--) {
			temps.add(products.get(i));
		}
		eastSelected = true;
		refilter(temps);
		eastSelected = false;
		for (Product temp : temps)
			processedList.add(temp);
		processedListSize = processedList.size();
	}

	public void removeWest(ArrayList<Product> p) {
		sortByCuisine(p);
		int limit = p.size();
		int count = 0;
		while (count < limit && p.get(0).getCuisine() == Cuisine.WEST) {
			p.remove(0);
			count++;
		}
		processedListSize = p.size();
	}

	public void addWest() {
		ArrayList<Product> temps = new ArrayList<Product>();
		sortByCuisine(products);
		for (int i = 0; i < products.size()
				&& products.get(i).getCuisine() != Cuisine.EAST; i++) {
			temps.add(products.get(i));
		}
		westSelected = true;
		refilter(temps);
		westSelected = false;
		for (Product temp : temps)
			processedList.add(temp);
		processedListSize = processedList.size();
	}

	public void removeSide(ArrayList<Product> p) {
		sortByCourse(p);
		while (0 < p.size() && p.get(0).getCourse() == Course.SIDE) {
			p.remove(0);
		}
		processedListSize = p.size();
	}

	public void addSide() {
		sortByCourse(products);
		ArrayList<Product> temps = new ArrayList<Product>();
		for (int i = 0; i < products.size()
				&& products.get(i).getCourse() == Course.SIDE; i++) {
			temps.add(products.get(i));
		}
		sideSelected = true;
		refilter(temps);
		sideSelected = false;
		for (Product temp : temps)
			processedList.add(temp);
		processedListSize = processedList.size();
	}

	public void removeMain(ArrayList<Product> p) {
		sortByCourse(p);
		for (int i = 0; i < p.size() && p.get(i).getCourse() != Course.DESSERT; i++) {
			if (p.get(i).getCourse() == Course.MAIN) {
				p.remove(i);
				i--;
			}
		}
		processedListSize = p.size();
	}

	public void addMain() {
		ArrayList<Product> temps = new ArrayList<Product>();
		sortByCourse(products);
		int i = 0;
		while (products.get(i).getCourse() != Course.DESSERT) {
			if (products.get(i).getCourse() != Course.SIDE) {
				temps.add(products.get(i));
			}
			i++;
		}
		mainSelected = true;
		refilter(temps);
		mainSelected = false;
		for (Product temp : temps)
			processedList.add(temp);
		processedListSize = processedList.size();
	}

	public void removeDessert(ArrayList<Product> p) {
		sortByCourse(p);
		for (int i = p.size() - 1; i >= 0
				&& p.get(i).getCourse() == Course.DESSERT; i--) {
			p.remove(i);
		}
		processedListSize = p.size();
	}

	public void addDessert() {
		ArrayList<Product> temps = new ArrayList<Product>();
		sortByCourse(products);
		for (int i = products.size() - 1; i >= 0
				&& products.get(i).getCourse() == Course.DESSERT; i--) {
			temps.add(products.get(i));
		}
		dessertSelected = true;
		refilter(temps);
		dessertSelected = false;
		for (Product temp : temps)
			processedList.add(temp);
		processedListSize = processedList.size();
	}

	public void rectifyPriceRange(double previous, double limit) {
		if (previous > limit) {
			sortByPrice(processedList);
			for (int i = processedListSize - 1; i >= 0
					&& processedList.get(i).getPrice() > limit; i--) {
				processedList.remove(i);
			}
		} else if (previous < limit) {
			ArrayList<Product> temps = new ArrayList<Product>();
			sortByPrice(products);
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getPrice() > previous
						&& products.get(i).getPrice() <= limit)
					temps.add(products.get(i));
			}
			refilter(temps);
			for (Product temp : temps)
				processedList.add(temp);
		}
		processedListSize = processedList.size();
	}

	private void refilter(ArrayList<Product> p) {
		if (!eastSelected) {
			removeEast(p);
		}
		if (!westSelected) {
			removeWest(p);
		}
		if (!mainSelected) {
			removeMain(p);
		}
		if (!sideSelected) {
			removeSide(p);
		}
		if (!dessertSelected) {
			removeDessert(p);
		}
	}

	public void resort(ArrayList<Product> products, SortOption oldMethod) {
		switch (oldMethod) {
		case NAME:
			sortByName(products);
			break;
		case PRICE:
			sortByPrice(products);
			break;
		case QUANTITY:
			sortByQuantity(products);
			break;
		case COURSE:
			sortByCourse(products);
			break;
		case CUISINE:
			sortByCuisine(products);
			break;
		}
	}
}
