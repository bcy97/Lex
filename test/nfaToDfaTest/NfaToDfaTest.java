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
		String re = "ab*(a|b)*";

		NFABuilder nfaBuilder = new NFABuilder();

		NFA nfa = nfaBuilder.createNFA(re);

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
				System.out.print(dfaNode.getEdge1()+" ");
			}
			if (dfaNode.getNext2()!=null) {
				System.out.print(dfaNode.getEdge2());
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
				System.out.print(dfaNode.getEdge1()+" ");
			}
			if (dfaNode.getNext2()!=null) {
				System.out.print(dfaNode.getEdge2());
			}
			System.out.println();
		}

		// ArrayList<DFANode> nodes=new ArrayList<>();
		//
		// ArrayList<Node> I0_closure=new ArrayList<>();
		// I0_closure.add(nfa.getStart());
		// DFANode I0=new DFANode(0, I0_closure);
		// nodes.add(I0);
		//
		// ArrayList<Node>
		// I1_closure=bulider.find_E_Closure(bulider.find_next(I0_closure, 'a'));
		// DFANode I1=new DFANode(0, I1_closure);
		// nodes.add(I1);
		//
		// ArrayList<Node>
		// I2_closure=bulider.find_E_Closure(bulider.find_next(I1_closure, 'b'));
		// DFANode I2=new DFANode(0, I2_closure);
		// nodes.add(I2);
		//
		// ArrayList<Node>
		// test_closure=bulider.find_E_Closure(bulider.find_next(I2_closure, 'b'));
		// DFANode test=new DFANode(0, test_closure);
		//
		// System.out.println(nodes.contains(test));
	}
}
