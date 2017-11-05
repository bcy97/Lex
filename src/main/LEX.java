package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dfa.DFA;
import dfa.DFABulider;
import dfa.DFANode;
import dfa.MinimizeDFA;
import nfa.NFA;
import nfa.NFABuilder;

public class LEX {

	public static void main(String[] args) {

		HashMap<String, DFA> dfas = init();
		
		
		File file = new File(System.getProperty("user.dir")+"/file/input.txt");
		
		try {
			BufferedReader reader= new BufferedReader(new FileReader(file));
			String string = reader.readLine();
			
			// 不停的读入字符
			while (string!=null) {
				String[] strs=string.split(" ");
				
				for (String str : strs) {
					if (str.equals("")) {
						continue;
					}
					judgeToken(str, dfas);
				}
				
				string = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static HashMap<String, DFA> init() {

		HashMap<String, DFA> dfas = new HashMap<>();

		ArrayList<String> reStrings = new ArrayList<>();
		ArrayList<String> tokens = new ArrayList<>();
		File file = new File(System.getProperty("user.dir")+"/file/re.txt");

		String s = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
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

			dfas.put(tokens.get(i), minDfa);

		}

		return dfas;

	}

	public static void judgeToken(String string, HashMap<String, DFA> dfas) {
		boolean found = false;

		// 先判断是否有保留字
		DFA dfa = dfas.get("reserve");
		if (dfa != null) {
			// 从dfa的初状态开始
			DFANode state = dfa.getStartNodes().get(0);
			int i = 0;
			for (i = 0; i < string.length(); i++) {
				DFANode node = state.getNextDFA(string.charAt(i));
				if (node != null) {
					state = node;
				} else {
					break;
				}
			}
			if (i == string.length() && dfa.getEndNodes().contains(state)) {
				System.out.println("Token{type='reserve', code='" + string + "', error='null'}");
				return;
			}
		}

		// 遍历所有的dfa，寻找匹配的dfa
		for (String token : dfas.keySet()) {

			// 判断是否为保留字
			String newString = string;
			newString = CharHandler.change(string);

			dfa = dfas.get(token);

			// 从dfa的初状态开始
			DFANode state = dfa.getStartNodes().get(0);
			int i = 0;
			for (i = 0; i < newString.length(); i++) {
				DFANode node = state.getNextDFA(newString.charAt(i));
				if (node != null) {
					state = node;
				} else {
					break;
				}
			}
			if (i == string.length() && dfa.getEndNodes().contains(state)) {
				// System.out.println(string + " " + token);
				System.out.println("Token{type='" + token + "', code='" + string + "', error='null'}");
				found = true;
			}
		}

		if (!found) {
			System.out.println("Token{type='null', code='" + string + "', error='null'}");
		}
	}
}
