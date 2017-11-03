package dfa;

import java.util.ArrayList;
import java.util.Iterator;

public class MinimizeDFA {

	public DFA minimizeDFA(DFA dfa) {

		ArrayList<DFANode> starts = dfa.getStartNodes();
		ArrayList<DFANode> end = dfa.getEndNodes();

		// 将开始和终止节点分类放入allNodes
		ArrayList<ArrayList<DFANode>> allNodes = new ArrayList<>();
		allNodes.add(starts);
		allNodes.add(end);

		// 能继续划分，循环继续
		while (canDivided(allNodes, dfa.getAlphabet())) {

			for (char c : dfa.getAlphabet()) {
				allNodes = divideByEdge(allNodes, c);
			}

		}

		// 分出新的开始和终止节点
		ArrayList<DFANode> newStart = new ArrayList<>();
		ArrayList<DFANode> newEnd = new ArrayList<>();

		// 初始化dfa0Nodes
		ArrayList<DFANode> dfa0Nodes = new ArrayList<>();
		for (int i = 0; i < allNodes.size(); i++) {

			DFANode node = new DFANode(i, null);
			dfa0Nodes.add(node);

			boolean isEnd = false;
			for (DFANode dfaNode : allNodes.get(i)) {
				if (end.contains(dfaNode)) {
					isEnd = true;
					break;
				}
			}

			if (isEnd) {
				newEnd.add(node);
			} else {
				newStart.add(node);
			}

		}

		// 构造dfa0的边
		for (int i = 0; i < allNodes.size(); i++) {

			// 获取当前dfa0节点对应的dfa节点集合
			ArrayList<DFANode> nodeList = allNodes.get(i);
			// 获取当前的dfa0节点
			DFANode dfa0Node = dfa0Nodes.get(i);

			for (DFANode dfaNode : nodeList) {

				for (char edge : dfaNode.getNexts().keySet()) {
					int kind = whichKind(dfaNode, edge, allNodes);
					DFANode next = dfa0Nodes.get(kind);
					if (dfa0Node.getNexts().containsKey(edge) && dfa0Node.getNexts().get(edge) == next) {
						continue;
					} else {
						dfa0Node.setNext(edge, next);
					}
				}
				//
				// if (dfaNode.getNext1() != null) {
				// char edge = dfaNode.getEdge1();
				// int kind = whichKind(dfaNode, edge, allNodes);
				// DFANode next = dfa0Nodes.get(kind);
				//
				// // 判断当前dfa0Node是否有next节点
				// if ((dfa0Node.getNext1() == next && dfa0Node.getEdge1() ==
				// edge)
				// || (dfa0Node.getNext2() == next && dfa0Node.getEdge2() ==
				// edge)) {
				// continue;
				// // 如果dfa0Node的下一个节点个数少于2
				// } else if (dfa0Node.getNextDFA().size() < 2) {
				// dfa0Node.setNext(edge, next);
				// }
				//
				// }
				//
				// if (dfaNode.getNext2() != null) {
				// char edge = dfaNode.getEdge2();
				// int kind = whichKind(dfaNode, edge, allNodes);
				// DFANode next = dfa0Nodes.get(kind);
				//
				// // 判断当前dfa0Node是否有next节点
				// if ((dfa0Node.getNext1() == next && dfa0Node.getEdge1() ==
				// edge)
				// || (dfa0Node.getNext2() == next && dfa0Node.getEdge2() ==
				// edge)) {
				// continue;
				// // 如果dfa0Node的下一个节点个数少于2
				// } else if (dfa0Node.getNextDFA().size() < 2) {
				// dfa0Node.setNext(edge, next);
				// }
				//
				// }
			}
		}

		DFA dfa0 = new DFA(newStart, newEnd);

		// for (ArrayList<DFANode> arrayList : allNodes) {
		// for (DFANode dfaNode : arrayList) {
		// System.out.print(dfaNode.getNodeID() + " ");
		// }
		// System.out.println();
		// }
		//
		// System.out.print("start nodes ");
		// for (DFANode dfaNode : newStart) {
		// System.out.print(dfaNode.getNodeID()+" ");
		// }
		// System.out.println();
		// for (DFANode dfaNode : newStart) {
		// if (dfaNode.getNext1() != null) {
		// System.out.println(
		// dfaNode.getNodeID() + "-" + dfaNode.getEdge1() + "->" +
		// dfaNode.getNext1().getNodeID());
		// }
		// if (dfaNode.getNext2() != null) {
		// System.out.println(
		// dfaNode.getNodeID() + "-" + dfaNode.getEdge2() + "->" +
		// dfaNode.getNext2().getNodeID());
		// }
		// }
		// System.out.print("end nodes ");
		// for (DFANode dfaNode : newEnd) {
		// System.out.print(dfaNode.getNodeID()+" ");
		// }
		// System.out.println();
		// for (DFANode dfaNode : newEnd) {
		// if (dfaNode.getNext1() != null) {
		// System.out.println(
		// dfaNode.getNodeID() + "-" + dfaNode.getEdge1() + "->" +
		// dfaNode.getNext1().getNodeID());
		// }
		// if (dfaNode.getNext2() != null) {
		// System.out.println(
		// dfaNode.getNodeID() + "-" + dfaNode.getEdge2() + "->" +
		// dfaNode.getNext2().getNodeID());
		// }
		// }

		return dfa0;
	}

