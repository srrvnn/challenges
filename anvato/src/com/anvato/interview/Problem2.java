package com.anvato.interview;

import java.util.ArrayList;

public class Problem2 {
	
	public static final boolean DEBUG = true;
	
	public static ArrayList<String> r = new ArrayList<String>();
	
	public static void permutationString(String input) {
		
		char[] character_array_string = input.toCharArray();
		
		ArrayList<Character> chars = new ArrayList<Character>();
		
		// converting char array into Character ArrayList
		
		for( int i = 0; i < character_array_string.length; i++) {
			
			chars.add(character_array_string[i]);
		}		

		if(DEBUG) System.out.println(chars);
		
		for( int i = 0; i < chars.size(); i++) {
			
			ArrayList<Character> temp = new ArrayList<Character>(chars);
			char tempc = temp.get(i);
			temp.remove(i);
			
			String s = "" + tempc;
			
			rec(s, tempc, temp);
		}
		
	}
	
	public static void rec(String result, char c, ArrayList<Character> remaining) {
		
		if(DEBUG) System.out.println("From rec function: " + result + " with " + c + " and " + remaining);
		
		if(remaining.size() == 0) {
			r.add(result);
		}
		
		for( int i = 0; i < remaining.size(); i++) {
			
			ArrayList<Character> temp = new ArrayList<Character>(remaining);
			char tempc = temp.get(i);
			temp.remove(i);
			
			rec(result + tempc, tempc, temp);
		}
		
	}

	public static void main(String[] args) {
		
		permutationString("abc");
		
		if(DEBUG) System.out.println(r.size());
		
		for(String s : r) {
			System.out.println(s);
		}

	}

}
