package nfa;

import java.util.ArrayList;

import fa.Node;
import fa.NodeController;

public class NFA {

	private Node start;
	private Node end;
	private ArrayList<Node> nodes = new ArrayList<>();
	private ArrayList<Character> alphabet = new ArrayList<>();

	public NFA(char c, NodeController controller) {

		start = controller.CreateNode();
		end = controller.CreateNode();

		start.setEdge1(c);
		start.setNext1(end);

		nodes.add(start);
		nodes.add(end);

	}

	public void join(NFA nfa) {

		if (end.getNext1() != null && end.getNext2() != null) {
			System.out.println("wrong");
			return;
		}

		for (Node node : nfa.getNodes()) {
			nodes.add(node);
		}

		end.setNext('#', nfa.start);

		this.end = nfa.end;

	}

	public void closure(NodeController controller) {

		Node newStart = controller.CreateNode();
		Node newEnd = controller.CreateNode();

		nodes.add(newStart);
		nodes.add(newEnd);

		end.setNext('#', newEnd);
		end.setNext('#', start);

		newStart.setNext('#', start);

		newStart.setNext('#', newEnd);

		this.start = newStart;
		this.end = newEnd;
	}

	public void select(NFA nfa, NodeController controller) {

		this.nodes.addAll(nfa.nodes);

		Node newStart = controller.CreateNode();
		Node newEnd = controller.CreateNode();

		nodes.add(newStart);
		nodes.add(newEnd);

		newStart.setNext('#', this.start);
		newStart.setNext('#', nfa.start);

		this.end.setNext('#', newEnd);
		nfa.end.setNext('#', newEnd);

		this.start = newStart;
		this.end = newEnd;
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

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<Character> alphabet) {
		this.alphabet = alphabet;
	}

	public void print() {
		for (Node node : nodes) {
			if (node.getNext1() != null) {
				System.out.println(node.getNodeID() + "-" + node.getEdge1() + "->" + node.getNext1().getNodeID());
			}
			if (node.getNext2() != null) {
				System.out.println(node.getNodeID() + "-" + node.getEdge2() + "->" + node.getNext2().getNodeID());
			}
		}
	}

}
