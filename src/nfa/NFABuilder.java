package nfa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

public class NFABuilder {

	public NodeController controller = new NodeController();

	public NFA createNFA(String re) {

		String newRe = ReHandler.reChange(re);

		Stack<NFA> nfaStack = new Stack<>();

		for (int i = 0; i < newRe.length(); i++) {
			char c = newRe.charAt(i);

			// if c is character, create a new nfa and push it into the nfastack
			if (ReHandler.isChar(c)) {
				NFA nfa = new NFA(c, controller);
				nfaStack.push(nfa);

				// if c is ., pop the top two elements in the stack and join
				// them, then push the new nfa into the stack
			} else if (c == '.') {
				NFA first = null;
				NFA second = null;
				try {
					first = nfaStack.pop();
					second = nfaStack.pop();
					second.join(first);
					nfaStack.push(second);
					
				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

				//if c is *, pop the top element in the stack and do the function of closure
			} else if (c == '*') {
				try {
					NFA nfa = nfaStack.pop();
					nfa.closure(controller);
					nfaStack.push(nfa);
					
					
				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

			}else if (c=='|') {
				NFA first = null;
				NFA second = null;
				try {
					first = nfaStack.pop();
					second = nfaStack.pop();
					first.select(second, controller);
					nfaStack.push(first);
					
				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}
				
			}
		}

		return nfaStack.pop();
	}
	
//	public static void main(String[] args) {
//		NFABuilder builder= new NFABuilder();
//		
//		String re= "ab*(a|b)*";
//		
//		NFA nfa=builder.createNFA(re);
//		Node start =nfa.start;
//		print(start);
//	}
//	
//	public static void print(Node start){
//		if (start.getNextAll()==null) {
//			return ;
//		}else {
//			if (start.getNext1()!=null) {
//				System.out.println(start.getNodeID()+"-"+start.getEdge1()+"->"+start.getNext1().getNodeID());
//			}
//			if (start.getNext2()!=null) {
//				System.out.println(start.getNodeID()+"-"+start.getEdge2()+"->"+start.getNext2().getNodeID());
//			}
//			if (start.getNext1()!=null&&start.getNext2()!=null) {
//				if (start.getNext1()==start.getNext2()) {
//					print(start.getNext1());
//				}else {
//					print(start.getNext1());
//					print(start.getNext2());
//				}
//			}else {
//				if (start.getNext1() != null) {
//					print(start.getNext1());
//				}
//				if (start.getNext2() != null) {
//					print(start.getNext2());
//				}
//			}
//			
//		}
//	}
}
