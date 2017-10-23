package nfaToDfaTest;

import java.util.ArrayList;
import java.util.Iterator;

import dfa.DFABulider;
import fa.Node;
import nfa.NFA;
import nfa.NFABuilder;

public class NfaToDfaTest {

	public static void main(String[] args) {
		NfaToDfaTest();
	}

	public static void NfaToDfaTest() {
		String re = "ab*(a|b)*";

		NFABuilder nfaBuilder = new NFABuilder();

		NFA nfa = nfaBuilder.createNFA(re);

		DFABulider transfer = new DFABulider();

		ArrayList<Node> start = new ArrayList<>();
		start.add(nfa.getStart());
//		start.add(nfa.getStart().getNext1().getNext1());
		// start.add(nfa.getStart().getNext1().getNext1());
		// start.add(nfa.getStart().getNext1().getNext1());
		ArrayList<Node> result = transfer.find_E_Closure(start);

//		for (Node node : result) {
//			System.out.println(node.getNodeID());
//		}
		System.out.println(result==start);

	}
}