	/**
	 * 判断当前dfa0是否可分
	 * 
	 * @param dfaLists
	 * @param alphabet
	 * @return
	 */
	public boolean canDivided(ArrayList<ArrayList<DFANode>> dfaLists, ArrayList<Character> alphabet) {

		// 遍历每一个dfa0
		for (ArrayList<DFANode> dfaList : dfaLists) {

			// 如果该集合仅有一个元素，跳过
			if (dfaList.size() == 1) {
				continue;
			}

			// 如果有不止一个元素，开始遍历
			for (char c : alphabet) {
				ArrayList<Integer> kinds = new ArrayList<>();

				for (DFANode dfaNode : dfaList) {

					// 检验字母表中的边
					// 如果kinds为空，加入种类
					if (kinds.isEmpty()) {
						kinds.add(whichKind(dfaNode, c, dfaLists));
						// 如果下一个和已有种类不同,返回可分
					} else if (kinds.size() >= 1 && !kinds.contains(whichKind(dfaNode, c, dfaLists))) {
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * 找到dfa的下一个状态对应的dfa0状态
	 * 
	 * @param dfaNode
	 * @param edge
	 * @param allNodes
	 * @return
	 */
	public int whichKind(DFANode dfaNode, char edge, ArrayList<ArrayList<DFANode>> allNodes) {

		for (int i = 0; i < allNodes.size(); i++) {
			ArrayList<DFANode> nodes = allNodes.get(i);
			DFANode node = dfaNode.getNextDFA(edge);
			if (nodes.contains(node)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 按照边来分新的等价
	 * 
	 * @param allNodes
	 * @param edge
	 * @return
	 */
	public ArrayList<ArrayList<DFANode>> divideByEdge(ArrayList<ArrayList<DFANode>> allNodes, char edge) {
		// 存放拆分后的新数组一个Arraylist<dfanode>代表一个dfa0节点
		ArrayList<ArrayList<DFANode>> temp = new ArrayList<>();

		// 存放当前dfa的偏移量
		int kindNum = 0;
		// 遍历每一个dfa0节点
		for (ArrayList<DFANode> dfaList : allNodes) {
			// 存放不同的种类
			ArrayList<Integer> kinds = new ArrayList<>();

			int newKindNum = 0;
			// 遍历每一个dfa0节点中的dfa节点
			for (DFANode dfaNode : dfaList) {
				// 获取dfa节点对应的dfa0节点号
				int kind = whichKind(dfaNode, edge, allNodes);

				// 如果对应新的dfa0节点，新建一个dfa0节点
				if (!kinds.contains(kind)) {

					ArrayList<DFANode> nodes = new ArrayList<>();
					nodes.add(dfaNode);

					// 将新建的dfa0节点放入拆分后的数组
					temp.add(nodes);

					// 将已有类别放入数组
					kinds.add(kind);
					newKindNum++;

				} else {

					temp.get(kindNum + kinds.indexOf(kind)).add(dfaNode);
				}

			}
			kindNum += newKindNum;
		}

		return temp;
	}

}
