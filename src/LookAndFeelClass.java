import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//hier wird das Look And Feel gesetzt
public class LookAndFeelClass {

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			LoggingClass.makeWarningLog(e1.getMessage());
		} catch (InstantiationException e1) {
			LoggingClass.makeWarningLog(e1.getMessage());
		} catch (IllegalAccessException e1) {
			LoggingClass.makeWarningLog(e1.getMessage());
		} catch (UnsupportedLookAndFeelException e1) {
			LoggingClass.makeWarningLog(e1.getMessage());
		}
	}
}
