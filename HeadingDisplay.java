package marketplace;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class HeadingDisplay {

	private Color color;
	
	public HeadingDisplay(Color color) {
		this.color  = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void create(JFrame tester) {		
		HeadingGraphic header = new HeadingGraphic();
		header.setBackground(color);
		header.setPreferredSize(new Dimension(400, 100));
		tester.add(header, BorderLayout.NORTH);
	}
}
