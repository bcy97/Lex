package NFA;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class ReHandler {

	public static String reChange(String re) {
		Stack<Character> stack;
		
		for (int i = 0; i < re.length(); i++) {
			if (isChar(re.charAt(i))) {
				queue.add(re.charAt(i));
			}
		}
	}
	
	public static boolean isChar(char a) {
		if (a>='a'&&a<='z'||a>='A'&&a<='Z') {
			return true;
		}
		return false;
	}
	
}
