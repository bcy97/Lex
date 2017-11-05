package nfaTest;

import nfa.ReHandler;

public class RETest {

	
	public static void main(String []args){
//		System.out.println(ReHandler.reChange("=|\\*|-|+|/|;"));
		System.out.println(ReHandler.reChange("((-|d)(d*\\.d*))|((-|d)(d*))"));
	}
}
