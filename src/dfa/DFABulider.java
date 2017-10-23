package dfa;

import java.util.ArrayList;

import fa.Node;
import fa.NodeController;
import nfa.NFA;

public class DFABulider {
	
	NodeController nodeController;
	
	public DFABulider() {
		nodeController=new NodeController();
	}

	public DFA dfaBuilder(NFA nfa) {
		//��ʼ��arraylis���dfa�ڵ�
		ArrayList<ArrayList<Node>> dfaNodes=new ArrayList<>();
		
		//��nfa��start�ڵ�ıհ���Ϊ��ʼ�ڵ����
		ArrayList<Node> start=new ArrayList<>();
		start.add(nfa.getStart());
		dfaNodes.add(find_E_Closure(start));
		
		return null;
	}
	
	public ArrayList<Node> find_E_Closure(ArrayList<Node> nodes) {
		//��������µĦűߣ�����ѭ��
		while (!hasAll(nodes)) {
			//��arraylist װ���µı�
			ArrayList<Node> temp =new ArrayList<>();
			//���������б�����б��нڵ���ڦűߣ������½ڵ�
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
			//��Ϊedge2 ֻ��Ϊ�ţ�����ֻ�����edge1
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
