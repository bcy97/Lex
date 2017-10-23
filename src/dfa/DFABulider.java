package dfa;

import java.util.ArrayList;

import nfa.NFA;
import node.Node;
import node.NodeController;

public class DFABulider {
	
	NodeController nodeController;
	
	public DFABulider() {
		nodeController=new NodeController();
	}

	public DFA dfaBuilder(NFA nfa) {
		//初始化arraylis存放dfa节点
		ArrayList<ArrayList<Node>> dfaNodes=new ArrayList<>();
		
		//将nfa的start节点的闭包作为初始节点放入
		ArrayList<Node> start=new ArrayList<>();
		start.add(nfa.getStart());
		dfaNodes.add(find_E_Closure(start));
		
		return null;
	}
	
	public ArrayList<Node> find_E_Closure(ArrayList<Node> nodes) {
		//如果还有新的ε边，继续循环
		while (!hasAll(nodes)) {
			//新arraylist 装入新的边
			ArrayList<Node> temp =new ArrayList<>();
			//遍历现有列表，如果列表中节点存在ε边，加入新节点
			for (Node node : nodes) {
				if (node.getEdge1()=='e'&&!nodes.contains(node.getNext1())&&!temp.contains(node.getNext1())) {
					temp.add(node.getNext1());
				}
				if (node.getEdge2()=='e'&&!nodes.contains(node.getNext2())&&!temp.contains(node.getNext2())) {
					temp.add(node.getNext2());
				}
			}
			nodes.addAll(temp);
		}
		return nodes;
	}
	
	public ArrayList<Node> find_next(ArrayList<Node> nodes,char edge) {
		
		ArrayList<Node> newNode=new ArrayList<>();
		for (Node node : nodes) {
			//因为edge2 只能为ε，所以只需访问edge1
			if (node.getEdge1()==edge&&!newNode.contains(node.getNext1())) {
				newNode.add(node.getNext1());
			}
		}
		return newNode;
		
	}
	
	
	public boolean hasAll(ArrayList<Node> nodes) {
		
		for (Node node : nodes) {
			if (node.getNext1()!=null&&node.getEdge1()=='e'&&!nodes.contains(node.getNext1())) {
				return false;
			}
			if (node.getNext2()!=null&&node.getEdge2()=='e'&&!nodes.contains(node.getNext2())) {
				return false;
			}
		}
		return true;
		
	}
}
