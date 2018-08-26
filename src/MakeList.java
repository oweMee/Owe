import java.io.File;

import javax.swing.filechooser.FileSystemView;

//erstellt eine Liste von Ordnern, die dann im ListHolder des ProjectHolders gespeichert werden
// Anmerkung: MakeList hat Probleme mit Ordnern, die Administrationsrecht benötigen
public class MakeList {

	public static void makeList(File file) {
		FileSystemView fileSystem = FileSystemView.getFileSystemView();
		int superDirectoryIndex = SuperDirectoryItem.getHighestIndex();
		String superDirectoryName = fileSystem.getSystemDisplayName(file);  // gibt entweder den Namen der Datei oder die Bezeichnung, falls es ein Laufwerk ist (z.B. D:\ wird zu Volume (D:)
		ProjectHolder.getListHolder().addSuperDirectoryItem(new SuperDirectoryItem(file));
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory() && !(listOfFiles[i].isHidden())) {
				_makeList(listOfFiles[i], superDirectoryName, superDirectoryIndex);
			}
		}
		ProjectHolder.getListHolder().sortLists();
	}
	
	private static void _makeList(File file, String superDirectoryName, int superDirectoryIndex) {
		File[] listOfFiles = file.listFiles();
		if (listOfFiles == null) {  // Bricht ab, wenn es geschützter Ordner ist
			return;
		}
		boolean containsFile = false;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				containsFile = true;
			}
		}
		if (containsFile) {
			ProjectHolder.getListHolder().addListItem(new ListItem(file, superDirectoryName, superDirectoryIndex));
		}
		else {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isDirectory() && !(listOfFiles[i].isHidden())){
					_makeList(listOfFiles[i], superDirectoryName, superDirectoryIndex);
				}
			}
		}
	}
}
