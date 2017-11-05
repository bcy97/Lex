package nfa;

import java.util.Stack;

public class ReHandler {

	public static String reChange(String re) {
		Stack<Character> stack = new Stack<>();
		char[] reNew = new char[re.length() * 2];
		int index = 0;

		for (int i = 0; i < re.length(); i++) {
			char c = re.charAt(i);
			if (isChar(c)) {
				reNew[index] = c;
				index++;
				if (i < re.length() - 1
						&& (re.charAt(i + 1) == '(' || isChar(re.charAt(i + 1)) || (re.charAt(i + 1) == '\\'))) {
					while (!stack.isEmpty() && 2 <= judge(stack.peek())) {
						reNew[index] = stack.pop();
						index++;
					}
					stack.push('.');
				}
			} else {

				// if current char is \ ,put the char after \ in to new re
				if (c == '\\') {

					reNew[index] = c;
					index++;
					reNew[index] = re.charAt(i + 1);
					index++;
					i++;

					if (i < re.length() - 1
							&& (re.charAt(i + 1) == '(' || isChar(re.charAt(i + 1)) || (re.charAt(i + 1) == '\\'))) {
						while (!stack.isEmpty() && 2 <= judge(stack.peek())) {
							reNew[index] = stack.pop();
							index++;
						}
						stack.push('.');
					}

				}

				// if is (, push ( into the stack
				if (c == '(') {
					stack.push(c);
				}

				// if is ),pop all the elements until meet (
				if (c == ')') {
					while (!stack.isEmpty() && stack.peek() != '(') {
						reNew[index] = stack.pop();
						index++;
					}
					stack.pop();
					if (i < re.length() - 1
							&& (re.charAt(i + 1) == '(' || isChar(re.charAt(i + 1)) || (re.charAt(i + 1) == '\\'))) {
						while (!stack.isEmpty() && 2 <= judge(stack.peek())) {
							reNew[index] = stack.pop();
							index++;
						}
						stack.push('.');
					}
				}

				// if is *, add it to the result string,then add a new . to the
				// stack
				if (c == '*') {
					reNew[index] = c;
					index++;
					if (i < re.length() - 1
							&& (re.charAt(i + 1) == '(' || isChar(re.charAt(i + 1)) || (re.charAt(i + 1) == '\\'))) {
						while (!stack.isEmpty() && 2 <= judge(stack.peek())) {
							reNew[index] = stack.pop();
							index++;
						}
						stack.push('.');
					}
				}

				if (c == '|') {
					while (!stack.isEmpty() && judge(c) <= judge(stack.peek())) {
						reNew[index] = stack.pop();
						index++;
					}
					stack.push(c);
				}
			}

		}

		while (!stack.isEmpty()) {
			reNew[index] = stack.pop();
			index++;
		}

		String result = String.copyValueOf(reNew);

		return result;
	}

	public static boolean isChar(char a) {
		if (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z' || a == '_' || a == '-' || a == '=' || a == '+' || a == '/'
				|| a == ';' || a == '<' || a == '>' || a == '{' || a == '}'||a=='"') {
			// if (a!='|'&&a!='*'&&a!='('&&a!=')'&&a!='\\'&&a!='.') {
			return true;
		}
		return false;
	}

	public static int judge(char c) {
		switch (c) {
		case '(':
			return 0;
		case '|':
			return 1;
		case '.':
			return 2;
		}
		return 0;
	}

}
