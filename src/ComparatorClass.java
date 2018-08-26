
public class ComparatorClass {

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
