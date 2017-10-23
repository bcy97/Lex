package fa;

public class NodeController {

	private int id = 0;

	public Node CreateNode() {
		Node node = new Node(id);
		id++;
		return node;
	}

}
