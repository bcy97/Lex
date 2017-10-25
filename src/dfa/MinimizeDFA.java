package dfa;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class MinimizeDFA {

	public DFA minimizeDFA(DFA dfa) {

		ArrayList<DFANode> starts = dfa.getStartNodes();
		ArrayList<DFANode> end = dfa.getEndNodes();

		// 将开始和终止节点分类放入allNodes
		ArrayList<ArrayList<DFANode>> allNodes = new ArrayList<>();
		allNodes.add(starts);
		allNodes.add(end);

		while (canDivided(allNodes)) {

			allNodes = divideByEdge(allNodes, 'a');

			// 按b边分解
			allNodes = divideByEdge(allNodes, 'b');
		}

		for (ArrayList<DFANode> arrayList : allNodes) {
			for (DFANode dfaNode : arrayList) {
				System.out.print(dfaNode.getNodeID() + " ");
			}
			System.out.println();
		}

		return null;
	}

	public boolean canDivided(ArrayList<ArrayList<DFANode>> dfaLists) {

		// 遍历每一个dfa0
		for (ArrayList<DFANode> dfaList : dfaLists) {

			// 如果该集合仅有一个元素，跳过
			if (dfaList.size() == 1) {
				continue;
			}

			ArrayList<Integer> kinds = new ArrayList<>();

			// 如果有不止一个元素，开始遍历
			for (DFANode dfaNode : dfaList) {
				/*
				 * 遍历字母表里的边（有时间就添加）
				 */

				// 检验a边
				if (dfaNode.getNext1() != null) {
					// 如果kinds为空，加入种类
					if (kinds.isEmpty()) {
						kinds.add(whichKind(dfaNode, 'a', dfaLists));
						// 如果下一个和已有种类不同,返回可分
					} else if (kinds.size() >= 1 && !kinds.contains(whichKind(dfaNode, 'a', dfaLists))) {
						return true;
					}
				}

			}

			kinds.removeAll(kinds);
			for (DFANode dfaNode : dfaList) {
				// 检验b边
				if (dfaNode.getNext2() != null) {
					// 如果kinds为空，加入种类
					if (kinds.isEmpty()) {
						kinds.add(whichKind(dfaNode, 'b', dfaLists));
						// 如果下一个和已有种类不同,返回可分
					} else if (kinds.size() >= 1 && !kinds.contains(whichKind(dfaNode, 'b', dfaLists))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int whichKind(DFANode dfaNode, char edge, ArrayList<ArrayList<DFANode>> allNodes) {

		for (int i = 0; i < allNodes.size(); i++) {
			ArrayList<DFANode> nodes = allNodes.get(i);
			ArrayList<DFANode> node = dfaNode.getNextDFA(edge);
			for (DFANode dfaNode2 : node) {
				if (nodes.contains(dfaNode2)) {
					return i;
				}
			}
		}

		return -1;
	}

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
