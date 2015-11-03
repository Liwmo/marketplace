package marketplace;
import javax.swing.JFrame;

public interface MainScreen {
	void create(JFrame tester);
	void createProductDisplay(JFrame tester, int page); 
	void createLog(JFrame tester, String logName);
	void createBar(JFrame tester, String buttonText);
	void createFeatures(JFrame tester);
}
