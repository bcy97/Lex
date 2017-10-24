package dfa;

import java.util.ArrayList;

import fa.Node;
import fa.NodeController;
import nfa.NFA;

public class DFABulider {

	NodeController nodeController;

	public DFABulider() {
		nodeController = new NodeController();
	}

	public DFA createDFA(NFA nfa) {
		ArrayList<ArrayList<Node>> dfaNodes = new ArrayList<>();
		ArrayList<ArrayList<Node>> nodeNotVisited = new ArrayList<>();

		ArrayList<Node> start = new ArrayList<>();
		start.add(nfa.getStart());
		dfaNodes.add(find_E_Closure(start));
		nodeNotVisited.add(find_E_Closure(start));

		while (!nodeNotVisited.isEmpty()) {
			ArrayList<Node> nodes = nodeNotVisited.get(nodeNotVisited.size() - 1);
			Node node=nodeController.CreateNode();
			
			ArrayList<Node> nodes_a_edge=find_E_Closure(find_next(nodes, 'a'));
			if (nodes_a_edge!=null&&!dfaNodes.contains(nodes_a_edge)&&!nodeNotVisited.contains(nodes_a_edge)) {
				Node node_a_next=nodeController.CreateNode();
				node.setNext('a', node_a_next);
				nodeNotVisited.add(nodes_a_edge);
			}
			
			ArrayList<Node> nodes_b_edge=find_E_Closure(find_next(nodes, 'b'));
			if (nodes_b_edge!=null&&!dfaNodes.contains(nodes_b_edge)&&!nodeNotVisited.contains(nodes_b_edge)) {
				Node node_b_next=nodeController.CreateNode();
				node.setNext('b', node_b_next);
				nodeNotVisited.add(nodes_b_edge);
			}
			
		}

		return null;
	}

	public ArrayList<Node> find_E_Closure(ArrayList<Node> nodes) {
		while (!hasAll(nodes)) {
			ArrayList<Node> temp = new ArrayList<>();
			for (Node node : nodes) {
				if (node.getEdge1() == 'e' && !nodes.contains(node.getNext1()) && !temp.contains(node.getNext1())) {
					temp.add(node.getNext1());
				}
				if (node.getEdge2() == 'e' && !nodes.contains(node.getNext2()) && !temp.contains(node.getNext2())) {
					temp.add(node.getNext2());
				}
			}
			nodes.addAll(temp);
		}
		return nodes;
	}

	public ArrayList<Node> find_next(ArrayList<Node> nodes, char edge) {

		ArrayList<Node> newNode = new ArrayList<>();
		for (Node node : nodes) {
			if (node.getEdge1() == edge && !newNode.contains(node.getNext1())) {
				newNode.add(node.getNext1());
			}
		}
		return newNode;

	}

	public boolean hasAll(ArrayList<Node> nodes) {

		for (Node node : nodes) {
			if (node.getNext1() != null && node.getEdge1() == 'e' && !nodes.contains(node.getNext1())) {
				return false;
			}
			if (node.getNext2() != null && node.getEdge2() == 'e' && !nodes.contains(node.getNext2())) {
				return false;
			}
		}
		return true;

	}
}
