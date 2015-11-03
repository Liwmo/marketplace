package marketplace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBarDisplay extends MarketplaceTester implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem menuItem;
	private JMenuItem about;
	private JMenuItem welcome;

	public MenuBarDisplay() {
		menuBar = new JMenuBar();
		menuItem = new JMenuItem("Quit");
		menu1 = new JMenu("Other");
		welcome = new JMenuItem("Welcome");
		about = new JMenuItem("About");
		menu2 = new JMenu("Help");
		menuItem.addActionListener(this);
		about.addActionListener(this);
		welcome.addActionListener(this);
	}

	public void create(JFrame tester) {
		menuBar.add(menu1);
		menu1.add(menuItem);

		menuBar.add(menu2);

		menu2.add(about);
		menu2.add(welcome);
		tester.setJMenuBar(menuBar);
		tester.validate();
		tester.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == welcome) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Hello! This user interface is so well designed \na help function is unnecessary. Just kidding. Good luck.", "Welcome",
					JOptionPane.ERROR_MESSAGE);
		} else if (source == about) {
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Marketplace Tester \nVersion: 1.0 \nAuthor: Warren Li");
		} else if (source == menuItem) {
			System.exit(0);
		} 
	}
}
