import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;

// halt die Listen des jeweilen Projekts. Diese Klasse wird gespeichert und geladen
// Anmerkung: die Methoden findVerySimilar und shiftedSimilar sollten noch mit ComparatorClass.toLowerCase ausgestattet werden
public class ListHolder implements Serializable {
	
	private static final long serialVersionUID = 9073858431619904619L;  // von Eclipse generiert
	
	private double versionNumber = 0.1;
	
	private int settingFlags;
	
	private String projectName;
	private String loadingPath = null;

	private ArrayList<ListItem> listItemList = new ArrayList<>();
	private ArrayList<SuperDirectoryItem> superDirectoryItemList = new ArrayList<>();
	
	public void addListItem(ListItem item) {
		listItemList.add(item);
	}
	
	public String getListItemInfoByIndex(int index) {
		return listItemList.get(index).getInfoForList();
	}
	
	public String getSuperDirectoryItemNameByIndex(int index) {
		return superDirectoryItemList.get(index).getName();
	}
	
	public void sortLists() {
		Collections.sort(listItemList, new ComparatorClassForListItem());
		Collections.sort(superDirectoryItemList, new ComparatorClassForSuperDirectoryItem());  // sollte wegen der Namengebung eigentlich weg
	}
	
	public void printToTXT() {
		JFileChooser chooser = new JFileChooser();
		if (loadingPath != null) {
			chooser.setCurrentDirectory(new File(loadingPath));
		}
		int stateOfChoosing = chooser.showSaveDialog(null);
		if (stateOfChoosing == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = chooser.getSelectedFile();
				FileWriter writer = new FileWriter(selectedFile);  // ??? braucht man hier vllt stattdessen den Path?
				for (int i = 0; i < listItemList.size(); i++) {
					writer.write(listItemList.get(i).getInfoForTXT() + "\r\n");
				}
				writer.close();
				selectedFile.renameTo(new File(selectedFile.getPath() + ".txt"));
			} catch (IOException e) {
				LoggingClass.makeLog(e.getMessage(), 'w');
			}
		}
		
	}
	
	public ArrayList<Integer> searchItem(String name){
		if (name.equals("")) {
			return new ArrayList<Integer>();  // wenn kein Name gesucht wird, wird keine Zahl weiter gegebeb
		}
		boolean[] ifGetInList = new boolean[listItemList.size()];
		for (int i = 0; i < ifGetInList.length; i++) {
			ifGetInList[i] = false;
		}
		name = ComparatorClass.toLowerCase(name);
		for (int i = 0; i < listItemList.size(); i++) {  // simiple Suche, wo der Anfang der Strings verglichen werden
			String searchedString = name;
			String stringToCompare = ComparatorClass.toLowerCase(listItemList.get(i).getName());
			searchedString = cutStringIfNeeded(searchedString, stringToCompare);
			stringToCompare = cutStringIfNeeded(stringToCompare, searchedString);
			if (searchedString.equals(stringToCompare)) {
				ifGetInList[i] = true;
			}
		}
		for (int i = 0; i < listItemList.size(); i++) {
			String stringToCompare = ComparatorClass.toLowerCase(listItemList.get(i).getName());
			Scanner scanner = new Scanner(stringToCompare);
			ArrayList<String> wordsOfString = new ArrayList<>();
			while (scanner.hasNext()) {
				wordsOfString.add(scanner.next());
			}
			for (int j = 0; j < wordsOfString.size(); j++) {
				String searchedString = name;
				String wordToCompare = wordsOfString.get(j);
				searchedString = cutStringIfNeeded(searchedString, wordToCompare);
				wordToCompare = cutStringIfNeeded(wordToCompare, searchedString);
				if (searchedString.equals(wordToCompare)) {
					ifGetInList[i] = true;
					break;  // es muss nicht mehr weiter gesucht werden, wenn ein Wort eine Übereinstimmung haben
				}
			}
		}
		ArrayList<Integer> searchedList = new ArrayList<>();
		for (int i = 0; i < ifGetInList.length; i++) {
			if (ifGetInList[i]) {
				searchedList.add(i);  // der Index der gesuchten Items wird gespeichert
			}
		}
		return searchedList;
	}
	
	public ArrayList<Integer> findSimilar(){
		boolean[] ifGetInList = new boolean[listItemList.size()];
		for (int i = 0; i < ifGetInList.length; i++) {
			ifGetInList[i] = false;
		}
		
		ArrayList<Integer> verySimilarList = findVerySimilar();
		for (int i = 0; i < verySimilarList.size(); i++) {
			ifGetInList[verySimilarList.get(i)] = true;  // die Werte, die von findVerySimilar sind, werden übernommen
		}
		/*  // experimentell
		ArrayList<Integer> shiftedSimilarList = findShiftedSimilar();
		for (int i = 0; i < shiftedSimilarList.size(); i++) {
			ifGetInList[shiftedSimilarList.get(i)] = true;  // die Werte, die von findShiftedSimilar sind, werden auch übernommen
		}
		*/
		for (int i = 0; i < listItemList.size(); i++) {
			for (int j = 0; j < listItemList.size(); j++) {
				if (i != j) {  // Item darf nicht mit sich selbst verglichen werden
					String s1 = ComparatorClass.toLowerCase(listItemList.get(i).getName());
					String s2 = ComparatorClass.toLowerCase(listItemList.get(j).getName());
					s1 = cutStringIfNeeded(s1, s2);
					s2 = cutStringIfNeeded(s2,s1);
					if (s1.length() > 6) {
						s1 = s1.substring(0, 5);
						s2 = s2.substring(0, 5);
					}
					if (s1.equals(s2)) {
						ifGetInList[i] = true;
						ifGetInList[j] = true;
					}
				}
			}
		}
		
		ArrayList<Integer> similarList = new ArrayList<>();
		for (int i = 0; i < ifGetInList.length; i++) {
			if (ifGetInList[i]) {
				similarList.add(i);  // der Index der ähnlichen Items wird gespeichert
			}
		}
		return similarList;
	}
	
	private String cutStringIfNeeded(String s1, String s2) {
		if (s1.length() > s2.length()) {
			s1 = s1.substring(0, s2.length());
		}
		return s1;
	}
	
	public ArrayList<Integer> findVerySimilar(){
		boolean[] ifGetInList = new boolean[listItemList.size()];
		for (int i = 0; i < ifGetInList.length; i++) {
			ifGetInList[i] = false;
		}
		
		for (int i = 0; i < listItemList.size(); i++) {
			for (int j = 0; j < listItemList.size(); j++) {
				if (i != j) {
					String s1 = listItemList.get(i).getName();
					String s2 = listItemList.get(j).getName();
					s1 = cutStringIfNeeded(s1, s2);
					s2 = cutStringIfNeeded(s2,s1);
					double percentagePerChar = 1 / s1.length();  // Prozentsatz der für jeden gleichen Buchstaben gegeben wird
					double wholePercentage = 0;
					for (int h = 0; h < s1.length(); h++) {
						if (s1.charAt(h) == s2.charAt(h)) {
							wholePercentage += percentagePerChar;
						}
					}
					if (wholePercentage > 0.75) {  //wenn die Namen zu 75% übereinstimmen, werden sie gespeichert
						ifGetInList[i] = true;
						ifGetInList[j] = true;
					}
				}
			}
		}
		ArrayList<Integer> verySimilarList = new ArrayList<>();
		for (int i = 0; i < ifGetInList.length; i++) {
			if (ifGetInList[i]) {
				verySimilarList.add(i);  // der Index der ähnlichen Items wird gespeichert
			}
		}
		return verySimilarList;
	}
	
	public ArrayList<Integer> findShiftedSimilar(){
		boolean[] ifGetInList = new boolean[listItemList.size()];
		for (int i = 0; i < ifGetInList.length; i++) {
			ifGetInList[i] = false;
		}
		for (int i = 0; i < listItemList.size(); i++) {
			for (int j = 0; j < listItemList.size(); j++) {
				if (i != j) {
					String s1 = listItemList.get(i).getName();
					String s2 = listItemList.get(j).getName();
					s1 = shiftIfNeeded(s1);
					s2 = shiftIfNeeded(s2);
					s1 = cutStringIfNeeded(s1, s2);
					s2 = cutStringIfNeeded(s2,s1);
					double percentagePerChar = 1 / s1.length();  // Prozentsatz der für jeden gleichen Buchstaben gegeben wird
					double wholePercentage = 0;
					for (int h = 0; h < s1.length(); h++) {
						if (s1.charAt(h) == s2.charAt(h)) {
							wholePercentage += percentagePerChar;
						}
					}
					if (wholePercentage > 0.75) {  //wenn die Namen zu 75% übereinstimmen, werden sie gespeichert
						ifGetInList[i] = true;
						ifGetInList[j] = true;
					}
				}
			}
		}
		ArrayList<Integer> shiftedSimilarList = new ArrayList<>();
		for (int i = 0; i < ifGetInList.length; i++) {
			if (ifGetInList[i]) {
				shiftedSimilarList.add(i);  // der Index der ähnlichen Items wird gespeichert
			}
		}
		return shiftedSimilarList;
	}
	
	private String shiftIfNeeded(String s) {
		String shiftedString = "";
		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isWhitespace(s.charAt(i)))) {  //Whitespacezeichen werden verworfen (??? vllt sollten auch Sonderziechen verworfen werden)
				shiftedString += s.charAt(i);
			}
		}
		return shiftedString;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getLoadingPath() {
		return loadingPath;
	}
	
	public void setLoadingPath(String path) {
		loadingPath = path;
	}
	
	public int getHighestSuperDirectoryIndex() {
		int result = 0;
		for (int i = 0; i < superDirectoryItemList.size(); i++) {
			int actualIndex = superDirectoryItemList.get(i).getIndex();
			if (actualIndex > result) {
				result = actualIndex;
			}
		}
		return result;
	}
	
	public void addSuperDirectoryItem(SuperDirectoryItem item) {
		superDirectoryItemList.add(item);
	}
	
	public ListItem getListItemByIndex(int index) {
		return listItemList.get(index);
	}
	
	public void updateList() {
		ArrayList<String> pathList = new ArrayList<>();
		for (int i = 0; i < superDirectoryItemList.size(); i++) {
			String path = superDirectoryItemList.get(i).getPath();
			pathList.add(path);
		}
		superDirectoryItemList = new ArrayList<SuperDirectoryItem>();
		listItemList = new ArrayList<ListItem>();
		for (int i = 0; i < pathList.size(); i++) {
			MakeList.makeList(new File(pathList.get(i)));
		}
	}
	
	public void deleteSuperDirectoryByPosition(int position) {
		ArrayList<SuperDirectoryItem> newDirectoryList = new ArrayList<>();
		for (int i = 0; i < superDirectoryItemList.size(); i++) {
			if (i != position) {
				newDirectoryList.add(superDirectoryItemList.get(i));
			}
		}
		int indexOfDeletingDirectory = superDirectoryItemList.get(position).getIndex();
		ArrayList<ListItem> newListItemList = new ArrayList<>();
		for (int i = 0; i < listItemList.size(); i++) {
			if (listItemList.get(i).getSuperDirectoryIndex() != indexOfDeletingDirectory) {
				newListItemList.add(listItemList.get(i));
			}
		}
		
		superDirectoryItemList = newDirectoryList;
		listItemList = newListItemList;
	}
	
	public int getListItemListSize() {
		return listItemList.size();
	}
	
	public int getSuperDirectoryListSize() {
		return superDirectoryItemList.size();
	}
}
