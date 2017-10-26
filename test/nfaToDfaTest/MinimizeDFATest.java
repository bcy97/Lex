package nfaToDfaTest;

import java.util.ArrayList;

import dfa.DFA;
import dfa.DFABulider;
import dfa.DFANode;
import dfa.MinimizeDFA;
import fa.Node;
import nfa.NFA;
import nfa.NFABuilder;

public class MinimizeDFATest {

	public static void main(String[] args) {
//		String re = "(ab*a)(a|b)b*";
//		String re = "ab(a|b)*";
		String re = "(a|b)*a(a|b)(a|b)(a|b)";
		

		NFABuilder nfaBuilder = new NFABuilder();
		NFA nfa = nfaBuilder.createNFA(re);
nfa.print();	
		DFABulider bulider = new DFABulider();
		DFA dfa = bulider.createDFA(nfa);
dfa.print();
		MinimizeDFA minimize = new MinimizeDFA();
		DFA minDfa = minimize.minimizeDFA(dfa);
		
	}
}
