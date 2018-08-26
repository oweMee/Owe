import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

// besitzt den ListHolder bzw. speichert und Ladet ihn und gibt die Schnittstelle für andere Methoden
public class ProjectHolder {

	private static ListHolder listHolder;
	
	public static void newProject() {
		listHolder = new ListHolder();
	}
	
	public static void saveProject() {
		if (listHolder.getLoadingPath() == null) {
			JFileChooser chooser = new JFileChooser();
			//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int stateOfChoosing = chooser.showSaveDialog(null);
			if(stateOfChoosing != JFileChooser.APPROVE_OPTION) {
				return;
			}
			String path = chooser.getSelectedFile().getAbsolutePath();  // ??? vllt muss hier auch der Name des Projektes gesetzt werden
			listHolder.setLoadingPath(path);
		}
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(listHolder.getLoadingPath() + ".owe"));  // ??? hoffentlich funktioniert die Endung ".owe"
			objectOutputStream.writeObject(listHolder);
		} catch (FileNotFoundException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		} catch (IOException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		}
	}
	
	public static void changeLoadingPath(String path) {
		listHolder.setLoadingPath(path);
	}
	
	public static void loadProject(String path) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
			try {
				listHolder = (ListHolder) objectInputStream.readObject();
				listHolder.setLoadingPath(path);  // mal sehen
			} catch (ClassNotFoundException e) {
				LoggingClass.makeWarningLog(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		} catch (InvalidClassException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		} catch (IOException e) {
			LoggingClass.makeWarningLog(e.getMessage());
		}
	}
	
	public static void printToTXT() {
		listHolder.printToTXT();
	}
	
	public static ListHolder getListHolder() {
		return listHolder;
	}
}
