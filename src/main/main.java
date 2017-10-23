package main;

import nfa.NFA;
import nfa.NFABuilder;

public class main {
	public static void main(String[] args) {
		String re = "ab*(a|b)*";

		NFABuilder nfaBuilder = new NFABuilder();

		NFA nfa = nfaBuilder.createNFA(re);
	}
}
