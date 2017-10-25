package nfaToDfaTest;

import dfa.DFA;
import dfa.DFABulider;
import dfa.MinimizeDFA;
import nfa.NFA;
import nfa.NFABuilder;

public class MinimizeDFATest {

	
	public static void main(String[] args) {
		String re = "((ba*)*a)*(a|b)";

		NFABuilder nfaBuilder = new NFABuilder();
		NFA nfa = nfaBuilder.createNFA(re);

		DFABulider bulider = new DFABulider();
		DFA dfa = bulider.createDFA(nfa);
		
		MinimizeDFA minimize=new MinimizeDFA();
		DFA minDfa=minimize.minimizeDFA(dfa);
	}
}
