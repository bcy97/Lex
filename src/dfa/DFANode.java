package dfa;

import java.util.ArrayList;

import fa.Node;

public class DFANode extends Node {

	private ArrayList<Node> nfaNodes;

	public DFANode(int id, ArrayList<Node> nfaNodes) {
		super(id);
		this.nfaNodes = nfaNodes;
	}

	@Override
	public boolean equals(Object dfaNode) {
		if (dfaNode instanceof DFANode&&this.nfaNodes.equals(((DFANode)dfaNode).getNfaNodes())) {
			return true;
		}
		return false;
	}
	
	public ArrayList<DFANode> getNextDFA() {
		ArrayList<DFANode> nexts = new ArrayList<>();
		if (this.getNext1()!=null) {
			nexts.add((DFANode)this.getNext1());
		}
		if (this.getNext2()!=null) {
			nexts.add((DFANode)this.getNext2());
		}
		return nexts;
	}
	
	public ArrayList<DFANode> getNextDFA(char edge) {
		ArrayList<DFANode> nexts = new ArrayList<>();
		if (this.getEdge1()==edge&&this.getNext1()!=null) {
			nexts.add((DFANode)this.getNext1());
		}
		if (this.getEdge2()==edge&&this.getNext2()!=null) {
			nexts.add((DFANode)this.getNext2());
		}
		return nexts;
	}

	public ArrayList<Node> getNfaNodes() {
		return nfaNodes;
	}

	public void setNfaNodes(ArrayList<Node> nfaNodes) {
		this.nfaNodes = nfaNodes;
	}

}
