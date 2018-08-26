import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// ladet, dass Icon vom res Ordner
public class IconClass {

	public static Image getIcon() {
		//Image icon = new ImageIcon(IconClass.class.getResource("/res/icon.png")).getImage();
		Image icon = null;
		try {
			icon = ImageIO.read(IconClass.class.getResource("/image/icon.png"));
		} catch (IOException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		}
		return icon;
	}
}
