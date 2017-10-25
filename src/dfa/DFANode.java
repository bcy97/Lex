package dfa;

import java.util.ArrayList;

import fa.Node;

public class DFANode extends Node {

	private ArrayList<Node> nfaNodes;

	public DFANode(int id, ArrayList<Node> nfaNodes) {
		super(id);
		this.nfaNodes = nfaNodes;
	}

	public boolean equals(DFANode dfaNode) {
		if (this.nfaNodes.equals(dfaNode.getNfaNodes())) {
			return true;
		}
		return false;
	}

	public ArrayList<DFANode> getNexts() {
		ArrayList<DFANode> dfaNodes = new ArrayList<>();
		if (this.getNext1() != null) {
			dfaNodes.add((DFANode) this.getNext1());
		}
		if (this.getNext2() != null) {
			dfaNodes.add((DFANode) this.getNext2());
		}
		return dfaNodes;
	}

	public ArrayList<Node> getNfaNodes() {
		return nfaNodes;
	}

	public void setNfaNodes(ArrayList<Node> nfaNodes) {
		this.nfaNodes = nfaNodes;
	}

}
