package nfa;

import java.util.ArrayList;

public class NFA {

	private Node start;
	private Node end;
	private ArrayList<Node> nodes = new ArrayList<>();

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
		
		end.setNext('e', nfa.start);

		this.end = nfa.end;

	}

	public void closure(NodeController controller) {

		Node newStart = controller.CreateNode();
		Node newEnd = controller.CreateNode();
		
		nodes.add(newStart);
		nodes.add(newEnd);

		end.setNext('e', newEnd);
		end.setNext('e', start);

		newStart.setNext('e', start);

		newStart.setNext('e', newEnd);

		this.start = newStart;
		this.end = newEnd;
	}

	public void select(NFA nfa, NodeController controller) {

		Node newStart = controller.CreateNode();
		Node newEnd = controller.CreateNode();
		
		nodes.add(newStart);
		nodes.add(newEnd);

		newStart.setNext('e', this.start);
		newStart.setNext('e', nfa.start);

		this.end.setNext('e', newEnd);
		nfa.end.setNext('e', newEnd);

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

}
