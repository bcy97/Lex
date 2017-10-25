package fa;

import java.util.ArrayList;

public class Node {

	private int nodeID;

	private Node next1;
	private Node next2;

	private char edge1;
	private char edge2;

	public Node(int id) {
		this.nodeID = id;
	}

	public void setNext(char edge, Node next) {

		if (next != null && next1 == null) {
			this.next1 = next;
			this.edge1 = edge;
		} else if (next1 != null&&next2==null) {
			this.next2 = next;
			this.edge2 = edge;
		}
	}
	
	public ArrayList<Node> getNext(char edge){
		
		ArrayList<Node> nexts=new ArrayList<>();
		
		if (this.edge1==edge&&this.next1!=null) {
			nexts.add(this.next1);
		}
		if (this.edge2==edge&&this.next2!=null) {
			nexts.add(this.next2);
		}
		
		return nexts;
	}

	public ArrayList<Node> getNextAll() {

		ArrayList<Node> nexts = new ArrayList<>();

		if (next1 != null) {
			nexts.add(next1);
		}

		if (next2 != null) {
			nexts.add(next2);
		}

		return nexts;
	}
	
	@Override
	public boolean equals(Object node) {
		if (this.nodeID==((Node)node).getNodeID()) {
			return true;
		}
		return false;
	}
	
	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public Node getNext1() {
		return next1;
	}

	public void setNext1(Node next1) {
		this.next1 = next1;
	}

	public Node getNext2() {
		return next2;
	}

	public void setNext2(Node next2) {
		this.next2 = next2;
	}

	public char getEdge1() {
		return edge1;
	}

	public void setEdge1(char edge1) {
		this.edge1 = edge1;
	}

	public char getEdge2() {
		return edge2;
	}

	public void setEdge2(char edge2) {
		this.edge2 = edge2;
	}
}
