package dfa;

import java.util.ArrayList;

import fa.Node;

public class DFA {
	
	private ArrayList<DFANode> startNodes;
	private ArrayList<DFANode> endNodes;
	private ArrayList<Character> alphabet;

	public DFA(ArrayList<DFANode> startNodes, ArrayList<DFANode> endNodes) {
		this.startNodes = startNodes;
		this.endNodes = endNodes;
	}

	public void print() {
		System.out.println("startNodes :");
		for (DFANode dfaNode : startNodes) {
			System.out.print("node"+dfaNode.getNodeID()+":");
			for (Node node : dfaNode.getNfaNodes()) {
				System.out.print(node.getNodeID()+" ");
			}
			System.out.println();
			if (dfaNode.getNext1() != null) {
				System.out.print(
						dfaNode.getNodeID() + "-" + dfaNode.getEdge1() + "->" + dfaNode.getNext1().getNodeID() + "   ");
			}
			if (dfaNode.getNext2() != null) {
				System.out.println(
						dfaNode.getNodeID() + "-" + dfaNode.getEdge2() + "->" + dfaNode.getNext2().getNodeID());
			}
		}
		System.out.println("endNodes :");
		for (DFANode dfaNode : endNodes) {
			System.out.print("node"+dfaNode.getNodeID()+":");
			System.out.println();
			for (Node node : dfaNode.getNfaNodes()) {
				System.out.print(node.getNodeID()+" ");
			}
			if (dfaNode.getNext1() != null) {
				System.out.print(
						dfaNode.getNodeID() + "-" + dfaNode.getEdge1() + "->" + dfaNode.getNext1().getNodeID() + "   ");
			}
			if (dfaNode.getNext2() != null) {
				System.out.println(
						dfaNode.getNodeID() + "-" + dfaNode.getEdge2() + "->" + dfaNode.getNext2().getNodeID());
			}
		}
	}

	public ArrayList<DFANode> getStartNodes() {
		return startNodes;
	}

	public void setStartNodes(ArrayList<DFANode> startNodes) {
		this.startNodes = startNodes;
	}

	public ArrayList<DFANode> getEndNodes() {
		return endNodes;
	}

	public void setEndNodes(ArrayList<DFANode> endNodes) {
		this.endNodes = endNodes;
	}

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<Character> alphabet) {
		this.alphabet = alphabet;
	}

}
