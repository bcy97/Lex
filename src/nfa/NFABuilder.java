package nfa;

import java.util.ArrayList;
import java.util.Stack;

import fa.NodeController;

public class NFABuilder {

	public NodeController controller = new NodeController();

	public NFA createNFA(String re) {

		// 存放字母表
		ArrayList<Character> alphabet = new ArrayList<>();

		String newRe = ReHandler.reChange(re);
//		System.out.println(newRe);

		Stack<NFA> nfaStack = new Stack<>();

		for (int i = 0; i < newRe.length(); i++) {
			char c = newRe.charAt(i);

			// if c is character, create a new nfa and push it into the nfastack
			if (ReHandler.isChar(c)) {

				// 将新字符放入字母表
				if (!alphabet.contains(c)) {
					alphabet.add(c);
				}

				NFA nfa = new NFA(c, controller);
				nfaStack.push(nfa);
			} else if (c == '\\') {
				
				char next = newRe.charAt(i+1);
				
				if (!alphabet.contains(next)) {
					alphabet.add(next);
				}

				NFA nfa = new NFA(next, controller);
				nfaStack.push(nfa);
				i++;

				// if c is ., pop the top two elements in the stack and join
				// them, then push the new nfa into the stack
			} else if (c == '.') {
				NFA first = null;
				NFA second = null;
				try {
					first = nfaStack.pop();
					second = nfaStack.pop();
					second.join(first);
					nfaStack.push(second);

				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

				// if c is *, pop the top element in the stack and do the
				// function of closure
			} else if (c == '*') {
				try {
					NFA nfa = nfaStack.pop();
					nfa.closure(controller);
					nfaStack.push(nfa);

				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

			} else if (c == '|') {
				NFA first = null;
				NFA second = null;
				try {
					first = nfaStack.pop();
					second = nfaStack.pop();
					second.select(first, controller);
					nfaStack.push(second);

				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

			}
		}

		nfaStack.peek().setAlphabet(alphabet);

		return nfaStack.pop();
	}

}
