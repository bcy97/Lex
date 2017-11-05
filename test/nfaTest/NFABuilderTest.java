package nfaTest;

import nfa.NFA;
import nfa.NFABuilder;

public class NFABuilderTest {

	public static void main(String[] args) {
		String re = "((-|d)(d*\\.d*))|((-|d)(d*))";

		NFABuilder nfaBuilder = new NFABuilder();
		NFA nfa = nfaBuilder.createNFA(re);
		
		for (char c : nfa.getAlphabet()) {
			System.out.println(c);
		}
	}

}
