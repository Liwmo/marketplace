package marketplace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ProductParser {
	private ArrayList<Product> temp;
	private int productListSize = 0;
	
	public ProductParser() {}
	
	public int getProductListSize() {
		if (productListSize == 0)
			getProductList();
		return productListSize;
	}
	
	public ArrayList<Product> getProductList() {
		temp = new ArrayList<Product>();
		try {
			@SuppressWarnings("resource")
			Scanner file = new Scanner(new File("ProductDatabase"));
			while (file.hasNext()) {
				String info = file.nextLine();
				String[] data = info.split(";");
				String name = data[0];
				double price = Double.parseDouble(data[1]);
				int quantity = Integer.parseInt(data[2]);
				String company = data[3];
				Course course = Course.valueOf(data[4]);
				Cuisine cuisine = Cuisine.valueOf(data[5]);
				BufferedImage image = null;
				final String _RESOURCE_DIR = "ProductImages/";
				try {
					image = ImageIO.read(new File(_RESOURCE_DIR + data[6]));
				} catch (IOException e) {
					e.printStackTrace(); 
				}
				Integer id = Integer.valueOf(data[7]);
				temp.add(new Product(name, price, quantity, company, course,
						cuisine, image, id));
				productListSize = temp.size();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return temp;
	}
}
