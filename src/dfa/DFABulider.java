package dfa;

import java.util.ArrayList;

import fa.Node;
import nfa.NFA;

public class DFABulider {

	public DFABulider() {
	}

	public DFA createDFA(NFA nfa) {
		
		DFA dfa=new DFA(null, null);
		dfa.setAlphabet(nfa.getAlphabet());
		
		int index = 0;

		// 两个arraylist存放节点
		ArrayList<DFANode> dfaNodes = new ArrayList<>();
		ArrayList<DFANode> dfaNodeNotVisited = new ArrayList<>();

		// 两个arraylist 存放节点代表的nfa闭包
		ArrayList<ArrayList<Node>> nodeVisited = new ArrayList<>();
		ArrayList<ArrayList<Node>> nodeNotVisited = new ArrayList<>();

		// 获取初始的闭包
		Node start = nfa.getStart();
		ArrayList<Node> startNodes = new ArrayList<>();
		startNodes.add(start);
		startNodes = find_E_Closure(startNodes);
		// 获取初始的闭包节点
		DFANode node = new DFANode(index, startNodes);
		index++;

		// 将初始节点放入待访问节点
		dfaNodeNotVisited.add(node);
		// 将初始闭包放入待访问闭包数组
		nodeNotVisited.add(startNodes);

		// 如果有未访问的节点，继续循环
		while (!nodeNotVisited.isEmpty()) {
			// 获取第一个未访问节点
			DFANode dfaNode = dfaNodeNotVisited.get(0);

			// 获取第一个未访问节点对应的闭包
			ArrayList<Node> nfaNodes = nodeNotVisited.get(0);

			for (char c : dfa.getAlphabet()) {
				// 寻找当前闭包对应的边的闭包
				ArrayList<Node> next_a_closure = find_E_Closure(find_next(nfaNodes, c));
				DFANode next_a = null;
				if (!next_a_closure.isEmpty()) {
					next_a = new DFANode(index, next_a_closure);
					index++;
				}

				if (next_a != null && next_a_closure.equals(nfaNodes)) {
					dfaNode.setNext(c, dfaNode);
					index--;
				} else if (next_a != null && !nodeVisited.contains(next_a_closure)
						&& !nodeNotVisited.contains(next_a_closure)) {

					dfaNode.setNext(c, next_a);

					// 将新节点添加入待访问
					dfaNodeNotVisited.add(next_a);
					nodeNotVisited.add(next_a_closure);

				} else if (next_a != null && !nodeVisited.contains(next_a_closure)
						&& nodeNotVisited.contains(next_a_closure)) {

					dfaNode.setNext(c, dfaNodeNotVisited.get(nodeNotVisited.indexOf(next_a_closure)));
					index--;

				} else if (next_a != null && nodeVisited.contains(next_a_closure)
						&& !nodeNotVisited.contains(next_a_closure)) {

					dfaNode.setNext(c, dfaNodes.get(nodeVisited.indexOf(next_a_closure)));
					index--;

				}
			}
			
			// 已经访问的加入以访问，从未访问中移除
			dfaNodes.add(dfaNode);
			dfaNodeNotVisited.remove(dfaNode);

			// 已经访问的加入以访问，从未访问中移除
			nodeVisited.add(nfaNodes);
			nodeNotVisited.remove(nfaNodes);

		}

		ArrayList<DFANode> startDFANodes = new ArrayList<>();
		ArrayList<DFANode> endDFANodes = new ArrayList<>();

		for (DFANode dfaNode2 : dfaNodes) {
			if (dfaNode2.getNfaNodes().contains(nfa.getEnd())) {
				endDFANodes.add(dfaNode2);
			} else {
				startDFANodes.add(dfaNode2);
			}
		}

		dfa.setStartNodes(startDFANodes);
		dfa.setEndNodes(endDFANodes);

		return dfa;

	}

	public ArrayList<Node> find_E_Closure(ArrayList<Node> nodes) {
		while (!hasAll(nodes)) {
			ArrayList<Node> temp = new ArrayList<>();
			for (Node node : nodes) {
				if (node.getEdge1() == '#' && !nodes.contains(node.getNext1()) && !temp.contains(node.getNext1())) {
					temp.add(node.getNext1());
				}
				if (node.getEdge2() == '#' && !nodes.contains(node.getNext2()) && !temp.contains(node.getNext2())) {
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
			if (node.getNext1() != null && node.getEdge1() == '#' && !nodes.contains(node.getNext1())) {
				return false;
			}
			if (node.getNext2() != null && node.getEdge2() == '#' && !nodes.contains(node.getNext2())) {
				return false;
			}
		}
		return true;

	}
}
