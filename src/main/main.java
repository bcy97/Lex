package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dfa.DFA;
import dfa.DFABulider;
import dfa.DFANode;
import dfa.MinimizeDFA;
import nfa.NFA;
import nfa.NFABuilder;

public class main {

	public static void main(String[] args) {
		
		HashMap<DFA, String> dfas=init();
		
		Scanner scanner=new Scanner(System.in);
		String string=scanner.next();
		
		while(!string.equals("end")) {
			for (DFA dfa : dfas.keySet()) {
				DFANode state=dfa.getStartNodes().get(0);
				for (int i = 0; i < string.length(); i++) {
					while (state.getNextDFA(string.charAt(i))!=null) {
						state=state.getNextDFA(string.charAt(i)).get(0);
					}
				}
				if (dfa.getEndNodes().contains(state)) {
					System.out.println(dfas.get(dfa));
				}
			}
		}

	}

	public static HashMap<DFA, String> init() {

		HashMap<DFA, String> dfas = new HashMap<>();

		ArrayList<String> reStrings = new ArrayList<>();
		ArrayList<String> tokens = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String s = "";
		try {
			while (!(s = reader.readLine()).equals("end")) {

				String[] token = s.split(" ");

				tokens.add(token[0]);
				reStrings.add(token[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < reStrings.size(); i++) {

			NFABuilder nfaBuilder = new NFABuilder();
			NFA nfa = nfaBuilder.createNFA(reStrings.get(i));

			DFABulider bulider = new DFABulider();
			DFA dfa = bulider.createDFA(nfa);

			MinimizeDFA minimize = new MinimizeDFA();
			DFA minDfa = minimize.minimizeDFA(dfa);

			dfas.put(minDfa, tokens.get(i));

		}

		return dfas;

	}
}
