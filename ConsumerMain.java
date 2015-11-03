package marketplace;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsumerMain extends MainScreenAbstract {
	private JPanel outPanel;
	private ProductParser pp;
	private ArrayList<Product> products;

	public ConsumerMain() {
		pp = new ProductParser();
		products = pp.getProductList();
	}

	public void create(JFrame tester) {
		createBar(tester, "CHECKOUT");
		createLog(tester, "SHOPPING CART");
		createProductDisplay(tester, 1);
		createFeatures(tester);
		setPreviousPrice(getPriceRange().getMaximum());
		getPriceRange().setValue(getPriceRange().getMaximum());
		getFilter().refresh();
		setPage(0);
	}

	public JPanel getProductDisplay() {
		return outPanel;
	}

	public void createProductDisplay(JFrame tester, int page) {
		outPanel = new JPanel(new GridLayout(2, 2));
		outPanel.setBackground(new Color(242, 227, 206));
		outPanel.setDoubleBuffered(true);
		int index = (page - 1) * 4;
		for (int p = index; (getProducts().size() == 0 || p < getProducts()
				.size()) && p < index + 4; p++) {
			if (getProducts().size() == 0) {
				JLabel label3 = new JLabel("No Products to Display.");
				label3.setFont(new Font("monospaced", Font.BOLD, 32));
				outPanel.add(label3);
				break;
			} else {
				JPanel inPanel = new JPanel();
				inPanel.setBackground(new Color(242, 227, 206));
				JPanel in2Panel = new JPanel(new FlowLayout());
				in2Panel.setBackground(new Color(242, 227, 206));
				inPanel.setLayout(new BoxLayout(inPanel, BoxLayout.PAGE_AXIS));
				BufferedImage image = getProducts().get(p).getImage();
				image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				ImageIcon icon = new ImageIcon(image);
				JLabel label1 = new JLabel();
				label1.setIcon(icon);
				inPanel.add(label1);
				final DecimalFormat myFormatter = new DecimalFormat(
						"$###,##0.00");
				String output = myFormatter.format(getProducts().get(p)
						.getPrice());
				JLabel label2 = new JLabel(getProducts().get(p).getName() + " "
						+ output);
				label2.setFont(new Font("Papyrus", Font.ITALIC, 18));
				inPanel.add(label2);
				JLabel label3 = new JLabel("Quantity:");
				SpinnerModel model;
				if (getOrder().get(getProducts().get(p).getID()) != null) {
					model = new SpinnerNumberModel((int) getOrder().get(
							getProducts().get(p).getID()), 0, getProducts()
							.get(p).getStockQuantity(), 1);
				} else {
					model = new SpinnerNumberModel(0, 0, getProducts().get(p)
							.getStockQuantity(), 1);
				}
				final JSpinner spinner = new JSpinner(model);
				final int x = p;
				spinner.setMaximumSize(new Dimension(80, 30));
				spinner.addChangeListener(new ChangeListener() {
					double total;

					public void stateChanged(ChangeEvent e) {
						getOrder().put(getProducts().get(x).getID(),
								(Integer) spinner.getValue());
						getTextArea().setText(null);
						getTextArea().append("SHOPPING CART");
						for (Integer integer : getOrder().keySet()) {
							total += products.get(integer).getPrice()
									* (Integer) getOrder().get(
											products.get(integer).getID());
							if (getOrder().get(integer) != 0) {
								getTextArea().append(
										"\n" + products.get(integer).getName());
								getTextArea()
										.append("\n"
												+ myFormatter.format(products
														.get(integer)
														.getPrice())
												+ " x"
												+ (Integer) getOrder().get(
														products.get(integer)
																.getID())
												+ " =\t"
												+ myFormatter
														.format(products.get(
																integer)
																.getPrice()
																* (Integer) getOrder()
																		.get(products
																				.get(integer)
																				.getID())));
							}
						}
						getTextArea().append(
								"\n-----------------------------------------\n" + "TOTAL:"
										+ myFormatter.format(total));
						total = 0;
					}
				});
				JLabel label4 = new JLabel(" "
						+ getProducts().get(p).getStockQuantity() + " in Stock");
				in2Panel.add(label3);
				in2Panel.add(spinner);
				in2Panel.add(label4);
				inPanel.add(in2Panel);
				outPanel.add(inPanel);
			}
		}
		tester.add(outPanel, BorderLayout.CENTER);
		getLabel().setText(
				"Page " + getPage() + " of " + (getListSize() + 3) / 4);
		tester.validate();
	}
}