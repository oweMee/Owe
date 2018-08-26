import java.io.File;
import java.io.Serializable;

import javax.swing.filechooser.FileSystemView;

// beinhaltet die ausgewählten Ordner
public class SuperDirectoryItem implements Serializable{

	private static final long serialVersionUID = -1777011562181455775L;  // von Eclipse generiert
	
	private static int highestIndex = 0;
	private int index;
	private String name;
	private String path;
	
	public SuperDirectoryItem(File file) {
		FileSystemView fileSystem = FileSystemView.getFileSystemView();
		name = fileSystem.getSystemDisplayName(file);
		path = file.getAbsolutePath();
		highestIndex = ProjectHolder.getListHolder().getHighestSuperDirectoryIndex();
		index = getHighestIndex();
	}
	
	public static int getHighestIndex(){
		highestIndex += 1;
		return highestIndex;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getIndex() {
		return index;
	}
}
