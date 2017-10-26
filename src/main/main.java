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

		HashMap<DFA, String> dfas = init();

		Scanner scanner = new Scanner(System.in);
		String string = scanner.next();

		// 不停的读入字符直到遇到end
		while (!string.equals("end")) {

			// 遍历所有的dfa，寻找匹配的dfa
			for (DFA dfa : dfas.keySet()) {
				// 从dfa的初状态开始
				DFANode state = dfa.getStartNodes().get(0);
				int i = 0;
				for (i = 0; i < string.length(); i++) {
					ArrayList<DFANode> nodes = state.getNextDFA(string.charAt(i));
					if (state.getNextDFA(string.charAt(i)).size() > 0) {
						state = state.getNextDFA(string.charAt(i)).get(0);
					} else {
						break;
					}
				}
				if (i == string.length() && dfa.getEndNodes().contains(state)) {
					System.out.println(dfas.get(dfa));
				}
			}
			string = scanner.next();
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
