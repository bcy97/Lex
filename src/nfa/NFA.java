package nfa;

public class NFA {

	public Node start;
	public Node end;

	public NFA(char c, NodeController controller) {

		start = controller.CreateNode();
		end = controller.CreateNode();

		start.setEdge1(c);
		start.setNext1(end);

	}

	public void join(NFA nfa) {

		if (end.getNext1() != null && end.getNext2() != null) {
			System.out.println("wrong");
			return;
		}

		end.setNext('e', nfa.start);

		this.end = nfa.end;

	}

	public void closure(NodeController controller) {

		Node newStart = controller.CreateNode();
		Node newEnd = controller.CreateNode();

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

		newStart.setNext('e', this.start);
		newStart.setNext('e', nfa.start);

		this.end.setNext('e', newEnd);
		nfa.end.setNext('e', newEnd);

		this.start = newStart;
		this.end = newEnd;
	}

}
