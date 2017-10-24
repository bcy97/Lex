package dfa;

import java.util.ArrayList;

import fa.Node;
import fa.NodeController;
import nfa.NFA;

public class DFA {

	private Node start;
	private Node end;
	private ArrayList<Node> nodes = new ArrayList<>();

	public DFA(char c, NodeController controller) {

		start = controller.CreateNode();
		end = controller.CreateNode();

		start.setEdge1(c);
		start.setNext1(end);
		
		nodes.add(start);
		nodes.add(end);

	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}
}
