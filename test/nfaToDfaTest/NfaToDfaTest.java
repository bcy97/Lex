package nfaToDfaTest;

import java.util.ArrayList;
import java.util.Iterator;

import dfa.DFA;
import dfa.DFABulider;
import dfa.DFANode;
import fa.Node;
import nfa.NFA;
import nfa.NFABuilder;

public class NfaToDfaTest {

	public static void main(String[] args) {
		NfaToDfaTest();
	}

	public static void NfaToDfaTest() {
		
		String re = "(a|b)a*c";

		NFABuilder nfaBuilder = new NFABuilder();

		NFA nfa = nfaBuilder.createNFA(re);
nfa.print();
		DFABulider bulider = new DFABulider();

		DFA dfa = bulider.createDFA(nfa);
dfa.print();
	}
}
