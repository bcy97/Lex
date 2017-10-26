package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import dfa.DFA;
import dfa.DFABulider;
import dfa.MinimizeDFA;
import nfa.NFA;
import nfa.NFABuilder;

public class main {
	
	public static void main(String[] args) {

		

	}
	
	public static HashMap<DFA, String> init() {
		
		HashMap<DFA, String> dfas=new HashMap<>();
		
		ArrayList<String> reStrings = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String s = "";
		try {
			while (!(s = reader.readLine()).equals("end")) {
				reStrings.add(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String re : reStrings) {
			
			NFABuilder nfaBuilder = new NFABuilder();
			NFA nfa = nfaBuilder.createNFA(re);
			
			DFABulider bulider = new DFABulider();
			DFA dfa = bulider.createDFA(nfa);
			
			MinimizeDFA minimize = new MinimizeDFA();
			DFA minDfa = minimize.minimizeDFA(dfa);
			
		}
		
		return dfas;
		
	}
}
