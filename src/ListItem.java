import java.io.File;
import java.io.Serializable;

// beinhaltet die rausgesuchten Ordners
public class ListItem implements Serializable{

	private static final long serialVersionUID = -2350695635943067250L;  // von Eclipse generiert
	
	private String name;
	private String path;
	private int superDirectoryIndex;
	private String superDirectoryName;
	
	private String comment = null;
	
	
	public ListItem(File file, String directoryName, int directoryIndex) {
		path = file.getAbsolutePath();
		name = file.getName();
		superDirectoryIndex = directoryIndex;
		superDirectoryName = directoryName;
	}
	
	public String getInfoForList() {   // soll die Darstellungen in der Liste geben
		return name + "                                         " + superDirectoryName;
	}
	
	public String getInfoForTXT() {
		return name + "\t" + superDirectoryName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getSuperDirectoryIndex() {
		return superDirectoryIndex;
	}
}
