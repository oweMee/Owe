import java.util.Comparator;

// vergleicht zwei ListItems nach alphabetischen Reihenfolge der Namen
public class ComparatorClassForListItem implements Comparator<ListItem> {

	@Override
	public int compare(ListItem arg0, ListItem arg1) {
		String s1 = toLowerCase(arg0.getName());
		String s2 = toLowerCase(arg1.getName());
		return s1.compareTo(s2);
	}

	public static String toLowerCase(String string) {
		String result  = "";
		for (int i = 0; i < string.length(); i++) {
			if (Character.isAlphabetic(string.charAt(i))) {
				result += Character.toLowerCase(string.charAt(i));
			}
			else {
				result += string.charAt(i);
			}
		}
		return result;
	}
}
