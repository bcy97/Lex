package dfa;

import java.util.ArrayList;

import fa.Node;

public class DFANodeController {

	private int id = 0;

	public DFANode CreateNode(ArrayList<Node> nfaNodes) {
		DFANode node = new DFANode(id,nfaNodes);
		id++;
		return node;
	}
}
