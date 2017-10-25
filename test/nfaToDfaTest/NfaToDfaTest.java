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
		
		//检测nfa初始和末状态
		ArrayList<DFANode> startnode=dfa.getStartNodes();
		System.out.println("startnode number:"+startnode.size());
		for (DFANode dfaNode : startnode) {
			for (Node node : dfaNode.getNfaNodes()) {
				System.out.print(node.getNodeID()+" ");
			}
			System.out.print("             ");
			if (dfaNode.getNext1()!=null) {
				System.out.print(dfaNode.getNodeID()+"-"+dfaNode.getEdge1()+"->"+dfaNode.getNext1().getNodeID()+"   ");
			}
			if (dfaNode.getNext2()!=null) {
				System.out.print(dfaNode.getNodeID()+"-"+dfaNode.getEdge2()+"->"+dfaNode.getNext2().getNodeID());
			}
			System.out.println();
		}
		ArrayList<DFANode> endNode=dfa.getEndNodes();
		System.out.println("endnode number:"+endNode.size());
		for (DFANode dfaNode : endNode) {
			for (Node node : dfaNode.getNfaNodes()) {
				System.out.print(node.getNodeID()+" ");
			}
			System.out.print("             ");
			if (dfaNode.getNext1()!=null) {
				System.out.print(dfaNode.getNodeID()+"-"+dfaNode.getEdge1()+"->"+dfaNode.getNext1().getNodeID());
			}
			System.out.print("   ");
			if (dfaNode.getNext2()!=null) {
				System.out.print(dfaNode.getNodeID()+"-"+dfaNode.getEdge2()+"->"+dfaNode.getNext2().getNodeID());
			}
			System.out.println();
		}

		
	}
}
