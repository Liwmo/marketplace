package marketplace;
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MarketplaceTester extends Applet{
	private static int page = 1;
	private static HeadingDisplay hd;
	static Demo tester = new Demo(); 
	private static Screen newScreen = Screen.ConsumerMain;
	private static Screen oldScreen = Screen.ConsumerMain;
	
	public static HeadingDisplay getHd() {
		return hd;
	}

	public static void setHd(HeadingDisplay hd) {
		MarketplaceTester.hd = hd;
	}

	public static Demo getTester() {
		return tester;
	}

	public static void setTester(Demo tester) {
		MarketplaceTester.tester = tester;
	}

	public static void main(String args[]) {
		tester.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		tester.setSize(1200, 750);
		tester.setVisible(true);
		tester.setResizable(false);
		
		MenuBarDisplay mbd = new MenuBarDisplay();
		mbd.create(tester);
		
		hd = new HeadingDisplay(new Color(242, 227, 206));
		hd.create(tester);

		ConsumerMain cm = new ConsumerMain();
		cm.create(tester);

		boolean running = true;

		while (running) {
			switch (newScreen) {
			case ConsumerMain:
				if (newScreen != oldScreen) {
					oldScreen = newScreen;
					tester.getContentPane().removeAll();
					hd.create(tester);
					cm.create(tester);
				}
				break;
			default:
				break;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
            
			if (MainScreenAbstract.getNewPage() != page) {
				MainScreenAbstract.setNewPage(page);
				if (page == 0 && newScreen == Screen.ConsumerMain) {
					setPage(1);
					tester.remove(cm.getProductDisplay());
					cm.createProductDisplay(tester, page);
				}
				if (newScreen == Screen.ConsumerMain) {
					tester.remove(cm.getProductDisplay());
					cm.createProductDisplay(tester, page);
				}
			}
		}

	}

	public static Screen getNewScreen() {
		return newScreen;
	}

	public static void setNewScreen(Screen screen) {
		MarketplaceTester.newScreen = screen;
	}

	public static int getPage() {
		return page;
	}

	public static void setPage(int page) {
		MarketplaceTester.page = page;
	}

}

@SuppressWarnings("serial")
class Demo extends JFrame {

	public Demo() {
		super("Foodstuffs");
	}
}
