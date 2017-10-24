package dfa;

import java.util.ArrayList;

import fa.Node;

public class DFANode extends Node{
	
	private ArrayList<Node> nfaNodes;

	public boolean equals(DFANode dfaNode) {
		if (this.nfaNodes.equals(dfaNode.getNfaNodes())) {
			return true;
		}
		return false;
	}

	public DFANode(int id,ArrayList<Node> nfaNodes) {
		super(id);
		this.nfaNodes=nfaNodes;
	}

	public ArrayList<Node> getNfaNodes() {
		return nfaNodes;
	}
	
	public void setNfaNodes(ArrayList<Node> nfaNodes) {
		this.nfaNodes = nfaNodes;
	}
	
}
