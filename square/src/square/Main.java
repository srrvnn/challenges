package square;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {	
	
	public static final boolean DEBUG = false;
	
	/**
	 * SuffixNode stores suffix and index of the suffix in the parent string
	 * implements comparable to sort based on suffix (if needed) 
	 */
	public static class SuffixNode implements Comparable{
		
		String suffix; 
		int index;
		
		SuffixNode(String s, int i) {
			
			suffix = s;
			index = i;
		}
		
		public void set(String s, int i) {
			suffix = s;
			index = i;
		}

		@Override
		public int compareTo(Object o) {
			
			return this.suffix.compareTo(((SuffixNode)o).suffix);
		}
		
		@Override
		public String toString() {
			
			return index + "-" + suffix;
		}
		
	}
	
	/**
	 * Function to find the common prefix between two substrings ensuring no overlap occurs
	 * 
	 * @param a: SuffixNode a to compare  
	 * @param b: SuffixNode b to compare
	 * @return common prefix as a string 
	 */
	public static String findCommonPrefix(SuffixNode a, SuffixNode b) {
		
		if(DEBUG) System.out.println("");
		
		if(DEBUG) System.out.print(a + " ");
		if(DEBUG) System.out.print(b + " ");
		
		// distance between suffixes in the parent string		
		int distance = Math.abs(a.index - b.index);
		
		// length of the smaller string 
		int check = Math.min(a.suffix.length(), b.suffix.length());
		
		if(DEBUG) System.out.print(distance + " ");
		if(DEBUG) System.out.print(check + " ");
		
		// compare all characters up to the length of the smaller string		
		for (int i = 0, o = 0; i < check; i++, o++) {
			
			// if a mismatch occurs, return till the previous index
			if(a.suffix.charAt(i) != b.suffix.charAt(i)){
				return a.suffix.substring(0, i);				
			}
			
			// if the distance between the two strings has been consumed, overlap is about to begin 
			// therefore return till the the previous index 
			if(o == distance) {
				return a.suffix.substring(0, i);									
			}
		}
		
		// this means the smaller substring (of the parent) is a substring of the larger substring (of the parent) 
		return a.suffix.substring(0, check);
	}
	
	/**
	 * Function to find the longest repeated substring in a given parent string
	 * 
	 * @param s: the parent string
	 * @return longest repeated substring or "NONE" if no such string exists
	 */
	public static String findRepeatedSubstring(String s) {
		
		String output = "";
		
		// replace multiple space characters 
		
		String input = s.replaceAll("\\s+", " ");
		int length = input.length();
		
		// building suffix nodes with suffixes and index
		// O(n^2) time and O(n^2) space
		
		SuffixNode[] suffixes = new Main.SuffixNode[length];
		
		for(int i = 0 ; i < length; i++) {
			
			suffixes[i] = new Main.SuffixNode(input.substring(i,length), i);
		}
		
		// sort suffix nodes (removed as sorting and checking consecutive string failes for "aaaaaa" 
		// O(nlongn) time
		
		// Arrays.sort(suffixes);
		
		if(DEBUG) {
			
			System.out.println("Printing suffix nodes");
		
			for(int i = 0; i < length; i++) {
				
				System.out.println(suffixes[i]);
			}
		}
		
		// find common prefixes between all two suffix nodes
		// O(n^2) time
		
		for(int i = 0 ; i < length; i++) {
			
			for(int j = 0; j < length; j++) {
			
				String common = findCommonPrefix(suffixes[i], suffixes[j]);
				
				if(DEBUG) System.out.println(common);
				
				if(common.length() > output.length()) {
					output = common; 
				}
			}
		}
		
		if(output.equals("")) 
			return "NONE";
		else return output;	
		
	}

	public static void main(String[] args) throws IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			
		    for(String line; (line = br.readLine()) != null; ) {
		    	
		    	System.out.println(findRepeatedSubstring(line));
		    	
		    	// findString works in O(n^2) time and O(n^2) space.
		    	
		    	// can be optimized to use a suffixtree instead of a suffix array to work in O(n) time and O(n) space
		    	
		    	// but using a suffixtree will allow overlapping,
		    	// and building a suffixtree that doesn't allow overlapping falls back to O(n^2) time and O(n^2) in the worst case.     
		    }
		    
		} catch (FileNotFoundException e) {
			
			System.out.println("The program expects an absolute file path as args[0]");
			return;
			
		} catch (Exception e) {
			
			System.out.println("Unknown exception occured while reading the file");
			return;
		}
		
	}	
}
