package dfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fa.Node;

public class DFANode {

	private int nodeID;

	private HashMap<Character, DFANode> nexts;

	private ArrayList<Node> nfaNodes;

	public DFANode(int id, ArrayList<Node> nfaNodes) {
		this.nodeID = id;
		this.nfaNodes = nfaNodes;
		this.nexts=new HashMap<>();
	}

	@Override
	public boolean equals(Object dfaNode) {
		if (dfaNode instanceof DFANode && this.getNodeID() == ((DFANode) dfaNode).getNodeID()) {
			if (this.getNfaNodes() == null && ((DFANode) dfaNode).getNfaNodes() == null) {
				return true;
			} else if (this.nfaNodes.equals(((DFANode) dfaNode).getNfaNodes())) {

				return true;
			}
		}
		return false;

	}

	public void setNext(char edge, DFANode dfaNode) {
		if (nexts.containsKey(edge)) {
			return;
		} else {
			nexts.put(edge, dfaNode);
		}
	}

	public ArrayList<DFANode> getNextDFA() {
		ArrayList<DFANode> next = new ArrayList<>();
		next = (ArrayList<DFANode>) nexts.values();
		return next;
	}

	public DFANode getNextDFA(char edge) {
		return nexts.get(edge);
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public Map<Character, DFANode> getNexts() {
		return nexts;
	}

	public void setNexts(HashMap<Character, DFANode> nexts) {
		this.nexts = nexts;
	}

	public ArrayList<Node> getNfaNodes() {
		return nfaNodes;
	}

	public void setNfaNodes(ArrayList<Node> nfaNodes) {
		this.nfaNodes = nfaNodes;
	}

}
