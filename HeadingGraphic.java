package marketplace;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;

@SuppressWarnings("serial")
public class HeadingGraphic extends JPanel
{
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method
                              //   to paint the background
    g.setColor(Color.ORANGE);
    g.fillOval(20, 20, 80, 80);
    g.setColor(Color.WHITE);
    g.fillOval(20, 20, 50, 50);
    g.setColor(Color.ORANGE);
    g.fillOval(20, 20, 20, 20);
    int fontSize = 28;
    g.setFont(new Font("Arial", Font.BOLD, fontSize));
    g.setColor(Color.BLACK);
    g.drawString("MARKETPLACE TESTER", 120, 60);
    
  }
}

