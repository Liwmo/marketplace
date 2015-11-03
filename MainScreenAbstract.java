package marketplace;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

public abstract class MainScreenAbstract extends MarketplaceTester implements
		MainScreen, ActionListener, ItemListener, ChangeListener {
	private JButton tb1, tb2, tb3, tb4;
	private JPanel panelLog, panelBar;
	private static ProductFilter pf;
	private static HashMap<Integer, Integer> hm;
	private static int newPage;
	private JLabel label;
	private JPanel leftPanel;
	private JCheckBox easternButton;
	private JCheckBox westernButton;
	private JCheckBox side;
	private JCheckBox main;
	private JCheckBox dessert;
	private JSlider priceRange;
	private double previousPrice;
	private int previousStock;
	private JTextArea textArea;
	private JScrollPane scroll;

	public static HashMap<Integer, Integer> getOrder() {
		return hm;
	}

	public JSlider getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(JSlider priceRange) {
		this.priceRange = priceRange;
	}

	public abstract void create(JFrame tester);

	public abstract void createProductDisplay(JFrame tester, int page);

	public static ProductFilter getFilter() {
		return pf;
	}

	public static void setProductFilter(ProductFilter pf) {
		MainScreenAbstract.pf = pf;
	}

	public double getPreviousPrice() {
		return previousPrice;
	}

	public void setPreviousPrice(double previousPrice) {
		this.previousPrice = previousPrice;
	}

	public int getPreviousStock() {
		return previousStock;
	}

	public void setPreviousStock(int previousStock) {
		this.previousStock = previousStock;
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JPanel getLog() {
		return panelLog;
	}

	public JPanel getBar() {
		return panelBar;
	}

	public static int getNewPage() {
		return newPage;
	}

	public static void setNewPage(int newPage) {
		MainScreenAbstract.newPage = newPage;
	}

	public int getListSize() {
		return pf.getProcessedListSize();
	}

	public ArrayList<Product> getProducts() {
		return pf.getProcessedList();
	}

	public MainScreenAbstract() {
		tb1 = new JButton();
		tb2 = new JButton("Sort Items");
		tb3 = new JButton("<-- Previous Page");
		tb4 = new JButton("Next Page -->");
		tb1.addActionListener(this);
		tb2.addActionListener(this);
		tb3.addActionListener(this);
		tb4.addActionListener(this);
		newPage = 1;
		pf = new ProductFilter();
		hm = new HashMap<Integer, Integer>();
		label = new JLabel("Page " + getPage() + " of " + (getListSize() + 3)
				/ 4);
		westernButton = new JCheckBox("Western Cuisine");
		easternButton = new JCheckBox("Eastern Cuisine");
		side = new JCheckBox("Side Dish");
		main = new JCheckBox("Main Course");
		dessert = new JCheckBox("Dessert");
		priceRange = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		priceRange.setMaximumSize(new Dimension(500, 50));
		previousPrice = (double) priceRange.getValue();
		westernButton.addItemListener(this);
		easternButton.addItemListener(this);
		side.addItemListener(this);
		main.addItemListener(this);
		dessert.addItemListener(this);
		priceRange.addChangeListener(this);
	}

	public void createLog(JFrame tester, String logName) {
		panelLog = new JPanel();
		panelLog.setBackground(new Color(242, 227, 206));
		panelLog.setDoubleBuffered(true);
		textArea = new JTextArea(28, 18);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Border border = BorderFactory.createLineBorder(Color.RED, 5);
		scroll.setBorder(border);
		textArea.setEditable(false);
		textArea.append(logName);
		panelLog.add(scroll);
		tester.add(panelLog, BorderLayout.EAST);
		tester.validate();
	}

	public void createBar(JFrame tester, String buttonText) {
		panelBar = new JPanel();
		panelBar.setBackground(new Color(242, 227, 206));
		panelBar.setDoubleBuffered(true);
		tb1.setText(buttonText);
		panelBar.setLayout(new BoxLayout(panelBar, BoxLayout.LINE_AXIS));
		panelBar.add(Box.createRigidArea(new Dimension(20, 0)));
		tb1.setPreferredSize(new Dimension(50, 20));
		panelBar.add(tb1);
		panelBar.add(Box.createRigidArea(new Dimension(20, 0)));
		tb2.setPreferredSize(new Dimension(50, 40));
		panelBar.add(tb2);
		panelBar.add(Box.createRigidArea(new Dimension(20, 0)));
		tb3.setPreferredSize(new Dimension(50, 40));
		panelBar.add(tb3);
		panelBar.add(Box.createRigidArea(new Dimension(5, 0)));
		tb4.setPreferredSize(new Dimension(50, 40));
		panelBar.add(tb4);
		panelBar.add(label);
		tester.add(panelBar, BorderLayout.SOUTH);
		tester.validate();
	}

	public void createFeatures(JFrame tester) {
		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(242, 227, 206));
		leftPanel.setDoubleBuffered(true);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		easternButton.setSelected(true);
		easternButton.setBackground(new Color(242, 227, 206));
		westernButton.setSelected(true);
		westernButton.setBackground(new Color(242, 227, 206));
		leftPanel.add(westernButton);
		leftPanel.add(easternButton);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		priceRange.setMajorTickSpacing(10);
		priceRange.setPaintTicks(true);
		priceRange.setPaintLabels(true);
		JLabel leftSideLabel2 = new JLabel("Set maximum price:");
		leftSideLabel2.setVisible(true);
		priceRange.setBackground(new Color(242, 227, 206));
		priceRange.setVisible(true);
		leftPanel.add(leftSideLabel2);
		leftPanel.add(priceRange);
		side.setSelected(true);
		main.setSelected(true);
		dessert.setSelected(true);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(side);
		leftPanel.add(main);
		leftPanel.add(dessert);
		side.setBackground(new Color(242, 227, 206));
		main.setBackground(new Color(242, 227, 206));
		dessert.setBackground(new Color(242, 227, 206));
		JLabel blank = new JLabel(" ");
		JLabel leftSideLabel3a = new JLabel("Select products to ");
		JLabel leftSideLabel3b = new JLabel("purchase on the right,");
		JLabel leftSideLabel3c = new JLabel("and filter products above.");
		leftPanel.add(blank);
		leftPanel.add(leftSideLabel3a);
		leftPanel.add(leftSideLabel3b);
		leftPanel.add(leftSideLabel3c);
		tester.add(leftPanel, BorderLayout.WEST);
		tester.validate();
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox cb = (JCheckBox) e.getSource();

		if (cb == westernButton && cb.isSelected()) {
			getFilter().addWest();
			getFilter().setWestSelected(true);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == westernButton && !cb.isSelected()) {
			getFilter().removeWest(getProducts());
			getFilter().setWestSelected(false);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		}

		else if (cb == easternButton && cb.isSelected()) {
			getFilter().addEast();
			getFilter().setEastSelected(true);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == easternButton && !cb.isSelected()) {
			getFilter().removeEast(getProducts());
			getFilter().setEastSelected(false);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		}

		else if (cb == side && cb.isSelected()) {
			getFilter().addSide();
			getFilter().setSideSelected(true);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == side && !cb.isSelected()) {
			getFilter().removeSide(getProducts());
			getFilter().setSideSelected(false);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		}

		else if (cb == main && cb.isSelected()) {
			getFilter().addMain();
			getFilter().setMainSelected(true);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == main && !cb.isSelected()) {
			getFilter().removeMain(getProducts());
			getFilter().setMainSelected(false);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == dessert && cb.isSelected()) {
			getFilter().addDessert();
			getFilter().setDessertSelected(true);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		} else if (cb == dessert && !cb.isSelected()) {
			getFilter().removeDessert(getProducts());
			getFilter().setDessertSelected(false);
			getFilter().rectifyPriceRange(100, previousPrice);
			setPage(0);
		}

	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (source == priceRange && !source.getValueIsAdjusting()) {
			source.setFocusable(false);
			double limit = (double) source.getValue();
			getFilter().rectifyPriceRange(previousPrice, limit);
			previousPrice = limit;
			setPage(0);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == tb1) {
			Object[] options = { "Cash", "Credit",
					"Debit"};
			int n = JOptionPane.showOptionDialog(getTester(),
					"Choose a payment method:",
					"Checkout", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if (n != JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(getTester(),
				    "Transaction Processed.",
				    "Confirmation",
				    JOptionPane.PLAIN_MESSAGE);
			}
		} else if (source == tb2) {
			Object[] possibilities = { "by Name", "by Price", "by Quantity" };
			String s = (String) JOptionPane.showInputDialog(getTester(),
					"Select Sort Method:", "Sorting Methods",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "by Name");
			if ((s != null) && s.equals("by Name")) {
				ProductSorter.sortByName(getProducts());
				setPage(0);
			}
			if ((s != null) && s.equals("by Price")) {
				ProductSorter.sortByPrice(getProducts());
				setPage(0);
			}
			if ((s != null) && s.equals("by Quantity")) {
				ProductSorter.sortByQuantity(getProducts());
				setPage(0);
			}
		} else if (source == tb3) {
			if (getPage() != 1)
				setPage(getPage() - 1);
			label.setText("Page " + getPage() + " of " + (getListSize() + 3)
					/ 4);

		} else if (source == tb4) {
			if ((getListSize() + 3) / 4 != 1
					&& getPage() < (getListSize() + 3) / 4)
				setPage(getPage() + 1);
			label.setText("Page " + getPage() + " of " + (getListSize() + 3)
					/ 4);
		}
	}

}
