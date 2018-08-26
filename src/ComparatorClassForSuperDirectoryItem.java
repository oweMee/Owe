import java.util.Comparator;

public class ComparatorClassForSuperDirectoryItem implements Comparator<SuperDirectoryItem>{

	@Override
	public int compare(SuperDirectoryItem arg0, SuperDirectoryItem arg1) {
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
