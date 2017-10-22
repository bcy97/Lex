package nfa;

import java.io.InputStream;
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
					first.join(second);
					nfaStack.push(first);
					
				} catch (Exception e) {
					System.out.println("there are not two nfa in the stack");
				}

				//if c is *, pop the top element in the stack and do the function of closure
			} else if (c == '*') {
				try {
					NFA nfa = nfaStack.pop();
					nfa.closure(controller);
					nfaStack.push(nfa);
					
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
	
	public static void main(String[] args) {
		NFABuilder builder= new NFABuilder();
		
		String re= "ab*(a|b)*";
		
		NFA nfa=builder.createNFA(re);
		Node start =nfa.start;
		while(start.getNext1()!=null){
			System.out.println(start.getNodeID()+"-"+start.getEdge1()+"->"+start.getNext1().getNodeID());
			if (start.getNext2()!=null) {
				System.out.println(start.getNodeID()+"-"+start.getEdge2()+"->"+start.getNext2().getNodeID());
			}
			start=start.getNext1();
		}
	}
}
