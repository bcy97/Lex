package main;

public class CharHandler {

	public static String change(String s) {

		String result = "";

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
				result += "l";
			} else if (c >= '0' && c <= '9') {
				result += "d";
			} else {
				result += c;
			}
		}

		return result;

	}

}
