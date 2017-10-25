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
						ArrayList<DFANode> nodes=new ArrayList<>();
						nodes.add(dfaNode);
						temp.add(nodes);
						kindExist++;
					}else {
						temp.get(kindExist+kinds.indexOf(kind)).add(dfaNode);
					}
				}
			}
			
			allNodes=temp;
		}
		
		for (ArrayList<DFANode> arrayList : allNodes) {
			for (DFANode dfaNode : arrayList) {
				System.out.print(dfaNode.getNodeID()+" ");
			}
			System.out.println();
		}

		return null;
	}

	public boolean canDivided(ArrayList<ArrayList<DFANode>> dfaLists) {

		for (ArrayList<DFANode> dfaList : dfaLists) {
			if (dfaList.size()==1) {
				continue;
			}
			for (DFANode dfaNode : dfaList) {
				for (DFANode nextDFANode : dfaNode.getNexts()) {
					if (!dfaList.contains(nextDFANode)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int whichKind(DFANode dfaNode, char edge, ArrayList<ArrayList<DFANode>> allNodes) {

		for (int i = 0; i < allNodes.size(); i++) {
			if (allNodes.get(i).contains(dfaNode.getNext(edge))) {
				return i;
			}
		}

		return -1;
	}

}
