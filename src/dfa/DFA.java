package dfa;

import java.util.ArrayList;

public class DFA {
	
	private ArrayList<DFANode> startNodes;
	private ArrayList<DFANode> endNodes;
	
	public DFA(ArrayList<DFANode> startNodes,ArrayList<DFANode> endNodes) {
		this.startNodes=startNodes;
		this.endNodes=endNodes;
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
	
}
