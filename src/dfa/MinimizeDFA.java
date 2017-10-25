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

			ArrayList<ArrayList<DFANode>> temp = new ArrayList<>();
			int kindExist = 0;

			for (ArrayList<DFANode> dfaList : allNodes) {
				ArrayList<Integer> kinds = new ArrayList<>();
				for (DFANode dfaNode : dfaList) {
					int kind = whichKind(dfaNode, 'a', allNodes);
					if (!kinds.contains(kind)) {
						ArrayList<DFANode> nodes = new ArrayList<>();
						nodes.add(dfaNode);
						temp.add(nodes);
						kindExist++;
					} else {
						temp.get(kindExist + kinds.indexOf(kind)).add(dfaNode);
					}
				}
			}

			allNodes = temp;
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

				//检验a边
				if (dfaNode.getNext1() != null) {
					// 如果kinds为空，加入种类
					if (kinds.isEmpty()) {
						kinds.add(whichKind(dfaNode, 'a', dfaLists));
						// 如果下一个和已有种类不同,返回可分
					} else if (kinds.size() >= 1 && !kinds.contains(whichKind(dfaNode, 'a', dfaLists))) {
						return true;
					}
				}
				
				//检验b边
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
			ArrayList<DFANode> nodes=allNodes.get(i);
			ArrayList<DFANode> node=dfaNode.getNextDFA(edge);
			for (DFANode dfaNode2 : node) {
				if (nodes.contains(dfaNode2)) {
					return i;
				}
			}
		}

		return -1;
	}

}
